package com.howtographql.hackernews;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkFilter {

    private int minimumVote;

    @JsonProperty("minimumVote") //the name must match the schema
    public int getMinimumVote() {
        return minimumVote;
    }

    public void setMinimumVote(int minimumVote) {
        this.minimumVote = minimumVote;
    }
}
