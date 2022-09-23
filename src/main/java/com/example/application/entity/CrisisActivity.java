package com.example.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class CrisisActivity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String responsible;
    private String description;
    private String assignedVolunteers;
    private int longitude;
    private int latitude;

    public CrisisActivity() {
    }

    public CrisisActivity(String name, String responsible, String description, String assignedVolunteers, int longitude, int latitude) {
        this.name = name;
        this.responsible = responsible;
        this.description = description;
        this.assignedVolunteers = assignedVolunteers;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public CrisisActivity(String name, String responsible, String description) {
        this.name = name;
        this.responsible = responsible;
        this.description = description;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedVolunteers() {
        return assignedVolunteers;
    }

    public void setAssignedVolunteers(String assignedVolunteers) {
        this.assignedVolunteers = assignedVolunteers;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
