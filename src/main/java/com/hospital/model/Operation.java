package com.hospital.model;

public class Operation {

    private int operId;
    private String operName;
    private int operValue;
    private int operLength;
    private int doctorId;
    private String diagnosis;
    private boolean operDone;
    private String name;
    private String surname;
    private String patronymic;
    private String posName;

    public Operation(){

    }

    public Operation(Operation o){
        this.operId=o.operId;
        this.operName=o.operName;
        this.operValue=o.operValue;
        this.operLength=o.operLength;
        this.doctorId=o.doctorId;
        this.name=o.name;
        this.surname=o.surname;
        this.patronymic=o.patronymic;
        this.posName=o.posName;
        this.operDone=o.operDone;
        this.diagnosis=o.diagnosis;
    }

    public int getOperId() {
        return operId;
    }

    public void setOperId(int operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public int getOperValue() {
        return operValue;
    }

    public void setOperValue(int operValue) {
        this.operValue = operValue;
    }

    public int getOperLength() {
        return operLength;
    }

    public void setOperLength(int operLength) {
        this.operLength = operLength;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public boolean isOperDone() {
        return operDone;
    }

    public void setOperDone(boolean operDone) {
        this.operDone = operDone;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
