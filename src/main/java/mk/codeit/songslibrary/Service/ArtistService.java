package mk.codeit.songslibrary.Service;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.ArtistDTO;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    List<ArtistDTO> getAllArtists();

    Optional<Artist> getArtistById(Long id);

    Optional<Artist> getArtistByName(String artistName);

    Optional<ArtistDTO> saveArtist(ArtistDTO artist) throws Exception;

    List<ArtistDTO> getMacedonianArtists();

    Optional<ArtistDTO> getSpecifiedArtist(Long id);

    Optional<ArtistDTO> deleteArtistById(Long id);
}
