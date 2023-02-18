package com.example.application.service;


import com.example.application.entity.User;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return (List<User>) repository.findAllById(ids);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return repository.findById(id);
    }


}
