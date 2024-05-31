package mk.codeit.songslibrary.web;

import mk.codeit.songslibrary.Web.ArtistController;
import mk.codeit.songslibrary.Web.PlaylistController;
import mk.codeit.songslibrary.Web.SongController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestingWebApplicationTests {

    @Autowired
    private ArtistController artistController;

    @Autowired
    private SongController songController;

    @Autowired
    private PlaylistController playlistController;

    @Test
    void contextLoadsArtist() throws Exception {
        assertThat(artistController).isNotNull();
    }
    @Test
    void contextLoadsSong() throws Exception {
        assertThat(songController).isNotNull();
    }
    @Test
    void contextLoadsPlaylist() throws Exception {
        assertThat(playlistController).isNotNull();
    }
}
