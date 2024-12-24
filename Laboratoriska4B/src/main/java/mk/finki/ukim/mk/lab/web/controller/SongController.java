package mk.finki.ukim.mk.lab.web.controller;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.hibernate.query.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;


    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;

    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) Long albumId,
                               Model model) {

        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("albumId", albumId);
        //model.addAttribute("title", title);

        List<Song> songs;
        if (albumId!=null){
            songs = songService.findAllByAlbumId(albumId);
        }  else {
            songs = songService.listSongs();
        }
        model.addAttribute("songs", songs);
        return "listSongs";
    }

//    @PostMapping
//    public String addSongToArtist(@RequestParam("trackId") String trackId, HttpSession session) {
//        if (trackId == null || trackId.isEmpty()) {
//            return "redirect:/songs?error=NoTrackSelected";
//        }
//       Song song = songService.findByTrackId(trackId).get();
//       session.setAttribute("trackId", song);
//        return "redirect:/artist";
//        }



    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        if (this.songService.findById(id).isPresent()) {
            Song song = this.songService.findById(id).get();
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("song", song);
            model.addAttribute("albums", albums);
            return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }


    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam Integer releaseYear,
                           @RequestParam Long albumId){
        songService.update(id,title, trackId, genre,releaseYear,albumId);
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model) {
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(
        @RequestParam(required = false) Long id,
        @RequestParam String trackId,
        @RequestParam String title,
        @RequestParam String genre,
        @RequestParam Integer releaseYear,
        @RequestParam Long album){
        if (id!=null){
            this.songService.update(id,trackId, title, genre, releaseYear, album);
        } else {
            this.songService.save(trackId, title, genre, releaseYear, album);

        }

        return "redirect:/songs";
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id) {
        this.songService.deleteById(id);
        return "redirect:/songs";
    }
}
