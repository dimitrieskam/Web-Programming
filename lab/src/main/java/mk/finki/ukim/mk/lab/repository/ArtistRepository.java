package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    private List<Artist> artists = new ArrayList<>();

    @PostConstruct
    public void initializeData()
    {
        artists.add(new Artist("Jon", "Bon Jovi", "Rock musician and actor"));
        artists.add(new Artist("Freddie", "Mercury", "Lead vocalist of Queen"));
        artists.add(new Artist("Elvis", "Presley", "King of Rock and Roll"));
        artists.add(new Artist("Michael", "Jackson", "King of Pop"));
        artists.add(new Artist("David", "Bowie", "Influential rock musician"));

    }

    public Optional<Artist> save(Artist artist){
        artists.removeIf(c->c.getFirstName().equals(artist.getFirstName()));
        artists.add(artist);
        return Optional.of(artist);
    }

    public List<Artist> findAll(){
        return artists;
    }

    public Optional<Artist> findByName(String name){
        return artists.stream()
                .filter(a->a.getFirstName().equals(name))
                .findFirst();
    }
    public Optional<Artist> findById(Long id){
        return artists.stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id){
        artists.removeIf(i->i.getId().equals(id));
    }
}
