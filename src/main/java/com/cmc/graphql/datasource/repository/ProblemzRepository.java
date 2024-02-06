package com.cmc.graphql.datasource.repository;

import com.cmc.graphql.datasource.entity.Problemz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemzRepository extends JpaRepository<Problemz, UUID> {
    List<Problemz> findAllByOrderByCreationTimestampDesc();

    @Query(nativeQuery = true, value = "select * from problemz p" +
            "where upper(content) like upper(:keyword) " +
            "or upper(title) like upper(:keyword) " +
            "or upper(tags) like upper(:keyword)")
    List<Problemz> findByKeyword(@Param("keyword") String keyword);
}
