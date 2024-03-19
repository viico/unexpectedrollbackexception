package com.example.unexpectedrollbackexception;

import graphql.ExecutionResult;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionContext;
import graphql.execution.ExecutionStrategyParameters;
import graphql.execution.NonNullableFieldWasNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class TransactionalAsyncExecutionStrategyWithExceptionHandler extends AsyncExecutionStrategy {

    @Override
    @Transactional
    public CompletableFuture<ExecutionResult> execute(final ExecutionContext executionContext,
                                                      final ExecutionStrategyParameters parameters) throws NonNullableFieldWasNullException {
        return super.execute(executionContext, parameters);
    }
}

