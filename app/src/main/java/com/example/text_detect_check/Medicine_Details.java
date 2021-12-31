package com.example.text_detect_check;

public class Medicine_Details {
    public String medi_name;
   public String morning;
    public String afternoon;
    public String night;

    public Medicine_Details(){}
    public Medicine_Details(String med_name, String morn,String after,String night){
        this.medi_name=med_name;
        this.morning=morn;
        this.afternoon=after;
        this.night=night;
    }

    public String getMedi_name() {
        return medi_name;
    }

    public void setMedi_name(String medi_name) {
        this.medi_name = medi_name;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }



}
