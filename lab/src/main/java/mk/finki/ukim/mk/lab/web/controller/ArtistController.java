package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistListPage(Model model, HttpSession session) {
        List<Artist> artists = artistService.listArtist();
        model.addAttribute("artists", artists);

        // Земете го trackId од сесијата, ако постои
        Song song = (Song) session.getAttribute("trackId");
        if (song != null) {
            model.addAttribute("trackId", song.getTrackId());
        }

        // Врати ја HTML страницата
        return "artistsList"; // Thymeleaf template: artistsList.html
    }

    @PostMapping
    public String addArtistToSong(@RequestParam String artistId, HttpSession session) {
        Artist artist = artistService.findById(Long.valueOf(artistId)).get();
        session.setAttribute("artistId", artist);

        Song song = (Song) session.getAttribute("trackId");
       session.setAttribute("trackId", song);

        songService.addArtistToSong(artist, song);
        // Пренасочи кон деталите за песната
        return "redirect:/songDetails";
    }
}