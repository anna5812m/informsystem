package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Publication;
import org.example.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class ElasticSearchConfig {

    @Autowired
    private PublicationRepository publicationRepository;

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\Анна\\IdeaProjects\\informsystem\\src\\main\\resources\\output.json");
        List<Publication> publications = objectMapper.readValue(file,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Publication.class));
        publicationRepository.deleteAll();
        publicationRepository.saveAll(publications);
    }
}
