package org.example.filter;

import org.example.model.Publication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CitationCountFilter implements Filter{
    @Override
    public List<Publication> apply(List<Publication> publications, String query) {
        return publications.stream()
                .filter(pub -> pub.getCitations() != null)
                .sorted((pub1, pub2) -> {
                    int citation1 = pub1.getCitations();
                    int citation2 = pub2.getCitations();
                    return Integer.compare(citation2, citation1);
                })
                .collect(Collectors.toList());
    }
}
