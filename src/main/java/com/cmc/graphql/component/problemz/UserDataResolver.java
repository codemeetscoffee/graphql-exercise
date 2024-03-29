package com.cmc.graphql.component.problemz;

import com.cmc.graphql.service.command.UserzCommandService;
import com.cmc.graphql.service.query.UserzQueryService;
import com.cmc.graphql.util.GraphQLBeanMapper;
import com.course.graphql.DgsConstants;
import com.course.graphql.types.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {
    @Autowired
    private UserzCommandService userzCommandService;
    @Autowired
    private UserzQueryService userzQueryService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    public User accountInfo(@RequestHeader (name = "authToken", required = true) String authToken){
        var userz = userzQueryService.findUserzByAuthToken(authToken).get();
        return GraphQLBeanMapper.mapToGraphql(userz);
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.UserCreate)
    public UserResponse createUser(@InputArgument(name = "user")UserCreateInput userCreateInput){
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.UserLogin)
    public UserResponse userLogin(@InputArgument(name = "user") UserLoginInput userLoginInput){
        var generatedToken = userzCommandService.login(userLoginInput.getUsername(), userLoginInput.getPassword());
        var userAuthToken = GraphQLBeanMapper.mapToGraphql(generatedToken);
        var userInfo = accountInfo(userAuthToken.getAuthToken());
        var userResponse = UserResponse.newBuilder().authToken(userAuthToken).user(userInfo).build();
        return userResponse;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.UserActivation)
    public UserResponse userActivation(@InputArgument(name = "user") UserActivationInput userActivationInput){
        return null;
    }

}


