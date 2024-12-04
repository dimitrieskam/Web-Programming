package mk.finki.ukim.mk.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;


    public Artist(String firstName, String lastName, String bio) {
        this.id = (long) (Math.random()*1000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

}
