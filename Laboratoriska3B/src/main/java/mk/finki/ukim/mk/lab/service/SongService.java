package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Optional<Song> findById(Long id);
    //Page<Song> findPage(String title, Long albumId, Integer pageNum, Integer pageSize);
   // Artist addArtistToSong(Artist artist, Song song);
    List<Song> findAllByAlbumId(Long albumId);
    Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Long albumId);
    Optional<Song> update(Long id, String trackId, String title, String genre, Integer releaseYear, Long albumId);
    //Optional<Song> findByTrackId(String trackId);
    void deleteById(Long id);
}
