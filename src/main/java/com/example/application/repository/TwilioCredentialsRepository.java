package com.example.application.repository;

import com.example.application.entity.TwilioCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface TwilioCredentialsRepository extends CrudRepository<TwilioCredentials, Long> {
}
