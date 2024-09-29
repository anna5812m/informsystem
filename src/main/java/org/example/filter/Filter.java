package org.example.filter;


import org.example.model.Publication;

import java.util.List;

public interface Filter {
    List<Publication> apply(List<Publication> publications, String query);
}