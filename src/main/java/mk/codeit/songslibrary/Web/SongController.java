package mk.codeit.songslibrary.Web;

import mk.codeit.songslibrary.Model.DTO.SongDTO;
import mk.codeit.songslibrary.Model.Enumerations.Genre;
import mk.codeit.songslibrary.Model.Song;
import mk.codeit.songslibrary.Service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<SongDTO> addSong(@RequestBody SongDTO song, @PathVariable Long id) {
        return this.songService.addNewSongFromArtist(id,song)
                .map(s -> ResponseEntity.ok().body(s))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/longestDuration/{id}")
    public ResponseEntity<SongDTO> longestDuration(@RequestParam String genre, @PathVariable Long id) {
        Genre genreEnum = Genre.valueOf(genre);
        return this.songService.getSongByLongestDurationFromArtist(id,genreEnum)
                .map(s -> ResponseEntity.ok().body(s))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/threeSongs")
    public List<SongDTO> threeSongs() {
        return this.songService.getFirstThreeSongs();
    }
}
