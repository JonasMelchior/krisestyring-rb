package com.example.application.service;

import com.example.application.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    List<User> findAllById(List<Long> ids);
    void save(User user);
    void saveAll(List<User> users);
    void delete(User user);
    Optional<User> find(Long id);

}
