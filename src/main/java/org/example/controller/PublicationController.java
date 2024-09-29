package org.example.controller;

import org.example.filter.*;
import org.example.model.Publication;
import org.example.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publications")
public class PublicationController {

  /*  @Autowired
   private PublicationService publicationService;

    @GetMapping("/search")
    public List<Publication> searchPublications(@RequestParam String query) {
        return publicationService.searchPublications(query);
    }*/
  @Autowired
  private PublicationService publicationService;

    @GetMapping("/search")
    public List<Publication> searchPublications(
            @RequestParam String query,
            @RequestParam(required = false) List<String> filters) {

        List<Class<? extends Filter>> filterClasses = (filters == null) ?
                List.of() :
                filters.stream()
                        .map(this::getFilterClassByName)
                        .collect(Collectors.toList());

        return publicationService.searchPublications(query, filterClasses);
    }

    private Class<? extends Filter> getFilterClassByName(String filterName) {
        switch (filterName) {
            case "рел":
                return Bm25Filter.class;
            case "цит":
                return CitationCountFilter.class;
            case "проц":
                return JournalPercentileFilter.class;
            case "ключ":
                return KeywordFilter.class;
            case "глаг":
                return NoveltyVerbFilter.class;
            case "акт":
                return RecencyFilter.class;
            default:
                throw new IllegalArgumentException("Unknown filter: " + filterName);
        }
    }


}




