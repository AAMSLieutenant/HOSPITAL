package com.hospital.model;

import java.util.Objects;

public class Diagnosis {

    private int diagId;
    private String diagName;
    private int cardId;

    public Diagnosis(){

    }

    public Diagnosis(Diagnosis d){
        this.diagId=d.diagId;
        this.diagName=d.diagName;
        this.cardId=d.cardId;

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

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "diagId=" + diagId +
                ", diagName='" + diagName + '\'' +
                ", cardId=" + cardId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diagnosis)) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return getDiagId() == diagnosis.getDiagId() &&
                getCardId() == diagnosis.getCardId() &&
                Objects.equals(getDiagName(), diagnosis.getDiagName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiagId(), getDiagName(), getCardId());
    }
}
