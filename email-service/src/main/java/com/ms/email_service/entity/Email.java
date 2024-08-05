package com.ms.email_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Email {

    @PrimaryKey
    private String id;

    private String message;

    private String destination;

    private LocalDateTime dateSubmission;
}
