package com.ms.email_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Email {

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column("message")
    private String message;

    @Column("destination")
    private String destination;

    @Column("date_submission")
    private LocalDateTime dateSubmission;
}
