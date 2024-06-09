package mk.codeit.songslibrary.Service.Implementations;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.PlaylistDTO;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArtistIdException;
import mk.codeit.songslibrary.Model.Exceptions.InvalidPlaylistIdException;
import mk.codeit.songslibrary.Model.Exceptions.InvalidSongIdException;
import mk.codeit.songslibrary.Model.Exceptions.PlaylistWithNoSongsException;
import mk.codeit.songslibrary.Model.Playlist;
import mk.codeit.songslibrary.Model.Song;
import mk.codeit.songslibrary.Repository.ArtistRepository;
import mk.codeit.songslibrary.Repository.PlaylistRepository;
import mk.codeit.songslibrary.Repository.SongRepository;
import mk.codeit.songslibrary.Service.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;



    public PlaylistServiceImpl(PlaylistRepository playlistRepository, SongRepository songRepository, ArtistRepository artistRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public Optional<Playlist> getPlaylistById(Long id) {
        return this.playlistRepository.findById(id);
    }

    @Override
    public Optional<PlaylistDTO> savePlaylist(PlaylistDTO playlist) {

        if (playlist.getSongs() == null || playlist.getSongs().isEmpty()) {
            throw new PlaylistWithNoSongsException();
        }

        List<Song> songs = playlist.getSongs().stream()
                .map(songRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();


        Playlist p = new Playlist(
                playlist.getName(),
                playlist.getDateCreated(),
                playlist.getStatusPublic(),
                new ArrayList<>()
        );

        songs.forEach(p::addSong);

        this.playlistRepository.save(p);


        return Optional.of(playlist);
    }

    @Override
    public Optional<PlaylistDTO> addSongToPlaylist(Long playlistId, Long songId) {
        Song song = this.songRepository.findById(songId).orElseThrow(() -> new InvalidSongIdException(songId));

        Playlist p = this.playlistRepository.findById(playlistId).orElseThrow(()-> new InvalidPlaylistIdException(playlistId));

        p.getSongs().add(song);
        this.playlistRepository.save(p);

        PlaylistDTO pDTO = new PlaylistDTO(
                p.getName(),
                p.getDateCreated(),
                p.getStatusPublic(),
                p.getSongs().stream().map(Song::getId).toList()
        );


        return Optional.of(pDTO);
    }

    @Override
    public List<PlaylistDTO> getPlaylistWithSongsByArtist(Long artistId) {
        Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new InvalidArtistIdException(artistId));

        List<Playlist> playlists =  this.playlistRepository.findPlaylistsWithSongsByArtist(artist);

        return getPlaylistDTOS(playlists);
    }

    @Override
    public List<PlaylistDTO> getPlaylistsWithPublicStatusAndMaxThreeSongs() {
        List<Playlist> playlists = this.playlistRepository.getAllPlaylistsWhichArePublicAndContainMaxOfThreeSongs();
        return getPlaylistDTOS(playlists);
    }

    private List<PlaylistDTO> getPlaylistDTOS(List<Playlist> playlists) {
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistDTOS.add(new PlaylistDTO(
                    playlist.getName(),
                    playlist.getDateCreated(),
                    playlist.getStatusPublic(),
                    playlist.getSongs().stream().map(Song::getId).toList()));
        }
        return playlistDTOS;
    }


    @Override
    public Integer calculateTotalDurationOfPlaylist(Long id) {
        Playlist p = this.playlistRepository.findById(id).orElseThrow(() -> new InvalidPlaylistIdException(id));

        List<Song> songs = p.getSongs();

        return songs.stream()
                .mapToInt(Song::getDurationInMinutes)
                .sum();
    }

    @Override
    public void deletePlaylistById(Long id) {
        Playlist p = this.playlistRepository.findById(id).orElseThrow(() -> new InvalidPlaylistIdException(id));
        this.playlistRepository.delete(p);
    }


}