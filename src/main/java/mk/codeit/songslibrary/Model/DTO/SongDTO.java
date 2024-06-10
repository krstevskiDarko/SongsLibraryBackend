package mk.codeit.songslibrary.Model.DTO;

import lombok.Data;
import mk.codeit.songslibrary.Model.Enumerations.Genre;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class SongDTO {

    private Long id;
    private String title;
    private Long artist;
    private Genre genre;
    private List<Long> playlists;
    private Integer durationInMinutes;
    private LocalDate releaseDate;

    public SongDTO() {}

    public SongDTO(Long id, String title, Integer durationInMinutes, LocalDate releaseDate, Genre genre , Long artist, List<Long> playlists) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.playlists = playlists;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
        this.releaseDate = releaseDate;
    }

    public SongDTO(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

}
