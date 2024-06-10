package mk.codeit.songslibrary.Service;

import mk.codeit.songslibrary.Model.DTO.SongDTO;
import mk.codeit.songslibrary.Model.Enumerations.Genre;
import mk.codeit.songslibrary.Model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<SongDTO> getAllSongs();

    Optional<Song> getSongById(Long id);

    Optional<SongDTO> addNewSongFromArtist(Long artistId, SongDTO song);

    Optional<SongDTO> getSongByLongestDurationFromArtist(Long artistId, Genre genre);

    List<SongDTO> getFirstThreeSongs();

    void deleteById(Long id);
}
