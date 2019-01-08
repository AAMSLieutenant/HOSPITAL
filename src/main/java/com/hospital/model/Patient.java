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

    public Patient(){
        pAddress=new Address();
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
    }

    public Date getpDischargeDate() {
        return pDischargeDate;
    }

    public void setpDischargeDate(Date pDischargeDate) {
        this.pDischargeDate = pDischargeDate;
    }

    public Address getpAddress() {
        return pAddress;
    }

    public void setpAddress(Address pAddress) {
        this.pAddress = new Address(pAddress);
    }




}
