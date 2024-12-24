package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.repository.jpa.AlbumRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Album> albums = null;
    private final AlbumRepository albumRepository;

    public DataHolder(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    public void init() {
        if (this.albumRepository.count() == 0) {
            albums = new ArrayList<>();
            albums.add(new Album("A Night at the Opera", "Rock", "1975"));
            albums.add(new Album("Slippery When Wet", "Rock", "1986"));
            albums.add(new Album("Thriller", "Pop", "1982"));
            albums.add(new Album("Space Oddity", "Pop", "1969"));
            albums.add(new Album("Blue Hawaii", "Rock", "1961"));

            albumRepository.saveAll(albums);
        }
    }
}
