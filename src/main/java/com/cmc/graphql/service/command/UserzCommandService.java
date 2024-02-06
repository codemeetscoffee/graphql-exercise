package com.cmc.graphql.service.command;

import com.cmc.graphql.datasource.entity.UserzToken;
import com.cmc.graphql.datasource.repository.UserzRepository;
import com.cmc.graphql.datasource.repository.UserzTokenRepository;
import com.cmc.graphql.exception.ProblemzAuthenticationException;
import com.cmc.graphql.util.HashUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserzCommandService {
    @Autowired
    private UserzRepository userzRepository;

    @Autowired
    private UserzTokenRepository userzTokenRepository;

    public UserzToken login(String username, String password){
        var userzQueryResult = userzRepository.findByUsernameIgnoreCase(username);
        if (userzQueryResult.isEmpty() || !HashUtil.isBcryptMatch(password, userzQueryResult.get().getHashedPassword())){
//            throw new IllegalArgumentException("invalid credential");
            throw new ProblemzAuthenticationException();
        }
        var randomAuthToken = RandomStringUtils.randomAlphabetic(40);
        return refreshToken(userzQueryResult.get().getId(),randomAuthToken);
    }

    private UserzToken refreshToken(UUID userId, String authToken){
        var userzToken = new UserzToken();
        userzToken.setId(userId);
        userzToken.setAuthToken(authToken);

        var now = LocalDateTime.now();
        userzToken.setCreationTimestamp(now);
        userzToken.setExpiryTimestamp(now.plusHours(2));
         return userzTokenRepository.save(userzToken);
    }
}
