package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import graphql.schema.DataFetchingEnvironment;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public Mutation(LinkRepository linkRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Link createLink(String url, String description, DataFetchingEnvironment env) {
        AuthContext context = env.getContext();
        Link newLink = new Link(linkRepository.nextId(), url, description, context.getUser().getId());
        linkRepository.saveLink(newLink);
        return newLink;
    }

    public User createUser(String name, AuthData auth) {
        return userRepository.saveUser(new User(name, auth.getEmail(), auth.getPassword()));
    }

    public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
        Optional<User> user = userRepository.findByEmail(auth.getEmail());
        return user
                .map(u -> u.getPassword().equals(auth.getPassword()) ? new SigninPayload(u.getId(), u) : null)
                .orElseThrow(() -> new IllegalAccessException("Invalid credentials"));
    }

    public Vote createVote(String linkId, String userId) {
        ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
        Vote vote = new Vote(now, userId, linkId);
        updateLinkVoteCount(vote);
        return vote;
    }

    private void updateLinkVoteCount(Vote vote) {
        linkRepository
                .findById(vote.getLinkId())
                .ifPresent(Link::voted);
    }
}
