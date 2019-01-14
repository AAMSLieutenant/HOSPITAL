package com.hospital.model;

import java.util.Objects;

public class Procedure {

    private int procId;
    private String procName;
    private int procValue;
    private int empId;
    private String name;
    private String surname;
    private String patronymic;
    private String posName;

    public Procedure(){

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

    public int getProcValue() {
        return procValue;
    }

    public void setProcValue(int procValue) {
        this.procValue = procValue;
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
        if (!(o instanceof Procedure)) return false;
        Procedure procedure = (Procedure) o;
        return getProcId() == procedure.getProcId() &&
                getProcValue() == procedure.getProcValue() &&
                getEmpId() == procedure.getEmpId() &&
                Objects.equals(getProcName(), procedure.getProcName()) &&
                Objects.equals(getName(), procedure.getName()) &&
                Objects.equals(getSurname(), procedure.getSurname()) &&
                Objects.equals(getPatronymic(), procedure.getPatronymic()) &&
                Objects.equals(getPosName(), procedure.getPosName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProcId(), getProcName(), getProcValue(), getEmpId(), getName(), getSurname(), getPatronymic(), getPosName());
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "procId=" + procId +
                ", procName='" + procName + '\'' +
                ", procValue=" + procValue +
                ", empId=" + empId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", posName='" + posName + '\'' +
                '}';
    }
}
