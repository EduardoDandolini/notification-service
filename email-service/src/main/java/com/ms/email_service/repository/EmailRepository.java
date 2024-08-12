package com.ms.email_service.repository;

import com.ms.email_service.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

}
