package com.hospital.model;

import java.util.Date;
import java.util.Objects;

public class ProcInfo {


    private int dpId;
    private int diagId;
    private String diagName;
    private int procId;
    private String procName;
    private Date procDate;
    private boolean procDone;
    private int empId;
    private String empSurname;
    private String posName;
    private String procDateString;

    public ProcInfo(){
        this.procDateString=new String();

    }

    public int getDpId(){
        return dpId;
    }

    public void setDpId(int dpId){
        this.dpId=dpId;
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
        if(procDate!=null) {
            String date = String.valueOf(procDate.getDate());
            if(date.length()==1){
                date="0"+date;
            }
            String month = String.valueOf(procDate.getMonth() + 1);
            if(month.length()==1){
                month="0"+month;
            }
            String year = String.valueOf(procDate.getYear() + 1900);
            this.procDateString = date + "-" + month + "-" + year;
        }
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

    public String getProcDateString() {
        return procDateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcInfo)) return false;
        ProcInfo procInfo = (ProcInfo) o;
        return getDpId() == procInfo.getDpId() &&
                getDiagId() == procInfo.getDiagId() &&
                getProcId() == procInfo.getProcId() &&
                isProcDone() == procInfo.isProcDone() &&
                getEmpId() == procInfo.getEmpId() &&
                Objects.equals(getDiagName(), procInfo.getDiagName()) &&
                Objects.equals(getProcName(), procInfo.getProcName()) &&
                Objects.equals(getProcDate(), procInfo.getProcDate()) &&
                Objects.equals(getEmpSurname(), procInfo.getEmpSurname()) &&
                Objects.equals(getPosName(), procInfo.getPosName()) &&
                Objects.equals(getProcDateString(), procInfo.getProcDateString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDpId(), getDiagId(), getDiagName(), getProcId(), getProcName(), getProcDate(), isProcDone(), getEmpId(), getEmpSurname(), getPosName(), getProcDateString());
    }

    @Override
    public String toString() {
        return "ProcInfo{" +
                "dpId=" + dpId +
                ", diagId=" + diagId +
                ", diagName='" + diagName + '\'' +
                ", procId=" + procId +
                ", procName='" + procName + '\'' +
                ", procDate=" + procDate +
                ", procDone=" + procDone +
                ", empId=" + empId +
                ", empSurname='" + empSurname + '\'' +
                ", posName='" + posName + '\'' +
                ", procDateString='" + procDateString + '\'' +
                '}';
    }
}
