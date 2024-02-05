package com.cmc.graphql.component.problemz;

import com.course.graphql.DgsConstants;
import com.course.graphql.types.Problem;
import com.course.graphql.types.ProblemCreateInput;
import com.course.graphql.types.ProblemResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;

@DgsComponent
public class ProblemDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemLatestList)
    public List<Problem> getProblemLatestList(){
        return null;
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemDetail)
    public List<Problem> getProblemDetail(@InputArgument(name = "id") String problemId){
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(@RequestHeader(name = "authToken", required = true) String authToken,
                                         @InputArgument(name = "problem")ProblemCreateInput problemCreateInput) {
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> subscribeProblemAdded(){
        return null;
    }
}
