package com.example.application.service;

import com.example.application.entity.CrisisEvent;
import com.example.application.repository.CrisisEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrisisEventService implements ICrisisEventService{
    @Autowired
    private CrisisEventRepository repository;

    @Override
    public List<com.example.application.entity.CrisisEvent> findAll() {
        return (List<CrisisEvent>) repository.findAll();
    }

    @Override
    public void save(com.example.application.entity.CrisisEvent crisisEvent) {
        repository.save(crisisEvent);
    }

    @Override
    public void delete() {
        repository.deleteAll();
    }

    @Override
    public Optional<com.example.application.entity.CrisisEvent> find(Long id) {
        return repository.findById(id);
    }
}
