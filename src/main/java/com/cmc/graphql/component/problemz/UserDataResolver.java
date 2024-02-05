package com.cmc.graphql.component.problemz;

import com.course.graphql.DgsConstants;
import com.course.graphql.types.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    public User accountInfo(@RequestHeader (name = "authToken", required = true) String authToken){
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.UserCreate)
    public UserResponse createUser(@InputArgument(name = "user")UserCreateInput userCreateInput){
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.UserLogin)
    public UserResponse userLogin(@InputArgument(name = "user") UserLoginInput userLoginInput){
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.UserActivation)
    public UserResponse userActivation(@InputArgument(name = "user") UserActivationInput userActivationInput){
        return null;
    }

}


