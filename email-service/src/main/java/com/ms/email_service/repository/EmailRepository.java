package com.ms.email_service.repository;

import com.ms.email_service.entity.Email;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CassandraRepository<Email, String> {

}
