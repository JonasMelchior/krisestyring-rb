package com.example.application.service;

import com.example.application.entity.TwilioCredentials;

import java.util.List;
import java.util.Optional;

public interface ITwilioCredentialsService {

    List<TwilioCredentials> findAll();
    void save(TwilioCredentials twilioCredentials);
    Optional<TwilioCredentials> findById(Long id);
}
