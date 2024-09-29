package org.example.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bm25Calculator {
    private static final double k1 = 1.5;
    private static final double b = 0.75;

    private List<String> documents;
    private Map<String, Integer> docFrequencies;
    private double avgDocLength;

    public Bm25Calculator(List<String> documents) {
        this.documents = documents.stream().filter(doc -> doc != null).collect(Collectors.toList());
        this.docFrequencies = new HashMap<>();
        this.avgDocLength = this.documents.stream()
                .collect(Collectors.averagingDouble(doc -> doc.split("\\s+").length));
        calculateDocFrequencies();
    }

    private void calculateDocFrequencies() {
        for (String doc : documents) {
            String[] words = doc.toLowerCase().split("\\s+");
            for (String word : words) {
                docFrequencies.put(word, docFrequencies.getOrDefault(word, 0) + 1);
            }
        }
    }

    public double score(String doc, String query) {
        if (doc == null) {
            return 0.0;
        }

        String[] queryWords = query.toLowerCase().split("\\s+");
        double score = 0.0;
        String[] docWords = doc.toLowerCase().split("\\s+");
        int docLength = docWords.length;

        for (String word : queryWords) {
            if (docFrequencies.containsKey(word)) {
                int tf = (int) List.of(docWords).stream().filter(w -> w.equals(word)).count();
                int df = docFrequencies.get(word);
                score += idf(df) * tf * (k1 + 1) / (tf + k1 * (1 - b + b * docLength / avgDocLength));
            }
        }
        return score;
    }

    private double idf(int docFrequency) {
        return Math.log((documents.size() - docFrequency + 0.5) / (docFrequency + 0.5) + 1);
    }
  /*  private static final double k1 = 1.5;
    private static final double b = 0.75;

    private List<String> documents;
    private Map<String, Integer> docFrequencies;
    private double avgDocLength;

    public Bm25Calculator(List<String> documents) {
        this.documents = documents.stream().filter(doc -> doc != null).collect(Collectors.toList());
        this.docFrequencies = new HashMap<>();
        this.avgDocLength = this.documents.stream()
                .collect(Collectors.averagingDouble(String::length));
        calculateDocFrequencies();
    }

    private void calculateDocFrequencies() {
        for (String doc : documents) {
            if (doc != null) {
                String[] words = doc.toLowerCase().split("\\s+");
                for (String word : words) {
                    docFrequencies.put(word, docFrequencies.getOrDefault(word, 0) + 1);
                }
            }
        }
    }

    public double score(String doc, String query) {
        if (doc == null) {
            return 0.0;
        }

        String[] words = query.toLowerCase().split("\\s+");
        double score = 0.0;
        String[] docWords = doc.toLowerCase().split("\\s+");
        int docLength = docWords.length;

        for (String word : words) {
            if (docFrequencies.containsKey(word)) {
                int tf = (int) List.of(docWords)
                        .stream()
                        .filter(w -> w.equals(word))
                        .count();
                int df = docFrequencies.get(word);
                score += idf(df) * tf * (k1 + 1) / (tf + k1 * (1 - b + b * docLength / avgDocLength));
            }
        }
        return score;
    }

    private double idf(int docFrequency) {
        return Math.log((documents.size() - docFrequency + 0.5) / (docFrequency + 0.5) + 1);
    }*/
  /*  private static final double k1 = 1.5;
    private static final double b = 0.75;

    private List<String> documents;
    private Map<String, Integer> docFrequencies;
    private double avgDocLength;

    public Bm25Calculator(List<String> documents) {
        this.documents = documents;
        this.docFrequencies = new HashMap<>();

        // Фильтрация null элементов перед averagingDouble()
        List<String> nonNullDocuments = documents.stream()
                .filter(doc -> doc != null)
                .collect(Collectors.toList());

        // Вычисление средней длины строк
        this.avgDocLength = nonNullDocuments.stream()
                .collect(Collectors.averagingDouble(String::length));

        calculateDocFrequencies();
    }

    private void calculateDocFrequencies() {
        for (String doc : documents) {
            if (doc != null) { // Убедимся, что документ не null перед обработкой
                String[] words = doc.split("\\s+");
                for (String word : words) {
                    docFrequencies.put(word, docFrequencies.getOrDefault(word, 0) + 1);
                }
            }
        }
    }

    public double score(String doc, String query) {
        if (doc == null) {
            return 0.0; // Если doc равен null, вернуть 0.0 или другое значение по умолчанию
        }

        String[] words = query.split("\\s+");
        double score = 0.0;
        int docLength = doc.split("\\s+").length;

        for (String word : words) {
            if (docFrequencies.containsKey(word)) {
                // Фильтрация null элементов при подсчете tf
                int tf = (int) List.of(doc.split("\\s+"))
                        .stream()
                        .filter(w -> w != null && w.equals(word))
                        .count();
                int df = docFrequencies.get(word);
                score += idf(df) * tf * (k1 + 1) / (tf + k1 * (1 - b + b * docLength / avgDocLength));
            }
        }
        return score;
    }

    private double idf(int docFrequency) {
        return Math.log((documents.size() - docFrequency + 0.5) / (docFrequency + 0.5) + 1);
    }*/
}

/*package org.example.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bm25Calculator {
    private static final double k1 = 1.5;
    private static final double b = 0.75;

    private List<String> documents;
    private Map<String, Integer> docFrequencies;
    private double avgDocLength;
    private String query; // Добавляем поле для хранения запроса

    public Bm25Calculator(List<String> documents, String query) {
        this.documents = documents;
        this.query = query; // Инициализируем запрос

        this.docFrequencies = new HashMap<>();

        // Фильтрация null элементов перед averagingDouble()
        List<String> nonNullDocuments = documents.stream()
                .filter(doc -> doc != null)
                .collect(Collectors.toList());

        // Вычисление средней длины строк
        this.avgDocLength = nonNullDocuments.stream()
                .collect(Collectors.averagingDouble(String::length));

        calculateDocFrequencies();
    }

    private void calculateDocFrequencies() {
        for (String doc : documents) {
            if (doc != null) { // Убедимся, что документ не null перед обработкой
                String[] words = doc.split("\\s+");
                for (String word : words) {
                    docFrequencies.put(word, docFrequencies.getOrDefault(word, 0) + 1);
                }
            }
        }
    }

    public double score(String doc) {
        if (doc == null) {
            return 0.0; // Если doc равен null, вернуть 0.0 или другое значение по умолчанию
        }

        String[] words = query.split("\\s+"); // Используем сохраненный query
        double score = 0.0;
        int docLength = doc.split("\\s+").length;

        for (String word : words) {
            if (docFrequencies.containsKey(word)) {
                // Фильтрация null элементов при подсчете tf
                int tf = (int) List.of(doc.split("\\s+"))
                        .stream()
                        .filter(w -> w != null && w.equals(word))
                        .count();
                int df = docFrequencies.get(word);
                score += idf(df) * tf * (k1 + 1) / (tf + k1 * (1 - b + b * docLength / avgDocLength));
            }
        }
        return score;
    }

    private double idf(int docFrequency) {
        return Math.log((documents.size() - docFrequency + 0.5) / (docFrequency + 0.5) + 1);
    }
}*/

