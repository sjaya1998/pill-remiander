package com.example.text_detect_check;

public class User {
    public String userName,password,cnfpassWord;
    public User(){

    }
    public User(String username,String pass,String cnf)
    {
        this.userName=username;
        this.password=pass;
        this.cnfpassWord=cnf;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnfpassWord() {
        return cnfpassWord;
    }

    public void setCnfpassWord(String cnfpassWord) {
        this.cnfpassWord = cnfpassWord;
    }
}
