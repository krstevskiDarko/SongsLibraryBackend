package mk.codeit.songslibrary.Repository;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.ArtistDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {

    Artist findArtistByNameLike(String name);

    @Query(" SELECT a " +
            "FROM Artist a " +
            "WHERE a.nationality LIKE '%Macedonian' AND a.dateOfBirth < TO_DATE('1999-01-01', 'YYYY-MM-DD')")
    List<Artist> findArtistFromMacedoniaAndBornBefore1999();
}
