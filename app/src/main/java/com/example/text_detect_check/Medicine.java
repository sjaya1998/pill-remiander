package com.example.text_detect_check;

public class Medicine {
    String userid;
    String medicine_name;
    String  medicine_time;
    public Medicine(){

    }
    public Medicine(String userid,String medicine,String scheduled)
    {
        this.userid=userid;
        this.medicine_name=medicine;
        this.medicine_time=scheduled;
    }
}
