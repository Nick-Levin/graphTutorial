package com.example.graphtutorial.controller.error;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return toGraphQLError(env, ex);
    }

    private static GraphQLError toGraphQLError(DataFetchingEnvironment env, Throwable throwable) {
        return GraphQLError.newError()
                .errorType(ErrorType.DataFetchingException)
                .message(throwable.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

}
