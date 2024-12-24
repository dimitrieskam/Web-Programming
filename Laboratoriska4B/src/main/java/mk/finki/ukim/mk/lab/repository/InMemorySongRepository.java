package mk.finki.ukim.mk.lab.repository;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {

    private List<Song> songs = new ArrayList<>();
    private final InMemoryAlbumRepository albumRepository;

    public InMemorySongRepository(InMemoryAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
    @PostConstruct
    public void initializeData()
    {
        List<Album> albums = albumRepository.findAll();

        songs.add(new Song("01", "Bohemian Rhapsody", "Rock", 1975, albums.get(0)));
        songs.add(new Song("02", "Livin' on a Prayer", "Rock", 1986, albums.get(1)));
        songs.add(new Song("03", "Thriller", "Pop", 1982, albums.get(2)));
        songs.add(new Song("04", "Space Oddity", "Rock", 1969, albums.get(3)));
        songs.add(new Song("05", "Can't Help Falling in Love", "Rock", 1961, albums.get(4)));
    }

    public Optional<Song> save(Song song) {
        songs.removeIf(s -> s.getTitle().equals(song.getTitle())); // Avoid duplicates by title
        songs.add(song);
        return Optional.of(song);
    }

    public List<Song> findAll(){
        return songs;
    }
    public Optional<Song> findById(Long id) {
        if (id == null) {
            return Optional.empty(); // Return an empty result if the input ID is null
        }
        return songs.stream()
                .filter(song -> id.equals(song.getId())) // Avoid `song.getId()` being null
                .findFirst();
    }

    public Optional<Song> findByTrackId(String trackId){
        return songs.stream()
                .filter(i -> trackId != null && trackId.equals(i.getTrackId()))
                .findFirst();

    }

    public Artist addArtistToSong(Artist artist, Song song){
        if (!song.getPerformers().contains(artist)){
            song.getPerformers().add(artist);
            save(song);
        }

        return artist;
    }

    public List<Song> search(String text){
        return songs.stream()
                .filter(s->s.getTitle().contains(text)||
                        s.getGenre().contains(text))
                .toList();
    }

    public void deleteById(Long id){
        songs.removeIf(i->i.getId().equals(id));
    }


}
