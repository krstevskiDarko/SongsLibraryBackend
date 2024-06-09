package mk.codeit.songslibrary.Service.Implementations;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.SongDTO;
import mk.codeit.songslibrary.Model.Enumerations.Genre;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArgumentsException;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArtistIdException;
import mk.codeit.songslibrary.Model.Playlist;
import mk.codeit.songslibrary.Model.Song;
import mk.codeit.songslibrary.Repository.ArtistRepository;
import mk.codeit.songslibrary.Repository.SongRepository;
import mk.codeit.songslibrary.Service.SongService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public Optional<Song> getSongById(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public Optional<SongDTO> addNewSongFromArtist(Long artistId, SongDTO songDTO) throws InvalidArtistIdException {
        if (songDTO.getTitle() == null || songDTO.getTitle().isEmpty() || songDTO.getGenre() == null || songDTO.getDurationInMinutes() == null) {
            throw new InvalidArgumentsException();
        }

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new InvalidArtistIdException(artistId));

        songDTO.setArtist(artistId);

        this.songRepository.save(new Song(
                songDTO.getTitle(),
                songDTO.getDurationInMinutes(),
                songDTO.getReleaseDate(),
                songDTO.getGenre(),
                artist
        ));

        return Optional.of(songDTO);
    }

    @Override
    public Optional<SongDTO> getSongByLongestDurationFromArtist(Long artistId, Genre genre) {
        Artist a = this.artistRepository.findById(artistId).orElseThrow(() -> new InvalidArtistIdException(artistId));

        Song s = this.songRepository.findSongByLongestDuration(a, genre);

        SongDTO songDTO = new SongDTO(
                s.getId(),
                s.getTitle(),
                s.getDurationInMinutes(),
                s.getReleaseDate(),
                s.getGenre(),
                s.getArtist().getId(),
                s.getPlaylists().stream().map(Playlist::getId).toList()
        );

        return Optional.of(songDTO);
    }

    public List<SongDTO> getFirstThreeSongs() {
        List<Song> songs =  this.songRepository.getFirstThreeSongsWithSpecifiedQuery();

        List<SongDTO> songDTOs = new ArrayList<>();
        for (Song song : songs) {
            SongDTO DTO = new SongDTO(
                    song.getId(),
                    song.getTitle(),
                    song.getDurationInMinutes(),
                    song.getReleaseDate(),
                    song.getGenre(),
                    song.getArtist().getId(),
                    song.getPlaylists().stream().map(Playlist::getId).toList());
            songDTOs.add(DTO);
        }
        return songDTOs;
    }
}
