package com.example.text_detect_check;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    public EditText usernameEdit,passwordEdit,confirmEdit,name;
    public TextView loginTv;
    public Button registerBtn;
    public FirebaseAuth mAuth;
    boolean  isEmailValid, isCnfValid, isPasswordValid;
    public TextInputLayout emailError, cnfError, passError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEdit=findViewById(R.id.idEdtUserName);
        passwordEdit=findViewById(R.id.idEdtPassword);
        confirmEdit=findViewById(R.id.idEdtConfirmPassword);
        emailError = findViewById(R.id.emailError);
        cnfError =  findViewById(R.id.cnfError);
        passError = findViewById(R.id.passError);

        registerBtn=findViewById(R.id.idBtnRegister);
        loginTv=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regiterUser();
            }

        });
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void regiterUser() {
        String email_text=usernameEdit.getText().toString().trim();
        String pass_text=passwordEdit.getText().toString().trim();
        String cnf_text=confirmEdit.getText().toString().trim();
        //USER NAME VALIDATION
        if (email_text.isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }
        //PASSWORD VALIDATION
        if (pass_text.isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (pass_text.length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }
        if (cnf_text.isEmpty()) {
            cnfError.setError(getResources().getString(R.string.password_error));
            isCnfValid = false;
        } else if (cnf_text.length() < 6) {
            cnfError.setError(getResources().getString(R.string.match_pass));
            isCnfValid = false;
        }
        else if(!pass_text.equals(cnf_text)){
            cnfError.setError(getResources().getString(R.string.match_pass));
            isCnfValid = false;
        }
        else
          {
            isCnfValid = true;
            passError.setErrorEnabled(false);
        }
        if (isEmailValid && isCnfValid && isPasswordValid) {
            mAuth.createUserWithEmailAndPassword(email_text,pass_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this,"heloo----------------",Toast.LENGTH_SHORT).show();
                        User user=new User(email_text,pass_text,cnf_text);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(RegisterActivity.this,"xxxxx",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                        });

                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Not successfully registered",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else{
            Toast.makeText(RegisterActivity.this,"Not,,,,,,,registered",Toast.LENGTH_SHORT).show();
        }


    }
}