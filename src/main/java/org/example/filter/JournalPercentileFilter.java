package org.example.filter;

import org.example.model.Publication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JournalPercentileFilter implements Filter{
    @Override
    public List<Publication> apply(List<Publication> publications, String query) {
        return publications.stream()
                .sorted(Comparator.comparingDouble(Publication::getJournalPercentile).reversed())
                .collect(Collectors.toList());
    }
}