package com.cmc.graphql.component.problemz;

import com.cmc.graphql.exception.ProblemzAuthenticationException;
import com.cmc.graphql.service.ProblemQueryService;
import com.cmc.graphql.service.command.ProblemzCommandService;
import com.cmc.graphql.service.query.UserzQueryService;
import com.cmc.graphql.util.GraphQLBeanMapper;
import com.course.graphql.DgsConstants;
import com.course.graphql.types.Problem;
import com.course.graphql.types.ProblemCreateInput;
import com.course.graphql.types.ProblemResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ProblemDataResolver {
    @Autowired
    private ProblemQueryService problemQueryService;
    @Autowired
    private ProblemzCommandService commandService;

    @Autowired
    private UserzQueryService userzQueryService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemLatestList)
    public List<Problem> getProblemLatestList(){

        return problemQueryService.problemzLatestList().stream().map(GraphQLBeanMapper::mapToGraphql).toList();
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemDetail)
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId){
        var problemzId = UUID.fromString(problemId);
        var problemz = problemQueryService.problemzDetail(problemzId).orElseThrow(DgsEntityNotFoundException::new);
        return GraphQLBeanMapper.mapToGraphql(problemz);
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(@RequestHeader(name = "authToken", required = true) String authToken,
                                         @InputArgument(name = "problem")ProblemCreateInput problemCreateInput) {

        var userz = userzQueryService.findUserzByAuthToken(authToken).orElseThrow(ProblemzAuthenticationException::new);
        var problemz = GraphQLBeanMapper.mapToEntity(problemCreateInput, userz);
        var created = commandService.createProblem(problemz);
        return ProblemResponse.newBuilder().problem(GraphQLBeanMapper.mapToGraphql(created)).build();
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> subscribeProblemAdded(){

        return commandService.problemzFlux().map(GraphQLBeanMapper::mapToGraphql);
    }
}
