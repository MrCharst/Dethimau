package com.example.dethimau;

public class Contact_An {
    private int id;
    private String Name;
    private String PhoneNum;

    public Contact_An() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public Contact_An(int id, String name, String phoneNum) {
        this.id = id;
        Name = name;
        PhoneNum = phoneNum;
    }
}
