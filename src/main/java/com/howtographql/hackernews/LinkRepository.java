package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LinkRepository {

    private final List<Link> links;

    public LinkRepository() {
        links = new ArrayList<>();
        //add some links to start off with
        links.add(new Link("1","http://howtographql.com", "Your favorite GraphQL page", "1"));
        links.add(new Link("2","http://graphql.org/learn/", "The official docks", "1"));
    }

    public List<Link> getAllLinks(LinkFilter filter, int skip, int take) {
        List<Link> links = fetchLinks(filter);
        List<Link> skipped = skip(links, skip);
        return take(skipped, take);
    }

    private List<Link> take(List<Link> links, int take) {
        return take == 0 ? links : links.subList(0, take);
    }

    private List<Link> skip(List<Link> links, int skip) {
        return skip == 0 ? links : links.subList(skip, links.size());
    }

    private List<Link> fetchLinks(LinkFilter filter) {
        if (filter == null) {
            return links;
        } else {
            return links
                    .stream()
                    .filter(link -> link.getVotes() >= filter.getMinimumVote())
                    .collect(Collectors.toList());
        }
    }

    public void saveLink(Link link) {
        links.add(link);
    }

    public String nextId() {
        return this
                .links
                .stream()
                .map(Link::getId)
                .map(Integer::valueOf)
                .sorted()
                .max(Integer::compareTo)
                .map(i -> ++i)
                .map(Object::toString)
                .orElse("1");
    }

    public Optional<Link> findById(String linkId) {
        return this
                .links
                .stream()
                .filter(link -> link.getId().equals(linkId))
                .findFirst();
    }
}
