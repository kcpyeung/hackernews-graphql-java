package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

import java.util.Optional;

public class LinkResolver implements GraphQLResolver<Link> {

    private final UserRepository userRepository;

    public LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(Link link) {
        return Optional.ofNullable(link.getUserId())
                .flatMap(userRepository::findById)
                .orElse(null);
    }

}
