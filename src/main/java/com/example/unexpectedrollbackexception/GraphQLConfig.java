package com.example.unexpectedrollbackexception;

import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(TransactionalAsyncExecutionStrategyWithExceptionHandler transactionalAsyncExecutionStrategyWithExceptionHandler) {
        return (graphQlSourceBuilder) -> graphQlSourceBuilder
                .configureGraphQl(graphQlBuilder -> graphQlBuilder
                        .queryExecutionStrategy(transactionalAsyncExecutionStrategyWithExceptionHandler)
                        .mutationExecutionStrategy(transactionalAsyncExecutionStrategyWithExceptionHandler));
    }

}