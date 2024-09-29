package org.example.filter;

import org.example.model.Publication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Bm25Filter implements Filter {
    @Override
    public List<Publication> apply(List<Publication> publications, String query) {
        List<String> annotations = publications.stream()
                .map(Publication::getAnnotation)
                .collect(Collectors.toList());

        Bm25Calculator bm25 = new Bm25Calculator(annotations);

        return publications.stream()
                .sorted((p1, p2) -> {
                    double score1 = bm25.score(p1.getAnnotation(), query);
                    double score2 = bm25.score(p2.getAnnotation(), query);
                    return Double.compare(score2, score1);
                })
                .collect(Collectors.toList());
    }
}
/*package org.example.filter;

import org.example.model.Publication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Bm25Filter implements Filter {
    @Override
    public List<Publication> apply(List<Publication> publications, String query) {

        List<String> annotations = publications.stream()
                .map(Publication::getAnnotation)
                .collect(Collectors.toList());

        Bm25Calculator bm25 = new Bm25Calculator(annotations, query);

        return publications.stream()
                .sorted((p1, p2) -> {
                    double score1 = bm25.score(p1.getAnnotation());
                    double score2 = bm25.score(p2.getAnnotation());
                    return Double.compare(score2, score1);
                })
                .collect(Collectors.toList());
    }
}*/

