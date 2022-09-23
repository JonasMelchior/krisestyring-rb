package com.example.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class CrisisEvent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private LocalDateTime meetingDate;
    private String availableVolunteers;


    public CrisisEvent() {
    }

    public CrisisEvent(String name, String description, LocalDateTime meetingDate) {
        this.name = name;
        this.description = description;
        this.meetingDate = meetingDate;
    }

    public CrisisEvent(String name, String description, LocalDateTime meetingDate, String availableVolunteers) {
        this.name = name;
        this.description = description;
        this.meetingDate = meetingDate;
        this.availableVolunteers = availableVolunteers;
    }

    public String getAvailableVolunteers() {
        return availableVolunteers;
    }

    public void setAvailableVolunteers(String availableVolunteers) {
        this.availableVolunteers = availableVolunteers;
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

    public LocalDateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDateTime meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
