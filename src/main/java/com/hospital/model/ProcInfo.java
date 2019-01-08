package com.hospital.model;

import java.util.Date;

public class ProcInfo {


    private int diagId;
    private String diagName;
    private int procId;
    private String procName;
    private Date procDate;
    private boolean procDone;
    private int empId;
    private String empSurname;
    private String posName;

    public ProcInfo(){

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

    public int getProcId() {
        return procId;
    }

    public void setProcId(int procId) {
        this.procId = procId;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    public boolean isProcDone() {
        return procDone;
    }

    public void setProcDone(boolean procDone) {
        this.procDone = procDone;
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
