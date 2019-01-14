package com.hospital.model;

import java.util.Date;
import java.util.Objects;

public class OperInfo {


    private int doId;
    private int diagId;
    private String diagName;
    private int operId;
    private String operName;
    private Date operDate;
    private boolean operDone;
    private int doctorId;
    private String empSurname;
    private String posName;
    private String operDateString;

    public OperInfo(){
        operDateString=new String();

    }

    public int getDoId() { return doId; }

    public void setDoId(int doId) { this.doId=doId; }

    public int getDiagId() {
        return diagId;
    }

    public void setDiagId(int diagId) {
        this.diagId = diagId;
    }

    public String getDiagName() {
        return diagName;
    }

    public void setDiagName(String diagName) {
        this.diagName = diagName;
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

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {

        this.operDate = operDate;
        if(operDate!=null) {
            String date = String.valueOf(operDate.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(operDate.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(operDate.getYear() + 1900);
            this.operDateString = date + "-" + month + "-" + year;
        }
    }

    public boolean isOperDone() {
        return operDone;
    }

    public void setOperDone(boolean operDone) {
        this.operDone = operDone;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getOperDateString(){
        return this.operDateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperInfo)) return false;
        OperInfo operInfo = (OperInfo) o;
        return getDoId() == operInfo.getDoId() &&
                getDiagId() == operInfo.getDiagId() &&
                getOperId() == operInfo.getOperId() &&
                isOperDone() == operInfo.isOperDone() &&
                getDoctorId() == operInfo.getDoctorId() &&
                Objects.equals(getDiagName(), operInfo.getDiagName()) &&
                Objects.equals(getOperName(), operInfo.getOperName()) &&
                Objects.equals(getOperDate(), operInfo.getOperDate()) &&
                Objects.equals(getEmpSurname(), operInfo.getEmpSurname()) &&
                Objects.equals(getPosName(), operInfo.getPosName()) &&
                Objects.equals(getOperDateString(), operInfo.getOperDateString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDoId(), getDiagId(), getDiagName(), getOperId(), getOperName(), getOperDate(), isOperDone(), getDoctorId(), getEmpSurname(), getPosName(), getOperDateString());
    }

    @Override
    public String toString() {
        return "OperInfo{" +
                "doId=" + doId +
                ", diagId=" + diagId +
                ", diagName='" + diagName + '\'' +
                ", operId=" + operId +
                ", operName='" + operName + '\'' +
                ", operDate=" + operDate +
                ", operDone=" + operDone +
                ", doctorId=" + doctorId +
                ", empSurname='" + empSurname + '\'' +
                ", posName='" + posName + '\'' +
                ", operDateString='" + operDateString + '\'' +
                '}';
    }
}
