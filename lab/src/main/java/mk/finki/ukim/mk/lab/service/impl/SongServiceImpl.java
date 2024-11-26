package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.model.exceptions.AlbumNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.SongNotFoundException;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return this.songRepository.findById(id);
    }


    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        if (!song.getPerformers().contains(artist)){
            song.getPerformers().add(artist);
            this.songRepository.addArtistToSong(artist, song);
        }
        return artist;
    }



    @Override
    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Long albumId) {
        Album album = this.albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
        Song song = new Song(trackId, title, genre, releaseYear, album);
        return this.songRepository.save(song);
    }

    @Override
    public Optional<Song> update(Long id, String trackId, String title, String genre, Integer releaseYear, Long albumId) {
        Song song = this.songRepository.findById(id)
                .orElseThrow(()-> new SongNotFoundException(id));
        Album album  = this.albumRepository.findById(albumId)
                .orElseThrow(()->new AlbumNotFoundException(albumId));

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        return this.songRepository.save(song);
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return this.songRepository.findByTrackId(trackId);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

}
