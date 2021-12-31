package com.example.text_detect_check;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ScanActivity extends AppCompatActivity {

    private TextView resultTv;
    private Button snap;
    private Button detect;
    private String strings;

    StringBuilder stringBuilder = new StringBuilder();
    List<String> scheduled=new ArrayList<>();
    List<String> medicine_name=new ArrayList<>();
    private static final int REQUEST_IMAGE_CAPTURE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        resultTv = findViewById(R.id.idTVDetectedText);
        snap=findViewById(R.id.idBtnsnap);
        detect=findViewById(R.id.idBtndetect);
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ScanActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_IMAGE_CAPTURE);
        }
        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(ScanActivity.this);
            }
        });
        detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strings.length()>100)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(ScanActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("your detected text is too long.");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
                else
                {
                    Intent in=new Intent(ScanActivity.this,MovetextActivity.class);
                    String[] medi_time = scheduled.toArray(new String[0]);
                    String[] medi_name = medicine_name.toArray(new String[0]);
                    in.putExtra("scheduled", medi_time);
                    in.putExtra("medcinine_name", medi_name);
                    startActivity(in);

                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri=result.getUri();
                try {
                   Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    detectTexts(imageBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void detectTexts(Bitmap bitmap) {
        System.out.println("----------------------------first--------------------------");
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()) {
            Toast.makeText(ScanActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
        } else {
            stringBuilder.setLength(0);
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            String[] value=new String[textBlockSparseArray.size()];
            for (int i = 0; i < textBlockSparseArray.size(); i++) {
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append(",");
            }
            resultTv.setText(stringBuilder.toString());
            strings = stringBuilder.toString();
            String[] str=strings.split(",");
            for(int i=0;i<str.length;i++)
            {
                if(Pattern.matches("[^a-zA-Z]*",str[i]) && str[i].length()>1)
                {
                    scheduled.add(str[i]);
                }
                else if(Pattern.matches("[a-zA-z]*",str[i]))
                {
                    medicine_name.add(str[i]);
                }
            }
//            System.out.println("------------------------"+scheduled+"--------------------------"+medicine_name);
        }
    }
}