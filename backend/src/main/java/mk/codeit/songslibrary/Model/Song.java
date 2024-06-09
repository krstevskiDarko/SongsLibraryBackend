package mk.codeit.songslibrary.Model;

import jakarta.persistence.*;
import lombok.Data;
import mk.codeit.songslibrary.Model.Enumerations.Genre;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany(mappedBy = "songs", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Playlist> playlists;

    public Song() {}

    public Song(String title, Integer durationInMinutes, LocalDate releaseDate, Genre genre, Artist artist) {
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.artist = artist;
    }

    public Song(String title, Integer durationInMinutes, LocalDate releaseDate, Artist artist, Genre genre, List<Playlist> playlists) {
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.genre = genre;
        this.playlists = playlists;
    }

}
