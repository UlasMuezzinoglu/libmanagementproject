package ulas.libmanagementproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "types")
@NoArgsConstructor
public class Genre {


    public Genre(String name) {
        this.name = name;
    }

    @Id
    private String id;
    @TextIndexed(weight = 2)
    private String name;

}
