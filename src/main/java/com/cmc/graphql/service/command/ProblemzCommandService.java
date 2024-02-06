package com.cmc.graphql.service.command;

import com.cmc.graphql.datasource.entity.Problemz;
import com.cmc.graphql.datasource.repository.ProblemzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ProblemzCommandService {
    private Sinks.Many<Problemz> problemzSink = Sinks.many().multicast().onBackpressureBuffer();
    @Autowired
    private ProblemzRepository repository;

    public Problemz createProblem(Problemz p){
        var created = repository.save(p);

        problemzSink.tryEmitNext(created);

        return created;
    }

    public Flux<Problemz> problemzFlux(){
        return problemzSink.asFlux();
    }
}
