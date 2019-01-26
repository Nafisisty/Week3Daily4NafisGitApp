package com.example.week3daily4_nafisgitapp.events;

import com.example.week3daily4_nafisgitapp.model.user.RepositoryResponse;

public class RepositoryEvent {

    private RepositoryResponse[] repositoryResponse;

    public RepositoryEvent(RepositoryResponse[] repositoryResponse) {
        this.repositoryResponse = repositoryResponse;
    }

    public RepositoryResponse[] getRepositoryResponse() {
        return repositoryResponse;
    }

    public void setRepositoryResponse(RepositoryResponse[] repositoryResponse) {
        this.repositoryResponse = repositoryResponse;
    }
}
