package com.example.text_detect_check;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.timepicker.MaterialTimePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<Medicine_Details> med_list=new ArrayList<Medicine_Details>();
    public Calendar calendar;
    public MaterialTimePicker picker;
    public int length=0;


    public Adapter(List<Medicine_Details> med_list){
        this.med_list=med_list;

    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String med_name=med_list.get(position).getMedi_name();
        String morn=med_list.get(position).getMorning();
        String after=med_list.get(position).getAfternoon();
        String night=med_list.get(position).getNight();
        holder.setData(med_name,morn,after,night);
    }

    @Override
    public int getItemCount() {
        return med_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public EditText med_name;
        public EditText morn;
        public EditText after;
        public EditText night;
        private ImageView check_morning;
        private ImageView check_after;
        private ImageView check_night;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            med_name=itemView.findViewById(R.id.med_name);
            morn=itemView.findViewById(R.id.morning);
            after=itemView.findViewById(R.id.afternoon);
            night=itemView.findViewById(R.id.night);
            check_morning=itemView.findViewById(R.id.btn_morn);
            check_after=itemView.findViewById(R.id.btn_after);
            check_night=itemView.findViewById(R.id.btn_night);
            check_morning.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i=(new Intent(v.getContext(),CalenderActivity.class));
//                    i.putExtra("medicine_name", (Parcelable) med_name);
                    Intent i=new Intent(Intent.ACTION_INSERT);
                    i.setData(CalendarContract.Events.CONTENT_URI);
                    i.putExtra(CalendarContract.Events.TITLE,med_name.getText().toString()+"-"+morn.getText().toString());
                    i.putExtra(CalendarContract.Events.ALL_DAY,"true");
                    v.getContext().startActivity(i);
                }

            });
            check_after.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Intent.ACTION_INSERT);
                    i.setData(CalendarContract.Events.CONTENT_URI);
                    i.putExtra(CalendarContract.Events.TITLE,med_name.getText().toString()+"-"+after.getText().toString());
                    i.putExtra(CalendarContract.Events.ALL_DAY,"true");
                    v.getContext().startActivity(i);
                }
            });
            check_night.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Intent.ACTION_INSERT);
                    i.setData(CalendarContract.Events.CONTENT_URI);
                    i.putExtra(CalendarContract.Events.TITLE,med_name.getText().toString()+"-"+night.getText().toString());
                    i.putExtra(CalendarContract.Events.ALL_DAY,"true");
                    v.getContext().startActivity(i);
                }
            });

        }
        public void setData(String med, String mo, String af, String ni) {
              med_name.setText(med);
              morn.setText(mo);
              after.setText(af);
              night.setText(ni);
        }

    }

}
