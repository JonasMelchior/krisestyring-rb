package com.example.application.service;

import com.example.application.entity.CrisisEvent;
import com.example.application.entity.User;

import java.util.List;
import java.util.Optional;

public interface ICrisisEventService {
    List<CrisisEvent> findAll();
    void save(CrisisEvent crisisEvent);
    void delete();
    Optional<CrisisEvent> find(Long id);
}
