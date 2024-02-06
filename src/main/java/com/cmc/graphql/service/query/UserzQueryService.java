package com.cmc.graphql.service.query;

import com.cmc.graphql.datasource.entity.Userz;
import com.cmc.graphql.datasource.repository.UserzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserzQueryService {
    @Autowired
    private UserzRepository userzRepository;
    public Optional<Userz> findUserzByAuthToken(String authToken){
        return userzRepository.findUserByToken(authToken);
    }
}
