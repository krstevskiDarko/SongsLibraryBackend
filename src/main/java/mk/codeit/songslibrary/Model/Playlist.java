package mk.codeit.songslibrary.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateCreated;

    @Column(nullable = false)
    private Boolean statusPublic;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "song_playlist",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs = new ArrayList<>();

    public Playlist() {}

    public Playlist(String name, LocalDate dateCreated, Boolean statusPublic, List<Song> songs) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.statusPublic = statusPublic;
        this.songs = songs;
    }

}
