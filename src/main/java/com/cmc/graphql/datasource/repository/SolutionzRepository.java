package com.cmc.graphql.datasource.repository;

import com.cmc.graphql.datasource.entity.Solutionz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SolutionzRepository extends JpaRepository<Solutionz, UUID> {
}
