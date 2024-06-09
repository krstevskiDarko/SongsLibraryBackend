package mk.codeit.songslibrary.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artisticName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    public Artist() {}

    public Artist(String name, String artisticName, LocalDate dateOfBirth, String nationality) {
        this.name = name;
        this.artisticName = artisticName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }
}
