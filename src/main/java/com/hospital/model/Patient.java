package com.hospital.model;

import java.util.Date;
import java.util.Objects;

import com.hospital.model.Address;

public class Patient {
    private int pCardId;
    private String pName;
    private String pSurname;
    private String pPatronymic;

    public Patient(int pCardId,
                   String pName,
                   String pSurname,
                   String pPatronymic,
                   String pSex) {
        this.pCardId = pCardId;
        this.pName = pName;
        this.pSurname = pSurname;
        this.pPatronymic = pPatronymic;
        this.pSex = pSex;
    }
    public Patient(Patient p){
        this.pCardId=p.pCardId;
        this.pName=p.pName;
        this.pSurname=p.pSurname;
    }

    private Date pBirthDate;
    private String pSex;
    private Date pArrivalDate;
    private Date pDischargeDate;
    private Address pAddress;



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
        this.pAddress = pAddress;
    }




}
