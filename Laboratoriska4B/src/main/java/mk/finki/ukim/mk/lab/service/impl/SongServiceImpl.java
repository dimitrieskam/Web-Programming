package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.model.exceptions.AlbumNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.SongNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.jpa.SongRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mk.finki.ukim.mk.lab.service.specifiactions.FieldFilterSpecification.filterContainsText;
import static mk.finki.ukim.mk.lab.service.specifiactions.FieldFilterSpecification.filterEquals;

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

//    @Override
//    public Page<Song> findPage(String title, Long albumId, Integer pageNum, Integer pageSize) {
//        Specification<Song> specification = Specification
//                .where(filterContainsText(Song.class, "title", title))
//                .and(filterEquals(Song.class, "album.id", albumId));
//
//        return this.songRepository.findAll(
//                specification,
//                PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "title"))
//        );
//    }




    @Override
    public List<Song> findAllByAlbumId(Long albumId) {
        return this.songRepository.findAllByAlbum_Id(albumId);
    }


//    @Override
//    public Artist addArtistToSong(Artist artist, Song song) {
//        if (!song.getPerformers().contains(artist)){
//            song.getPerformers().add(artist);
//            this.songRepository.addArtistToSong(artist, song);
//        }
//        return artist;
//    }



    @Override
    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Long albumId) {
        Album album = this.albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
        Song song = new Song(trackId, title, genre, releaseYear, album);
        return Optional.of(this.songRepository.save(song));
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

        return Optional.of(this.songRepository.save(song));
    }
//
//    @Override
//    public Optional<Song> findByTrackId(String trackId) {
//        return this.songRepository.findByTrackId(trackId);
//    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

}
