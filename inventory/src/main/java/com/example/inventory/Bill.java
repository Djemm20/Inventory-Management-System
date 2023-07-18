package com.example.inventory;

import java.sql.Date;

public class Bill {
    int id;
    String bill;
    String amt;
    String billDate;

    public Bill(int id, String bill, String amt, Date billDate) {
        this.id = id;
        this.bill = bill;
        this.amt = amt;
        this.billDate = String.valueOf(billDate);
    }

    // Getters and setters for the properties
    public int getId() {
        return id;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) { this.amt = amt; }

    public String getBillDate() { return billDate; }

    public void setBillDate(String billDate) { this.billDate = billDate; }
}
