package mk.codeit.songslibrary.Web;

import mk.codeit.songslibrary.Model.DTO.PlaylistDTO;
import mk.codeit.songslibrary.Model.Playlist;
import mk.codeit.songslibrary.Service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/save/")
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlist) {
        return this.playlistService.savePlaylist(playlist)
                .map(p -> ResponseEntity.ok().body(p))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/artist/{id}")
    public List<PlaylistDTO> getPlaylistById(@PathVariable Long id) {
        return this.playlistService.getPlaylistWithSongsByArtist(id);

    }

    @GetMapping("/public")
    public List<PlaylistDTO> getPlaylistThatArePublic() {
        return this.playlistService.getPlaylistsWithPublicStatusAndMaxThreeSongs();
    }

    @GetMapping("/totalDuration/{id}")
    public Integer totalDuration(@PathVariable Long id) {
        return this.playlistService.calculateTotalDurationOfPlaylist(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlaylistDTO> deletePlaylist(@PathVariable Long id) {
        this.playlistService.deletePlaylistById(id);

        Optional<Playlist> playlist = this.playlistService.getPlaylistById(id);
        if (playlist.isPresent()) {
            return ResponseEntity.notFound().build();
        }else return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<PlaylistDTO> addSongToPlaylist(@PathVariable Long id, @RequestParam Long songId) {
        return this.playlistService.addSongToPlaylist(id,songId)
                .map(p -> ResponseEntity.ok().body(p))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
