package com.hospital.model;

import java.util.Date;
import java.util.Objects;

import com.hospital.model.Address;

public class Patient {
    private int cardId;
    private String pName;
    private String pSurname;
    private String pPatronymic;

    public Patient(int cardId,
                   String pName,
                   String pSurname,
                   String pPatronymic,
                   String pSex) {
        this.cardId = cardId;
        this.pName = pName;
        this.pSurname = pSurname;
        this.pPatronymic = pPatronymic;
        this.pSex = pSex;
    }

    private Date pBirthDate;
    private String pSex;
    private Date pArrivalDate;
    private Date pDischargeDate;
    private Address pAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getCardId() == patient.getCardId() &&
                Objects.equals(getpName(), patient.getpName()) &&
                Objects.equals(getpSurname(), patient.getpSurname()) &&
                Objects.equals(getpPatronymic(), patient.getpPatronymic()) &&
                Objects.equals(getpBirthDate(), patient.getpBirthDate()) &&
                Objects.equals(getpSex(), patient.getpSex()) &&
                Objects.equals(getpArrivalDate(), patient.getpArrivalDate()) &&
                Objects.equals(getpDischargeDate(), patient.getpDischargeDate()) &&
                Objects.equals(getpAddress(), patient.getpAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getpName(), getpSurname(), getpPatronymic(), getpBirthDate(), getpSex(), getpArrivalDate(), getpDischargeDate(), getpAddress());
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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
