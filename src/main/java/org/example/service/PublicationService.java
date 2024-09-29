package org.example.service;

import org.example.filter.*;
import org.example.model.FilterChain;
import org.example.model.Publication;
import org.example.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    private final List<Filter> availableFilters;

    @Autowired
    public PublicationService(List<Filter> availableFilters) {
        this.availableFilters = availableFilters;
    }

    public List<Publication> searchPublications(String query, List<Class<? extends Filter>> filterClasses) {
        Iterable<Publication> iterablePublications = publicationRepository.findAll();
        List<Publication> publications = StreamSupport.stream(iterablePublications.spliterator(), false)
                .collect(Collectors.toList());

        FilterChain filterChain = new FilterChain();

        for (Class<? extends Filter> filterClass : filterClasses) {
            availableFilters.stream()
                    .filter(filter -> filter.getClass().equals(filterClass))
                    .findFirst()
                    .ifPresent(filterChain::addFilter);
        }

        return filterChain.applyFilters(publications, query);
    }
  /*  @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private List<Filter> filters;*/

   /* @Autowired
    private PublicationRepository publicationRepository;

    private final List<Filter> availableFilters;

    @Autowired
    public PublicationService(List<Filter> availableFilters) {
        this.availableFilters = availableFilters;
    }

    public List<Publication> searchPublications(String query, List<Class<? extends Filter>> filterClasses) {
        Iterable<Publication> iterablePublications = publicationRepository.findAll();
        List<Publication> publications = StreamSupport.stream(iterablePublications.spliterator(), false)
                .collect(Collectors.toList());

        FilterChain filterChain = new FilterChain();

        for (Class<? extends Filter> filterClass : filterClasses) {
            availableFilters.stream()
                    .filter(filter -> filter.getClass().equals(filterClass))
                    .findFirst()
                    .ifPresent(filterChain::addFilter);
        }

        return filterChain.applyFilters(publications, query);
    }*/

  /*  public List<Publication> searchPublications(String query) {
        Iterable<Publication> iterablePublications = publicationRepository.findAll();
        List<Publication> publications = StreamSupport.stream(iterablePublications.spliterator(), false)
                .collect(Collectors.toList());

        Set<Publication> filteredPublications = new HashSet<>(publications);

        boolean firstFilterApplied = false;

        int appliedFilters = 0;
        // Применение конкретных фильтров
        for (Filter filter : filters) {
            if (filter instanceof Bm25Filter || filter instanceof RecencyFilter || filter instanceof NoveltyVerbFilter) {
                List<Publication> currentFiltered = filter.apply(publications, query);

                if (!firstFilterApplied) {
                    filteredPublications = new HashSet<>(currentFiltered);
                    firstFilterApplied = true;
                } else {
                    filteredPublications.retainAll(currentFiltered);
                }

                appliedFilters++;
                if (appliedFilters == 3) {
                    break;
                }
            }
        }

        return filteredPublications.stream().collect(Collectors.toList());
    }
}

    public List<Publication> searchPublications(String query) {
        Iterable<Publication> iterablePublications = publicationRepository.findAll();
        List<Publication> publications = StreamSupport.stream(iterablePublications.spliterator(), false)
                .collect(Collectors.toList());

        // Переменная для хранения объединенных результатов фильтрации
        Set<Publication> filteredPublications = publications.stream().collect(Collectors.toSet());


        for (Filter filter : filters) {
            publications = filter.apply(publications, query);
            //List<Publication> currentFiltered = filter.apply(publications, query);
          //  filteredPublications.retainAll(currentFiltered);

        /*    if (filter instanceof RecencyFilter) {
               publications = filter.apply(publications, query);
                break;
            }*/
         /*   if (filter instanceof KeywordFilter) {
                publications = filter.apply(publications, query);
                break;
            }*/
          /*  if (filter instanceof NoveltyVerbFilter) {
                publications = filter.apply(publications, query);
                break;
            }*/
        /*    if (filter instanceof Bm25Filter) {
                publications = filter.apply(publications, query);
                break;
            }*/
         /*   if (filter instanceof JournalPercentileFilter) {
                publications = filter.apply(publications, query);
                break;
            }*/
           /* if (filter instanceof CitationCountFilter) {
                publications = filter.apply(publications, query);
                break;
            }
        }

        return publications;
        return filteredPublications.stream().collect(Collectors.toList());*/
    //}
}
