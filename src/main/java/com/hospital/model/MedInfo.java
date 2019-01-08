package com.hospital.model;

import java.util.Date;

public class MedInfo {


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

    public MedInfo(){

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
    }

    public Date getMedEnd() {
        return medEnd;
    }

    public void setMedEnd(Date medEnd) {
        this.medEnd = medEnd;
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
}
