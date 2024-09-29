package org.example.filter;

import org.example.model.Publication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KeywordFilter implements Filter {
    @Override
    public List<Publication> apply(List<Publication> publications, String query) {
        String[] queryWords = query.toLowerCase().split("\\s+");

        return publications.stream()
                .filter(publication -> {
                    List<String> keywords = publication.getKeywords();
                    if (keywords == null) {
                        return false;
                    }
                    long matchCount = keywords.stream()
                            .map(String::toLowerCase)
                            .filter(keyword -> {
                                for (String word : queryWords) {
                                    if (keyword.contains(word)) {
                                        return true;
                                    }
                                }
                                return false;
                            })
                            .count();
                    return matchCount == queryWords.length;
                })
                .sorted((p1, p2) -> {
                    long count1 = p1.getKeywords().stream()
                            .map(String::toLowerCase)
                            .filter(keyword -> {
                                for (String word : queryWords) {
                                    if (keyword.contains(word)) {
                                        return true;
                                    }
                                }
                                return false;
                            }).count();
                    long count2 = p2.getKeywords().stream()
                            .map(String::toLowerCase)
                            .filter(keyword -> {
                                for (String word : queryWords) {
                                    if (keyword.contains(word)) {
                                        return true;
                                    }
                                }
                                return false;
                            }).count();
                    return Long.compare(count2, count1);
                })
                .collect(Collectors.toList());
    }

  /*  @Override
    public List<Publication> apply(List<Publication> publications, String query) {
        return publications.stream()
                .filter(publication -> {
                    List<String> keywords = publication.getKeywords();
                    return keywords != null && keywords.stream()
                            .anyMatch(keyword -> keyword.toLowerCase().contains(query.toLowerCase()));
                })
                .sorted((p1, p2) -> {
                    long count1 = p1.getKeywords().stream()
                            .filter(keyword -> keyword.toLowerCase().contains(query.toLowerCase())).count();
                    long count2 = p2.getKeywords().stream()
                            .filter(keyword -> keyword.toLowerCase().contains(query.toLowerCase())).count();
                    return Long.compare(count2, count1);
                })
                .collect(Collectors.toList());
    }*/

      /*  return publications.stream()
                .filter(publication -> {
                    List<String> keywords = publication.getKeywords();
                    return keywords != null && keywords.stream()
                            .anyMatch(keyword -> keyword.contains(query));
                })
                .sorted((p1, p2) -> {
                    long count1 = p1.getKeywords().stream()
                            .filter(keyword -> keyword.contains(query)).count();
                    long count2 = p2.getKeywords().stream()
                            .filter(keyword -> keyword.contains(query)).count();
                    return Long.compare(count2, count1);
                })
                .collect(Collectors.toList());
    }*/
}
