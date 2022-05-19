package com.example.hermes;

import android.os.Build;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Delivery {
    private int ID;
    private Date date;
    private String customerID; //Change later to customer object
    private boolean isReady;
    private boolean isDone;
    private ArrayList<String> items;

    public Delivery(String customerID){
        this.customerID = customerID;
        Date date = new Date();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = date;
        }
        this.ID = idGenerator(customerID, date);
        this.isReady = false;
        this.isDone = false;
        this.items= new ArrayList<>();
    }

    public Delivery(String customerID, String date) throws ParseException {

        this.customerID = customerID;
        Date d = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.CHINA).parse(date);
        this.date = d;
        this.ID = idGenerator(customerID, d);
        this.items= new ArrayList<>();
    }

    public String getCustomerID(){
        return customerID;
    }

    public void setCustomerID(String ID){
        if (customerID == null)
            this.customerID = ID;
    }

    public String getDate(){
        String[] split = date.toString().split(" ");
        return split[5] + " " + split[1] + " " + split[2];
    }

    public String getTime() {
        String[] split = date.toString().split(" ");
        return split[3];
    }

    public void setDate(String yyyymmdd_hhmmss) throws ParseException {
        date = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.CHINA).parse(yyyymmdd_hhmmss);
    }

    public void setDate(){
        date = new Date();
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void addItem(String ItemName){
        items.add(ItemName);
    }
    public ArrayList <String> getItems(){
        return  items;
    }

    public boolean getReady() { return isReady; }

    public void setReady(boolean value) { this.isReady = value; }

    public boolean getDone() { return this.isDone; }

    public void setDone(boolean value) { this.isDone = value; }

    public int idGenerator(String customerID, Date date){
        return customerID.hashCode() * date.hashCode();
    }

    public int getId(){
        return this.ID;
    }

    @NonNull
    @Override
    public String toString(){
        return this.customerID + " " + this.date;
    }

    public static final Comparator<Delivery> byOldest = Comparator.comparing(delivery -> delivery.date);

    public static final Comparator<Delivery> byNewest = (delivery1, delivery2) -> delivery2.date.compareTo(delivery1.date);

    public static final Comparator<Delivery> byName = (delivery1, delivery2) -> delivery1.customerID.compareToIgnoreCase(delivery2.customerID);

    public static final Comparator<Delivery> byNameReverse = (delivery1, delivery2) -> delivery2.customerID.compareToIgnoreCase(delivery1.customerID);
}