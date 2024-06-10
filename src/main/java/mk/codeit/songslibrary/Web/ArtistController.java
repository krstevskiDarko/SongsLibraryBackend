package mk.codeit.songslibrary.Web;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.ArtistDTO;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArgumentsException;
import mk.codeit.songslibrary.Service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://songslibraryfrontend.onrender.com")
@RequestMapping("/api/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        return ResponseEntity.ok(this.artistService.getAllArtists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getSpecifiedArtist(@PathVariable Long id) {
        return this.artistService.getSpecifiedArtist(id)
                .map(artist -> ResponseEntity.ok().body(artist))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) throws Exception {
        return this.artistService.saveArtist(artistDTO)
                .map(artist -> ResponseEntity.ok().body(artist))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("/macedonians")
    public List<ArtistDTO> getMacedonianArtist() {
        return this.artistService.getMacedonianArtists();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ArtistDTO> deleteArtist(@PathVariable Long id) {
        return this.artistService.deleteArtistById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
}
