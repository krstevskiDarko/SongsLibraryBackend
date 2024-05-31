package mk.codeit.songslibrary.Model.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import mk.codeit.songslibrary.Model.Song;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class PlaylistDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate dateCreated;
    private Boolean statusPublic;
    private List<Long> songs;

    public PlaylistDTO(){}

    public PlaylistDTO(String name, LocalDate dateCreated, Boolean statusPublic, List<Long> songs) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.statusPublic = statusPublic;
        this.songs = songs;
    }
}
