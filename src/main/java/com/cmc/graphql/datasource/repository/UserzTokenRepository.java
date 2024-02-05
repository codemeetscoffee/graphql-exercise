package com.cmc.graphql.datasource.repository;

import com.cmc.graphql.datasource.entity.UserzToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserzTokenRepository extends JpaRepository<UserzToken, UUID> {
}
