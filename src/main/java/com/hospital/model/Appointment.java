package com.hospital.model;

import java.util.Date;

public class Appointment {

    private int appId;
    private Date appDate;
    private int appValue;
    private String appCompliant;
    private int cardId;



    private int diagId;

    public Appointment(){

    }

    public Appointment(
            int appId,
            Date appDate,
            int appValue,
            String appCompliant,
            int cardId,
            int diagId){

        this.appId=appId;
        this.appDate=appDate;
        this.appValue=appValue;
        this.appCompliant=appCompliant;
        this.cardId=cardId;
        this.diagId=diagId;

    }

    public Appointment(Appointment a){
        this.appId=a.appId;
        this.appDate=a.appDate;
        this.appValue=a.appValue;
        this.appCompliant=a.appCompliant;
        this.cardId=a.cardId;
        this.diagId=a.diagId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public int getAppValue() {
        return appValue;
    }

    public void setAppValue(int appValue) {
        this.appValue = appValue;
    }

    public String getAppCompliant() {
        return appCompliant;
    }

    public void setAppCompliant(String appCompliant) {
        this.appCompliant = appCompliant;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getDiagId() {
        return diagId;
    }

    public void setDiagId(int diagId) {
        this.diagId = diagId;
    }

}
