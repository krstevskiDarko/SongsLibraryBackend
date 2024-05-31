package mk.codeit.songslibrary.Repository;

import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    /** Didn't manage to find a way to order by a.name and a.dateOfBirth
     * without returning List<Object[]> and the query would've looked like SELECT p, a.name, a.dateOfBirth :(
     */
    @Query("SELECT DISTINCT p " +
            "FROM Playlist p " +
            "JOIN p.songs sp " +
            "JOIN sp.artist a " +
            "WHERE a = (:artist) ")
    List<Playlist> findPlaylistsWithSongsByArtist(@Param("artist") Artist artist);


    @Query("SELECT p " +
            "FROM Playlist p " +
            "WHERE p.statusPublic = true AND SIZE(p.songs)<=3")
    List<Playlist> getAllPlaylistsWhichArePublicAndContainMaxOfThreeSongs();
}
