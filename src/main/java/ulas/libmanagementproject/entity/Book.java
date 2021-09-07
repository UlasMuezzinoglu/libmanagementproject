package ulas.libmanagementproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Data
@Document(collection = "books")
@NoArgsConstructor


public class Book {

    public Book(String name, int pages, Author author, Genre genre) {
        this.name = name;
        this.pages = pages;
        this.author = author;
        this.genre = genre;
    }



    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @TextIndexed(weight = 4)
    private String name;

    private int pages;

    @TextIndexed(weight = 3)
    private Author author;

    @TextIndexed(weight = 2)
    private  Genre genre;

    @JsonIgnore
    @TextScore
    private Float score;

}
