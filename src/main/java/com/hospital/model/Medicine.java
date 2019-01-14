package com.hospital.model;

import java.util.Date;
import java.util.Objects;

public class Medicine {


    private int medId;
    private String medName;
    private int medValue;
    private int empId;
    private String name;
    private String surname;
    private String patronymic;
    private String posName;

    public Medicine(){

    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getMedValue() {
        return medValue;
    }

    public void setMedValue(int medValue) {
        this.medValue = medValue;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;
        Medicine medicine = (Medicine) o;
        return getMedId() == medicine.getMedId() &&
                getMedValue() == medicine.getMedValue() &&
                getEmpId() == medicine.getEmpId() &&
                Objects.equals(getMedName(), medicine.getMedName()) &&
                Objects.equals(getName(), medicine.getName()) &&
                Objects.equals(getSurname(), medicine.getSurname()) &&
                Objects.equals(getPatronymic(), medicine.getPatronymic()) &&
                Objects.equals(getPosName(), medicine.getPosName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMedId(), getMedName(), getMedValue(), getEmpId(), getName(), getSurname(), getPatronymic(), getPosName());
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medId=" + medId +
                ", medName='" + medName + '\'' +
                ", medValue=" + medValue +
                ", empId=" + empId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", posName='" + posName + '\'' +
                '}';
    }
}
