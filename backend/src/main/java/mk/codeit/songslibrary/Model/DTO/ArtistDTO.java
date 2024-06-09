package mk.codeit.songslibrary.Model.DTO;

import lombok.Data;

import java.time.LocalDate;

import java.util.List;

@Data
public class ArtistDTO {
    private Long id;
    private String name;
    private String artisticName;
    private LocalDate dateOfBirth;
    private String nationality;
    private List<Long> songs;

    private List<SongDTO> songDTOS;

    public ArtistDTO (Long id,String name, String artisticName, String nationality, LocalDate dateOfBirth, List<SongDTO> songDTOS) {
        this.id = id;
        this.name = name;
        this.artisticName = artisticName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.songDTOS = songDTOS;
    }
    public ArtistDTO(){}

    public ArtistDTO(String name, String artisticName) {
        this.name = name;
        this.artisticName = artisticName;
    }

    public ArtistDTO(Long id, String s, String s1, LocalDate of, String macedonian) {
        this.id = id;
        this.name = s;
        this.artisticName = s1;
        this.dateOfBirth = of;
        this.nationality = macedonian;
    }

}
