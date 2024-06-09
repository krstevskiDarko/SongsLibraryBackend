package mk.codeit.songslibrary.Service.Implementations;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.ArtistDTO;
import mk.codeit.songslibrary.Model.DTO.SongDTO;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArgumentsException;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArtistIdException;
import mk.codeit.songslibrary.Model.Playlist;
import mk.codeit.songslibrary.Model.Song;
import mk.codeit.songslibrary.Repository.ArtistRepository;
import mk.codeit.songslibrary.Repository.SongRepository;
import mk.codeit.songslibrary.Service.ArtistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> getArtistById(Long id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public Optional<Artist> getArtistByName(String artistName) {
        return Optional.of(this.artistRepository.findArtistByNameLike(artistName));
    }

    @Override
    public Optional<Artist> saveArtist(ArtistDTO artist) {
        if(artist == null || artist.getName() == null || artist.getName().isEmpty() || artist.getArtisticName() == null || artist.getArtisticName().isEmpty()
               || artist.getNationality() == null || artist.getNationality().isEmpty() || artist.getDateOfBirth() == null
        ){
            throw new InvalidArgumentsException();
        }

        Artist a = this.artistRepository.save(new Artist(
                artist.getName(),
                artist.getArtisticName(),
                artist.getDateOfBirth(),
                artist.getNationality()
        ));
        return Optional.of(a);
    }

    @Override
    public List<ArtistDTO> getMacedonianArtists() {
        List<Artist> artists = this.artistRepository.findArtistFromMacedoniaAndBornBefore1999();

        return artists.stream().map(a -> new ArtistDTO(a.getName(), a.getArtisticName())).toList();
    }

        @Override
        public Optional<ArtistDTO> getSpecifiedArtist(Long id) {
            Artist a = this.artistRepository.findById(id).orElseThrow(() -> new InvalidArtistIdException(id));

            List<Song> songs = a.getSongs()
                    .stream()
                    .sorted(Comparator.comparing(Song::getTitle).reversed())
                    .toList();

            List<String> songTitles = songs
                    .stream()
                    .map(Song::getTitle)
                    .toList();
            List<LocalDate> songDates = songs
                    .stream()
                    .map(Song::getReleaseDate)
                    .toList();

            List<SongDTO> songDTOS = new ArrayList<>();
            for(Song s: songs){
                songDTOS.add(new SongDTO(s.getTitle(), s.getReleaseDate()));
            }

            return Optional.of(new ArtistDTO(a.getId(),a.getName(), a.getArtisticName(), a.getNationality(), a.getDateOfBirth(), songDTOS));
        }

    @Override
    public Optional<ArtistDTO> deleteArtistById(Long id) {
        Artist artist  = this.artistRepository.findById(id)
                .orElseThrow(()-> new InvalidArtistIdException(id));

        for(Song song : artist.getSongs()){
            song.setArtist(null);
            for(Playlist playlist : song.getPlaylists()){
                playlist.getSongs().remove(song);
            }
        }

        this.artistRepository.deleteById(id);

        return Optional.of(new ArtistDTO(artist.getName(),artist.getArtisticName()));
    }


}
