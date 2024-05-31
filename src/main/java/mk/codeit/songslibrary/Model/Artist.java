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

    private String name;

    private String artisticName;

    private LocalDate dateOfBirth;

    private String nationality;

    @OneToMany(mappedBy = "artist")
    private List<Song> songs = new ArrayList<>();

    public Artist() {}

    public Artist(String name, String artisticName, LocalDate dateOfBirth, String nationality) {
        this.name = name;
        this.artisticName = artisticName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }
}
