package com.hospital.model;

import java.util.Date;

public class Appointment {

    private int appId;
    private Date appDate;
    private int appValue;
    private String appComplaint;
    private int docId;
    private int cardId;

    public Appointment(){

    }

    public Appointment(
            int appId,
            Date appDate,
            int appValue,
            String appComplaint,
            int docId,
            int cardId){

        this.appId=appId;
        this.appDate=appDate;
        this.appValue=appValue;
        this.appComplaint=appComplaint;
        this.docId=docId;
        this.cardId=cardId;
    }

    public Appointment(Appointment a){
        this.appId=a.appId;
        this.appDate=a.appDate;
        this.appValue=a.appValue;
        this.appComplaint=a.appComplaint;
        this.docId=a.docId;
        this.cardId=a.cardId;
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

    public String getAppComplaint() {
        return appComplaint;
    }

    public void setAppComplaint(String appComplaint) {
        this.appComplaint = appComplaint;
    }

    public int getDocId() { return docId; }

    public void setDocId(int docId) { this.docId = docId; }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

}
