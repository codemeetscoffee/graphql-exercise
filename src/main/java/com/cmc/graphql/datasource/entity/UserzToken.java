package com.cmc.graphql.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserzToken {
    @Id
    private UUID id;
    private String authToken;
    @CreationTimestamp
    private LocalDateTime creationTimestamp;
    private LocalDateTime expiryTimestamp;
}
