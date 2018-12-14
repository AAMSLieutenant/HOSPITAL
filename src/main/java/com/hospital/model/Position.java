package com.hospital.model;

import java.math.BigInteger;
import java.util.Objects;

public class Position {
    private int posId;
    private String posName;
    private BigInteger posSalary;
    private boolean idMd;

    public Position(int posId, String posName, BigInteger posSalary, boolean idMd) {
        this.posId = posId;
        this.posName = posName;
        this.posSalary = posSalary;
        this.idMd = idMd;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public BigInteger getPosSalary() {
        return posSalary;
    }

    public void setPosSalary(BigInteger posSalary) {
        this.posSalary = posSalary;
    }

    public boolean isIdMd() {
        return idMd;
    }

    public void setIdMd(boolean idMd) {
        this.idMd = idMd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return posId == position.posId &&
                idMd == position.idMd &&
                Objects.equals(posName, position.posName) &&
                Objects.equals(posSalary, position.posSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posId, posName, posSalary, idMd);
    }

    @Override
    public String toString() {
        return "Position{" +
                "posId=" + posId +
                ", posName='" + posName + '\'' +
                ", posSalary=" + posSalary +
                ", idMd=" + idMd +
                '}';
    }
}
