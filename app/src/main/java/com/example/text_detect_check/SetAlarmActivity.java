//package com.example.text_detect_check;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.google.android.material.timepicker.MaterialTimePicker;
//import com.google.android.material.timepicker.TimeFormat;
//
//import java.util.Calendar;
//
//public class SetAlarmActivity extends AppCompatActivity {
//
//    public ImageView morn;
//    public ImageView after;
//    public ImageView night;
//    public EditText morning_text;
//    EditText afternoon_text;
//    EditText night_text;
//    private Calendar calendar;
//    private MaterialTimePicker picker;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_alarm);
//        morn=findViewById(R.id.btn_morn);
//        after=findViewById(R.id.btn_after);
//        night=findViewById(R.id.btn_night);
//        morning_text=findViewById(R.id.morning);
//        afternoon_text=findViewById(R.id.afternoon);
//        night_text=findViewById(R.id.night);
//
//        morn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showTimePicker1();
//            }
//        });
//        after.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showTimePicker2();
//            }
//        });
//        night.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showTimePicker3();
//            }
//        });
//    }
//    public void showTimePicker3() {
//        picker = new MaterialTimePicker.Builder()
//                .setTimeFormat(TimeFormat.CLOCK_12H)
//                .setHour(12)
//                .setMinute(0)
//                .setTitleText("Select Alarm Time")
//                .build();
//
//        picker.show(getSupportFragmentManager(),"foxandroid");
//
//        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (picker.getHour() > 12){
//
//                    night_text.setText(
//                            String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",picker.getMinute())+" PM"
//                    );
//
//                }else {
//
//                    night_text.setText(picker.getHour()+" : " + picker.getMinute() + " AM");
//
//                }
//
//                calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
//                calendar.set(Calendar.MINUTE,picker.getMinute());
//                calendar.set(Calendar.SECOND,0);
//                calendar.set(Calendar.MILLISECOND,0);
//
//            }
//        });
//
//    }
//
//    public void showTimePicker2() {
//        picker = new MaterialTimePicker.Builder()
//                .setTimeFormat(TimeFormat.CLOCK_12H)
//                .setHour(12)
//                .setMinute(0)
//                .setTitleText("Select Alarm Time")
//                .build();
//
//        picker.show(getSupportFragmentManager(),"foxandroid");
//
//        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (picker.getHour() > 12){
//
//                    afternoon_text.setText(
//                            String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",picker.getMinute())+" PM"
//                    );
//
//                }else {
//
//                    afternoon_text.setText(picker.getHour()+" : " + picker.getMinute() + " AM");
//
//                }
//
//                calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
//                calendar.set(Calendar.MINUTE,picker.getMinute());
//                calendar.set(Calendar.SECOND,0);
//                calendar.set(Calendar.MILLISECOND,0);
//
//            }
//        });
//    }
//
//    public void showTimePicker1() {
//        picker = new MaterialTimePicker.Builder()
//                .setTimeFormat(TimeFormat.CLOCK_12H)
//                .setHour(12)
//                .setMinute(0)
//                .setTitleText("Select Alarm Time")
//                .build();
//
//        picker.show(getSupportFragmentManager(),"foxandroid");
//
//        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (picker.getHour() > 12){
//
//                    morning_text.setText(
//                            String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",picker.getMinute())+" PM"
//                    );
//
//                }else {
//
//                    morning_text.setText(picker.getHour()+" : " + picker.getMinute() + " AM");
//
//                }
//
//                calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
//                calendar.set(Calendar.MINUTE,picker.getMinute());
//                calendar.set(Calendar.SECOND,0);
//                calendar.set(Calendar.MILLISECOND,0);
//
//            }
//        });
//    }
//}