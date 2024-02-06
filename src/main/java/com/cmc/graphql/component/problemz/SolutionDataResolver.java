package com.cmc.graphql.component.problemz;

import com.cmc.graphql.datasource.entity.Solutionz;
import com.cmc.graphql.exception.ProblemzAuthenticationException;
import com.cmc.graphql.service.ProblemQueryService;
import com.cmc.graphql.service.command.SolutionzCommandService;
import com.cmc.graphql.service.query.UserzQueryService;
import com.cmc.graphql.util.GraphQLBeanMapper;
import com.course.graphql.DgsConstants;
import com.course.graphql.types.Solution;
import com.course.graphql.types.SolutionCreateInput;
import com.course.graphql.types.SolutionResponse;
import com.course.graphql.types.SolutionVoteInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

@DgsComponent
public class SolutionDataResolver {
    @Autowired
    private UserzQueryService userzQueryService;
    @Autowired
    private ProblemQueryService problemQueryService;
    @Autowired
    private SolutionzCommandService solutionzCommandService;

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.SolutionCreate)
    public SolutionResponse createSolution(@RequestHeader(name = "authToken", required = true) String authToken,
                                           @InputArgument(name = "newSolution")SolutionCreateInput solutionCreateInput){
        var userz = userzQueryService.findUserzByAuthToken(authToken).orElseThrow(ProblemzAuthenticationException::new);
        var problemId = UUID.fromString(solutionCreateInput.getProblemId());
        var problmez = problemQueryService.problemzDetail(problemId).orElseThrow(DgsEntityNotFoundException::new);
        var solutionz = GraphQLBeanMapper.mapToEntity(solutionCreateInput, userz, problmez);
        var created = solutionzCommandService.createSolutionz(solutionz);
        return SolutionResponse.newBuilder().solution(GraphQLBeanMapper.mapToGraphql(created)).build();
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.SolutionVote)
    public SolutionResponse createSolutionVote(@RequestHeader(name = "authToken", required = true) String authToken,
                                           @InputArgument(name = "newVote") SolutionVoteInput solutionVoteInput){
        Optional<Solutionz> updated;
        userzQueryService.findUserzByAuthToken(authToken).orElseThrow(ProblemzAuthenticationException::new);
        if(solutionVoteInput.getVoteAsGood()){
            updated = solutionzCommandService.voteGood(UUID.fromString(solutionVoteInput.getSolutuonId()));
        } else {
                updated = solutionzCommandService.voteBad(UUID.fromString(solutionVoteInput.getSolutuonId()));
            }
        if(updated.isEmpty())
            throw new DgsEntityNotFoundException("solution " + solutionVoteInput.getSolutuonId() + " not found");
        return SolutionResponse.newBuilder().solution(GraphQLBeanMapper.mapToGraphql(updated.get())).build();
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> subscribeSolutionVote(@InputArgument(name = "solutionId") String solutionId){
        return solutionzCommandService.solutionzFlux().map(GraphQLBeanMapper::mapToGraphql);
    }
}
