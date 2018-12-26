package com.hospital.model;

import java.util.Date;
import com.hospital.model.Position;

public class Employee {

    private int empId;
    private String empName;
    private String empSurname;
    private String empPatronymic;
    private String PosName;

    public Employee(){

    }

    public Employee(int empId, String empName, String empSurname, String empPatronymic, String PosName) {
        this.empId = empId;
        this.empName = empName;
        this.empSurname = empSurname;
        this.empPatronymic = empPatronymic;
        this.PosName = PosName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    public String getEmpPatronymic() {
        return empPatronymic;
    }

    public void setEmpPatronymic(String empPatronymic) {
        this.empPatronymic = empPatronymic;
    }

    public String getPosName() {
        return PosName;
    }

    public void setPosName(String empPosName) {
        this.PosName = empPosName;
    }
}
