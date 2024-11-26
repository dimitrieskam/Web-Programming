package mk.finki.ukim.mk.lab.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
//@NoArgsConstructor

public class Album {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;

    public Album(String name, String genre, String releaseYear) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
