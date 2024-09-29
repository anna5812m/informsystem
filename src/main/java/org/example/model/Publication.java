package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = "myindex")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Publication {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Text)
    private String journal;

    @Field(type = FieldType.Double)
    private Double journalPercentile;

    @Field(type = FieldType.Integer)
    private Integer year;

    @Field(type = FieldType.Integer)
    private Integer citations;

    @Field(type = FieldType.Keyword)
    private List<String> keywords;

    @Field(type = FieldType.Text)
    private String annotation;

  /*  @Field(type = FieldType.Double)
    private double bm25Score;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public Double getJournalPercentile() {
        return journalPercentile;
    }

    public void setJournalPercentile(Double journalPercentile) {
        this.journalPercentile = journalPercentile;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCitations() {
        return citations;
    }

    public void setCitations(Integer citations) {
        this.citations = citations;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

  /*  public double getBm25Score() {
        return bm25Score;
    }

    public void setBm25Score(double bm25Score) {
        this.bm25Score = bm25Score;
    }*/
}