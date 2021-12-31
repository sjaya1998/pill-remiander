package com.example.text_detect_check;

import java.lang.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class MovetextActivity extends AppCompatActivity {

    String[] medi_taken_time;
    String[]  medi_name;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    public List<Medicine_Details> med_list;
    public Adapter adapter;
    private Calendar calendar;
    private MaterialTimePicker picker;
    public Button btn;
    private Button alldone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        RelativeLayout myLayout = new RelativeLayout(this);
//        myLayout.addView(alldone);
//        setContentView(myLayout);
//        TextView textView = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movetext);
        Bundle bundle = getIntent().getExtras();
        medi_taken_time = getIntent().getStringArrayExtra("scheduled");
        medi_name=getIntent().getStringArrayExtra("medcinine_name");
        initData();
        initRecyclerView();

    }


    public void initData() {
//        if(medi_taken_time.length<4)
//        {
//            Toast.makeText(MovetextActivity.this,"Check your input!!Scan properly",Toast.LENGTH_SHORT).show();
//            Intent i=new Intent(MovetextActivity.this,ScanActivity.class);
//            startActivity(i);
//        }
//        else
//        {
            med_list=new ArrayList<Medicine_Details>();
            for(int i=0;i<medi_name.length;i++){
                String morn=medi_taken_time[i].charAt(0)+"";
                String after=medi_taken_time[i].charAt(2)+"";
                String night=medi_taken_time[i].charAt(4)+"";
                System.out.println("+++++++++++++++++++"+morn+"+++++++++++++++++++++"+after+"------------------"+"nigth");
                med_list.add(new Medicine_Details(medi_name[i],morn,after,night));

//            }
        }

    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(med_list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}











