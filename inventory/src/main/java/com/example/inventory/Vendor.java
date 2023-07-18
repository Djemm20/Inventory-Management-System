package com.example.inventory;

import java.sql.Date;

public class Vendor {
    int id;
    String fname;
    String lname;
    String pnum;
    String email;
    String date;
    String contact;

    public Vendor(int id, String fname, String lname, String pnum, String email, Date date, String contact) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.pnum = pnum;
        this.email = email;
        this.date = String.valueOf(date);
        this.contact = contact;
    }

    // Getters and setters for the properties
    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPnum() { return pnum; }

    public void setPnum(String pnum) { this.pnum = pnum; }

    public String getEmail(){ return email; }

    public void setEmail(String email) { this.email = email; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }
}
