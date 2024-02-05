package com.cmc.graphql.component.problemz;

import com.course.graphql.DgsConstants;
import com.course.graphql.types.Solution;
import com.course.graphql.types.SolutionCreateInput;
import com.course.graphql.types.SolutionResponse;
import com.course.graphql.types.SolutionVoteInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
public class SolutionDataResolver {
    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.SolutionCreate)
    public SolutionResponse createSolution(@RequestHeader(name = "authToken", required = true) String authToken,
                                           @InputArgument(name = "newSolution")SolutionCreateInput solutionCreateInput){
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.SolutionVote)
    public SolutionResponse createSolutionVote(@RequestHeader(name = "authToken", required = true) String authToken,
                                           @InputArgument(name = "newVote") SolutionVoteInput solutionVoteInput){
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> subscribeSolutionVote(@InputArgument(name = "solutionId") String solutionId){
        return null;
    }
}
