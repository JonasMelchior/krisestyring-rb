package com.example.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TwilioCredentials {

    @Id
    @GeneratedValue
    private Long id;
    private String accountSid;
    private String authToken;

    public TwilioCredentials() {
    }

    public TwilioCredentials(String accountSid, String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
