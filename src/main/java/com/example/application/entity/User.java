package com.example.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Double cprNumber;
    private String registeredThrough;
    private String competences;
    private boolean physicalWork;


    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, double cprNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, Double cprNumber, String registeredThrough) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
        this.registeredThrough = registeredThrough;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, Double cprNumber, String registeredThrough, String competences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
        this.registeredThrough = registeredThrough;
        this.competences = competences;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, Double cprNumber, String registeredThrough, String competences, boolean physicalWork) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
        this.registeredThrough = registeredThrough;
        this.competences = competences;
        this.physicalWork = physicalWork;
    }

    public boolean isPhysicalWork() {
        return physicalWork;
    }

    public void setPhysicalWork(boolean physicalWork) {
        this.physicalWork = physicalWork;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getRegisteredThrough() {
        return registeredThrough;
    }

    public void setRegisteredThrough(String registeredThrough) {
        this.registeredThrough = registeredThrough;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCprNumber() {
        return cprNumber.longValue();
    }

    public void setCprNumber(Double cprNumber) {
        this.cprNumber = cprNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
