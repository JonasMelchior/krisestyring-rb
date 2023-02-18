package com.example.application.service;


import com.example.application.entity.TwilioCredentials;
import com.example.application.repository.TwilioCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TwilioCredentialsService implements ITwilioCredentialsService{

    @Autowired
    private TwilioCredentialsRepository repository;

    @Override
    public List<TwilioCredentials> findAll() {
        return (List<TwilioCredentials>) repository.findAll();
    }

    @Override
    public void save(TwilioCredentials twilioCredentials) {
        repository.save(twilioCredentials);
    }

    @Override
    public Optional<TwilioCredentials> findById(Long id) {
        return repository.findById(id);
    }
}
