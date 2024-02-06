package com.cmc.graphql.datasource.repository;

import com.cmc.graphql.datasource.entity.Userz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserzRepository extends JpaRepository<Userz, UUID> {
    Optional<Userz> findByUsernameIgnoreCase(String username);
    Optional<Userz> findUserByToken(String authtoken);
}
