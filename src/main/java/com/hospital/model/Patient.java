package com.hospital.model;

import java.util.Date;
import java.util.Objects;

import com.hospital.model.Address;

public class Patient {
    private int pCardId;
    private String pName;
    private String pSurname;
    private String pPatronymic;
    private int pAge;
    private String pSex;
    private Date pBirthDate;
    private Date pArrivalDate;
    private Date pDischargeDate;
    private Address pAddress;
    private String pBirthDateString;
    private String pArrivalDateString;
    private String pDischargeDateString;

    public Patient(){

        pAddress=new Address();
        pBirthDateString=new String();
        pArrivalDateString=new String();
        pDischargeDateString=new String();
    }

    public Patient(int pCardId,
                   String pName,
                   String pSurname,
                   String pPatronymic,
                   int pAge,
                   String pSex,
                   Date pBirthDate,
                   Date pArrivalDate,
                   Address pAddress) {
        this.pCardId = pCardId;
        this.pName = pName;
        this.pSurname = pSurname;
        this.pPatronymic = pPatronymic;
        this.pAge = pAge;
        this.pSex = pSex;
        this.pBirthDate=pBirthDate;
        this.pArrivalDate=pArrivalDate;
        this.pAddress=new Address(pAddress);
    }
    public Patient(Patient p){
        this.pCardId=p.pCardId;
        this.pName=p.pName;
        this.pSurname=p.pSurname;
        this.pAge=p.pAge;
        this.pSex=p.pSex;
        this.pBirthDate=p.pBirthDate;
        this.pArrivalDate=p.pArrivalDate;
        this.pAddress=new Address(p.pAddress);
    }





    public int getpCardId() {
        return pCardId;
    }

    public void setpCardId(int pCardId) {
        this.pCardId = pCardId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSurname() {
        return pSurname;
    }

    public void setpSurname(String pSurname) {
        this.pSurname = pSurname;
    }

    public int getpAge() {
        return pAge;
    }

    public void setpAge(int pAge) {
        this.pAge = pAge;
    }

    public String getpPatronymic() {
        return pPatronymic;
    }

    public void setpPatronymic(String pPatronymic) {
        this.pPatronymic = pPatronymic;
    }


    public Date getpBirthDate() {
        return pBirthDate;
    }

    public void setpBirthDate(Date pBirthDate) {

        this.pBirthDate = pBirthDate;
        if(pBirthDate!=null) {
            String date = String.valueOf(pBirthDate.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(pBirthDate.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(pBirthDate.getYear() + 1900);
            this.pBirthDateString = date + "-" + month + "-" + year;
        }
    }

    public String getpSex() {
        return pSex;
    }

    public void setpSex(String pSex) {
        this.pSex = pSex;
    }

    public Date getpArrivalDate() {
        return pArrivalDate;
    }

    public void setpArrivalDate(Date pArrivalDate) {

        this.pArrivalDate = pArrivalDate;
        if(pArrivalDate!=null) {
            String date = String.valueOf(pArrivalDate.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(pArrivalDate.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(pArrivalDate.getYear() + 1900);
            this.pArrivalDateString = date + "-" + month + "-" + year;
        }
    }

    public Date getpDischargeDate() {
        return pDischargeDate;
    }

    public void setpDischargeDate(Date pDischargeDate) {

        this.pDischargeDate = pDischargeDate;
        if(pDischargeDate!=null) {
            String date = String.valueOf(pDischargeDate.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(pDischargeDate.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(pDischargeDate.getYear() + 1900);
            this.pDischargeDateString = date + "-" + month + "-" + year;
        }
    }

    public Address getpAddress() {
        return pAddress;
    }

    public void setpAddress(Address pAddress) {
        this.pAddress = new Address(pAddress);
    }

    public String getpBirthDateString(){return this.pBirthDateString;}
    public String getpArrivalDateString(){return this.pArrivalDateString;}
    public String getpDischargeDateString(){return this.pDischargeDateString;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getpCardId() == patient.getpCardId() &&
                getpAge() == patient.getpAge() &&
                Objects.equals(getpName(), patient.getpName()) &&
                Objects.equals(getpSurname(), patient.getpSurname()) &&
                Objects.equals(getpPatronymic(), patient.getpPatronymic()) &&
                Objects.equals(getpSex(), patient.getpSex()) &&
                Objects.equals(getpBirthDate(), patient.getpBirthDate()) &&
                Objects.equals(getpArrivalDate(), patient.getpArrivalDate()) &&
                Objects.equals(getpDischargeDate(), patient.getpDischargeDate()) &&
                Objects.equals(getpAddress(), patient.getpAddress()) &&
                Objects.equals(getpBirthDateString(), patient.getpBirthDateString()) &&
                Objects.equals(getpArrivalDateString(), patient.getpArrivalDateString()) &&
                Objects.equals(getpDischargeDateString(), patient.getpDischargeDateString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getpCardId(), getpName(), getpSurname(), getpPatronymic(), getpAge(), getpSex(), getpBirthDate(), getpArrivalDate(), getpDischargeDate(), getpAddress(), getpBirthDateString(), getpArrivalDateString(), getpDischargeDateString());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pCardId=" + pCardId +
                ", pName='" + pName + '\'' +
                ", pSurname='" + pSurname + '\'' +
                ", pPatronymic='" + pPatronymic + '\'' +
                ", pAge=" + pAge +
                ", pSex='" + pSex + '\'' +
                ", pBirthDate=" + pBirthDate +
                ", pArrivalDate=" + pArrivalDate +
                ", pDischargeDate=" + pDischargeDate +
                ", pAddress=" + pAddress +
                ", pBirthDateString='" + pBirthDateString + '\'' +
                ", pArrivalDateString='" + pArrivalDateString + '\'' +
                ", pDischargeDateString='" + pDischargeDateString + '\'' +
                '}';
    }
}
