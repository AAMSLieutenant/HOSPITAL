package com.hospital.model;

import java.util.Objects;

public class Uuser {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Uuser(){

    }

    public Uuser(String login, String password){
        this.login=login;
        this.password=password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uuser)) return false;
        Uuser uuser = (Uuser) o;
        return Objects.equals(getLogin(), uuser.getLogin()) &&
                Objects.equals(getPassword(), uuser.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "Uuser{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
