package mk.codeit.songslibrary.Model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ArtistDTO {
    private Long id;
    private String name;
    private String artisticName;
    private LocalDate dateOfBirth;
    private String nationality;
    private List<Long> songs;

    private List<String> songTitles;
    private List<LocalDate> songReleaseDate;

    public ArtistDTO(String name, String artisticName, String nationality, LocalDate dateOfBirth, List<Long> songs) {
        this.name = name;
        this.artisticName = artisticName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.songs = songs;
    }

    public ArtistDTO (Long id,String name, String artisticName, String nationality, LocalDate dateOfBirth, List<String> songTitles, List<LocalDate> songReleaseDate) {
        this.id = id;
        this.name = name;
        this.artisticName = artisticName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.songTitles=songTitles;
        this.songReleaseDate=songReleaseDate;
    }
    public ArtistDTO(){}

    public ArtistDTO(String name, String artisticName) {
        this.name = name;
        this.artisticName = artisticName;
    }

    public ArtistDTO(String s, String s1, LocalDate of, String macedonian) {
        this.name = s;
        this.artisticName = s1;
        this.dateOfBirth = of;
        this.nationality = macedonian;
    }
    public ArtistDTO(String name, String artisticName, LocalDate dateOfBirth, String nationality, List<Long> songs) {
        this.name = name;
        this.artisticName = artisticName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.songs = songs;
    }

}
