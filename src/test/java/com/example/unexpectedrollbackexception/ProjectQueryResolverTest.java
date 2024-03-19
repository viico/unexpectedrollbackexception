package com.example.unexpectedrollbackexception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectQueryResolverTest {

    @Autowired
    private WebApplicationContext context;

    @Test
    void shouldPass() {
        final String query = "{ projects { id label } }";

        final WebTestClient client =
                MockMvcWebTestClient.bindToApplicationContext(this.context)
                        .configureClient()
                        .baseUrl("/graphql")
                        .build();

        final HttpGraphQlTester httpGraphQlTester = HttpGraphQlTester.create(client);

        httpGraphQlTester
                .document(query)
                .execute()
                .errors()
                .satisfy(
                        responseErrors -> {
                            assertThat(responseErrors).hasSize(1);
                            assertThat(responseErrors.get(0).getMessage())
                                    .isEqualTo("The field at path '/projects[0]/label' was declared as a non null type, but the code involved in retrieving data has wrongly returned a null value.  The graphql specification requires that the parent field be set to null, or if that is non nullable that it bubble up null to its parent and so on. The non-nullable type is 'String' within parent type 'Project'");
                        });
    }
}