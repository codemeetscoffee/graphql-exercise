package com.cmc.graphql.service.command;

import com.cmc.graphql.datasource.entity.Solutionz;
import com.cmc.graphql.datasource.repository.SolutionzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionzCommandService {
    private Sinks.Many<Solutionz> solutionzSinks = Sinks.many().multicast().onBackpressureBuffer();
    @Autowired
    private SolutionzRepository repository;

    public Solutionz createSolutionz(Solutionz solutionz){
        return repository.save(solutionz);
    }

    public Optional<Solutionz> voteBad(UUID solutionId){
        repository.addVoteBadCount(solutionId);
        var updated = repository.findById(solutionId);
        updated.ifPresent(solutionz -> solutionzSinks.tryEmitNext(solutionz));
        return updated;
    }

    public Optional<Solutionz> voteGood(UUID solutionId){
        repository.addVoteGoodCount(solutionId);
        var updated = repository.findById(solutionId);
        updated.ifPresent(solutionz -> solutionzSinks.tryEmitNext(solutionz));
        return updated;
    }

    public Flux<Solutionz> solutionzFlux(){
        return solutionzSinks.asFlux();
    }
}
