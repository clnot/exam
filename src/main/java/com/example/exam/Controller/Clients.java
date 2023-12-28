package com.example.exam.Controller;

public class Clients {
    String surname, name, lastname, abonement, dateC, dateD;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAbonement() {
        return abonement;
    }

    public void setAbonement(String abonement) {
        this.abonement = abonement;
    }

    public String getDateC() {
        return dateC;
    }

    public void setDateC(String dateC) {
        this.dateC = dateC;
    }

    public String getDateD() {
        return dateD;
    }

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }

    public Clients(String surname, String name, String lastname, String abonement, String dateC, String dateD) {
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.abonement = abonement;
        this.dateC = dateC;
        this.dateD = dateD;
    }
}
