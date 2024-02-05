package com.cmc.graphql.datasource.repository;

import com.cmc.graphql.datasource.entity.Problemz;
import com.course.graphql.types.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemzRepository extends JpaRepository<Problemz, UUID> {
}
