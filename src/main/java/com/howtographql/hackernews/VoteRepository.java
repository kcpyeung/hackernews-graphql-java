package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VoteRepository {

    private final List<Vote> votes;

    public VoteRepository() {
        this.votes = new ArrayList<>();
    }

    public List<Vote> findByUserId(String userId) {
        return this
                .votes
                .stream()
                .filter(vote -> vote.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Vote> findByLinkId(String linkId) {
        return this
                .votes
                .stream()
                .filter(vote -> vote.getLinkId().equals(linkId))
                .collect(Collectors.toList());
    }

    public void saveVote(Vote vote) {
        this.votes.add(vote);
    }
}
