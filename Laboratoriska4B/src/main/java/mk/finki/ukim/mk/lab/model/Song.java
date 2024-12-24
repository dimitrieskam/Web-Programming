package mk.finki.ukim.mk.lab.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


@Data
@Entity
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ManyToOne
    private Album album;
    @ManyToMany
    private List<Artist> performers;


    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = (long) (Math.random()*1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album=album;
    }

}

