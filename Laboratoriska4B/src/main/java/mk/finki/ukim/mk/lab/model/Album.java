package mk.finki.ukim.mk.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor

public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;
    @OneToMany(mappedBy = "album")
    private List<Song> song;

    public Album(String name, String genre, String releaseYear) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
