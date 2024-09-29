package org.example.filter;

import org.example.model.Publication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoveltyVerbFilter implements Filter{

    private static final List<String> NOVELTY_VERBS = Arrays.asList(
            "обнаруживать", "выявлять", "открывать", "оценивать",
            "сформулировать", "определить", "дать оценку"
    );

    @Override
    public List<Publication> apply(List<Publication> publications, String query) {
        return publications.stream()
                .sorted(Comparator.comparingLong(this::countNoveltyVerbs).reversed())
                .collect(Collectors.toList());
    }

    private long countNoveltyVerbs(Publication publication) {
        return NOVELTY_VERBS.stream()
                .filter(publication.getAnnotation()::contains)
                .count();
    }
}