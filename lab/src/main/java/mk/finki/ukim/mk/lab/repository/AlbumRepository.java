package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    private List<Album> albums = new ArrayList<>();


    @PostConstruct
    public void initializeData()
    {

        albums.add(new Album("A Night at the Opera", "Rock", "1975"));
        albums.add(new Album("Slippery When Wet", "Rock", "1986"));
        albums.add(new Album("Thriller", "Pop","1982"));
        albums.add(new Album("Space Oddity", "Pop","1969"));
        albums.add(new Album("Blue Hawaii", "Rock", "1961"));
    }

    public Optional<Album> save(Album album){
        albums.removeIf(a->a.getName().equals(album.getName()));
        albums.add(album);
        return Optional.of(album);
    }

    public List<Album> findAll(){
        return albums;
    }

    public void deleteById(Long id){
        albums.removeIf(i->i.getId().equals(id));
    }

    public Optional<Album> findById(Long id){
        return albums.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
