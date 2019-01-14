package com.hospital.model;

import java.util.Date;
import java.util.Objects;

public class Appointment {

    private int appId;
    private Date appDate;
    private int appValue;
    private String appComplaint;
    private int docId;
    private int cardId;
    private String appDateString;

    public Appointment(){
        this.appDateString=new String();

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
        if(appDate!=null) {
            String date = String.valueOf(appDate.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(appDate.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(appDate.getYear() + 1900);
            this.appDateString = date + "-" + month + "-" + year;
        }
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

    public String getAppDateString(){
        return this.appDateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return getAppId() == that.getAppId() &&
                getAppValue() == that.getAppValue() &&
                getDocId() == that.getDocId() &&
                getCardId() == that.getCardId() &&
                Objects.equals(getAppDate(), that.getAppDate()) &&
                Objects.equals(getAppComplaint(), that.getAppComplaint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAppId(), getAppDate(), getAppValue(), getAppComplaint(), getDocId(), getCardId());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appId=" + appId +
                ", appDate=" + appDate +
                ", appValue=" + appValue +
                ", appComplaint='" + appComplaint + '\'' +
                ", docId=" + docId +
                ", cardId=" + cardId +
                '}';
    }
}
