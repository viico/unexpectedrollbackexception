package com.example.unexpectedrollbackexception;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProjectQueryResolver {
    private final ProjectRepository projectRepository;

    public ProjectQueryResolver(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @QueryMapping
    public List<Project> projects() {
        return projectRepository.findAll();
    }

}
