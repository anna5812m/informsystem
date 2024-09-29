package org.example.model;

import org.example.filter.Filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new ArrayList<>();

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public List<Publication> applyFilters(List<Publication> publications, String query) {
        for (Filter filter : filters) {
            publications = filter.apply(publications, query);
        }
        return publications;
    }
}
