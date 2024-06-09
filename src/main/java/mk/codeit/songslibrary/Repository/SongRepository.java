package mk.codeit.songslibrary.Repository;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.Enumerations.Genre;
import mk.codeit.songslibrary.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {

    @Query(" SELECT s " +
            "FROM Song s " +
            "WHERE s.artist = (:artist) and s.genre = (:genre) and s.durationInMinutes = (" +
            "  SELECT MAX(s2.durationInMinutes) FROM Song s2 " +
            "  WHERE s2.artist = :artist AND s2.genre = :genre" +
            ")")
    Song findSongByLongestDuration(@Param ("artist") Artist a, @Param ("genre")Genre genre);

    @Query(" SELECT s " +
            "FROM Song s " +
            "WHERE s.durationInMinutes BETWEEN 5 and 10 " +
            "ORDER BY s.durationInMinutes DESC " +
            "LIMIT 3")
    List<Song> getFirstThreeSongsWithSpecifiedQuery();
}
