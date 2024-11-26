package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Long id) {
        return this.albumRepository.findById(id);
    }


//    @Override
//    public Optional<Album> save(String name, String genre, String releaseYear) {
//        Album album = new Album(name, genre, releaseYear);
//        return Optional.of(this.albumRepository.save(album));
//    }

    @Override
    public void deleteById(Long id) {

    }
}
