package com.hospital.model;

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
}
