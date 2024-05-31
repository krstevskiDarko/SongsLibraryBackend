package mk.codeit.songslibrary.Service;

import mk.codeit.songslibrary.Model.DTO.PlaylistDTO;
import mk.codeit.songslibrary.Model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {


    Optional<PlaylistDTO> savePlaylist(PlaylistDTO playlist);

    Optional<PlaylistDTO> addSongToPlaylist(Long id, Long songId);

    List<PlaylistDTO> getPlaylistWithSongsByArtist(Long artistId);

    List<PlaylistDTO> getPlaylistsWithPublicStatusAndMaxThreeSongs();

    Optional<Playlist> getPlaylistById(Long id);

    Integer calculateTotalDurationOfPlaylist(Long id);

    void deletePlaylistById(Long id);
}
