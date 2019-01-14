package com.hospital.model;

import java.util.Date;
import java.util.Objects;

public class MedInfo {


    private int dmId;
    private int diagId;
    private String diagName;
    private int medId;
    private String medName;
    private Date medStart;
    private Date medEnd;
    private boolean medDone;
    private int empId;
    private String empSurname;
    private String posName;
    private String medStartString;
    private String medEndString;

    public MedInfo(){
        medStartString=new String();
        medEndString=new String();

    }

    public int getDmId() {
        return dmId;
    }

    public void setDmId(int dmId) {
        this.dmId = dmId;
    }

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

    public Date getMedStart() {
        return medStart;
    }

    public void setMedStart(Date medStart) {

        this.medStart = medStart;
        if(medStart!=null) {
            String date = String.valueOf(medStart.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(medStart.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(medStart.getYear() + 1900);
            this.medStartString = date + "-" + month + "-" + year;
        }
    }

    public Date getMedEnd() {
        return medEnd;
    }

    public void setMedEnd(Date medEnd) {

        this.medEnd = medEnd;
        if(medEnd!=null) {
            String date = String.valueOf(medEnd.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(medEnd.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(medEnd.getYear() + 1900);
            this.medEndString = date + "-" + month + "-" + year;
        }
    }

    public boolean isMedDone() {
        return medDone;
    }

    public void setMedDone(boolean medDone) {
        this.medDone = medDone;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public String getMedStartString() {
        return medStartString;
    }

    public String getMedEndString() {
        return medEndString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedInfo)) return false;
        MedInfo medInfo = (MedInfo) o;
        return getDmId() == medInfo.getDmId() &&
                getDiagId() == medInfo.getDiagId() &&
                getMedId() == medInfo.getMedId() &&
                isMedDone() == medInfo.isMedDone() &&
                getEmpId() == medInfo.getEmpId() &&
                Objects.equals(getDiagName(), medInfo.getDiagName()) &&
                Objects.equals(getMedName(), medInfo.getMedName()) &&
                Objects.equals(getMedStart(), medInfo.getMedStart()) &&
                Objects.equals(getMedEnd(), medInfo.getMedEnd()) &&
                Objects.equals(getEmpSurname(), medInfo.getEmpSurname()) &&
                Objects.equals(getPosName(), medInfo.getPosName()) &&
                Objects.equals(getMedStartString(), medInfo.getMedStartString()) &&
                Objects.equals(getMedEndString(), medInfo.getMedEndString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDmId(), getDiagId(), getDiagName(), getMedId(), getMedName(), getMedStart(), getMedEnd(), isMedDone(), getEmpId(), getEmpSurname(), getPosName(), getMedStartString(), getMedEndString());
    }

    @Override
    public String toString() {
        return "MedInfo{" +
                "dmId=" + dmId +
                ", diagId=" + diagId +
                ", diagName='" + diagName + '\'' +
                ", medId=" + medId +
                ", medName='" + medName + '\'' +
                ", medStart=" + medStart +
                ", medEnd=" + medEnd +
                ", medDone=" + medDone +
                ", empId=" + empId +
                ", empSurname='" + empSurname + '\'' +
                ", posName='" + posName + '\'' +
                ", medStartString='" + medStartString + '\'' +
                ", medEndString='" + medEndString + '\'' +
                '}';
    }
}
