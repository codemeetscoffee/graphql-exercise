package com.cmc.graphql.service;

import com.cmc.graphql.datasource.entity.Problemz;
import com.cmc.graphql.datasource.repository.ProblemzRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemQueryService {
    private final ProblemzRepository problemzRepository;

    public ProblemQueryService(ProblemzRepository problemzRepository) {
        this.problemzRepository = problemzRepository;
    }

    public List<Problemz> problemzLatestList(){
        var problemz = problemzRepository.findAllByOrderByCreationTimestampDesc();
//        problemz.forEach(p -> p.getSolutions().sort(Comparator.comparing(Solutionz::getCreationTimestamp).reversed()));
        return problemz;
    }

    public Optional<Problemz> problemzDetail(UUID problemzId){
        return problemzRepository.findById(problemzId);
    }

    public List<Problemz> problemzByKeyword(String keyword){
        return problemzRepository.findByKeyword("%" + keyword + "%");
    }
}
