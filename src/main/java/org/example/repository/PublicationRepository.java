package org.example.repository;


import org.example.model.Publication;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PublicationRepository extends ElasticsearchRepository<Publication, String> {
}
