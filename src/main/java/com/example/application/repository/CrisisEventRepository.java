package com.example.application.repository;

import com.example.application.entity.CrisisEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrisisEventRepository extends CrudRepository<CrisisEvent, Long> {}
