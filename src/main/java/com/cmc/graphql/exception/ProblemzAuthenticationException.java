package com.cmc.graphql.exception;

public class ProblemzAuthenticationException extends RuntimeException {
    public ProblemzAuthenticationException(){
        super("invalid credential");
    }
}
