//package mk.finki.ukim.mk.lab.web.controller;
//
//
//import jakarta.servlet.http.HttpSession;
//import mk.finki.ukim.mk.lab.model.Artist;
//import mk.finki.ukim.mk.lab.model.Song;
//import mk.finki.ukim.mk.lab.service.SongService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/songDetails")
//public class SongDetailsController {
//
//    private final SongService songService;
//
//    public SongDetailsController(SongService songService) {
//        this.songService = songService;
//    }
//    @GetMapping
//    public String showSongDetailsPage(@RequestParam(value = "trackId", required = false) String trackId, HttpSession session, Model model) {
//        if (trackId == null || trackId.isEmpty()) {
//            // Attempt to retrieve from session
//            Song sessionSong = (Song) session.getAttribute("trackId");
//            if (sessionSong == null) {
//                model.addAttribute("hasError", true);
//                model.addAttribute("errorMessage", "No trackId provided or found in session.");
//                return "errorPage"; // Create a user-friendly error page
//            }
//            trackId = sessionSong.getTrackId(); // Use the session song's trackId
//        }
//
//        // Fetch the song by trackId
////        Optional<Song> songOptional = songService.findByTrackId(trackId);
////        if (songOptional.isEmpty()) {
////            model.addAttribute("hasError", true);
////            model.addAttribute("errorMessage", "No song found for the provided trackId.");
////            return "errorPage"; // User-friendly error message
////        }
//
////        Song song = songOptional.get();
////        session.setAttribute("trackId", song);
////        model.addAttribute("song", song);
////
////        return "songDetails";
////    }
//
//
////    @GetMapping
////    public String showSongDetailsPage(@RequestParam(value = "trackId", required = false) String trackId, HttpSession session, Model model) {
//////        Optional<Song> songOptional = songService.findByTrackId(trackId);
////        Song song = (Song) session.getAttribute("trackId");
////        if (song!=null) {;
////            session.setAttribute("title", song.getTitle());
////            session.setAttribute("genre", song.getGenre());
////            session.setAttribute("releaseYear", song.getReleaseYear());
////            session.setAttribute("album", song.getAlbum().getName());
////            List<Artist> performers = song.getPerformers();
////            session.setAttribute("performers", song.getPerformers());
////
////        }
////        session.setAttribute("song", song);
////            // Handle song not found or invalid trackId
////            return "songDetails";
////        }
//}
