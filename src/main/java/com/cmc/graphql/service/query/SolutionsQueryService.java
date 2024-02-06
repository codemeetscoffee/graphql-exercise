package com.cmc.graphql.service.query;

import com.cmc.graphql.datasource.entity.Solutionz;
import com.cmc.graphql.datasource.repository.SolutionzRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionsQueryService {
    private final SolutionzRepository solutionzRepository;

    public SolutionsQueryService(SolutionzRepository solutionzRepository) {
        this.solutionzRepository = solutionzRepository;
    }

    public List<Solutionz> solutionzByKeyword(String keyword){
        return solutionzRepository.findByKeyword("%" + keyword + "%");
    }
}
