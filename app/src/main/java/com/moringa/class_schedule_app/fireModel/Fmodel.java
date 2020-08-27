package com.moringa.class_schedule_app.fireModel;

public class Fmodel {
    private String name,password,email,cohort;

    public Fmodel() {
    }

    public Fmodel(String name, String password, String email, String cohort) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.cohort = cohort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }
}
