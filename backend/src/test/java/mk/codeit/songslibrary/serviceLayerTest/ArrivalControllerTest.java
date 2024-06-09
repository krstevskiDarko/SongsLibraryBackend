
package mk.codeit.songslibrary.serviceLayerTest;
import mk.codeit.songslibrary.Model.Artist;
import mk.codeit.songslibrary.Model.DTO.ArtistDTO;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArgumentsException;
import mk.codeit.songslibrary.Model.Exceptions.InvalidArtistIdException;
import mk.codeit.songslibrary.Model.Song;
import mk.codeit.songslibrary.Repository.ArtistRepository;
import mk.codeit.songslibrary.Service.Implementations.ArtistServiceImpl;
import mk.codeit.songslibrary.Web.ArtistController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
public class ArrivalControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArtistController artistController;

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    public void testAddNewArtist() throws Exception {
        // Arrange
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName("Test Artist");
        artistDTO.setArtisticName("Test Artistic Name");
        artistDTO.setNationality("Test Nationality");
        artistDTO.setDateOfBirth(LocalDate.now());

        when(artistRepository.save(ArgumentMatchers.any())).thenReturn(new Artist());

        artistService.saveArtist(artistDTO);

        verify(artistRepository, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testAddNewArtist_NullDTO() {
        ArtistDTO artistDTO = null;

        assertThrows(InvalidArgumentsException.class, () -> artistService.saveArtist(artistDTO));
    }

    @Test
    public void testGetSpecifiedArtist() {
        Long artistId = 1L;
        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setName("Test Artist");
        artist.setArtisticName("Test Artistic Name");
        artist.setNationality("Test Nationality");
        artist.setDateOfBirth(LocalDate.now());
        List<Song> songs = new ArrayList<>();
        Song song1 = new Song();
        song1.setTitle("Song 1");
        song1.setReleaseDate(LocalDate.now());
        Song song2 = new Song();
        song2.setTitle("Song 2");
        song2.setReleaseDate(LocalDate.now());
        songs.add(song1);
        songs.add(song2);
        artist.setSongs(songs);

        when(artistRepository.findById(artistId)).thenReturn(Optional.of(artist));

        Optional<ArtistDTO> artistDTO = artistService.getSpecifiedArtist(artistId);

        assertEquals(artistId, artistDTO.get().getId());
        assertEquals(artist.getName(), artistDTO.get().getName());
        assertEquals(artist.getArtisticName(), artistDTO.get().getArtisticName());
        assertEquals(artist.getNationality(), artistDTO.get().getNationality());
        assertEquals(artist.getDateOfBirth(), artistDTO.get().getDateOfBirth());
    }

    @Test
    public void testGetSpecifiedArtist_InvalidId() {
        Long invalidArtistId = 100L;
        when(artistRepository.findById(invalidArtistId)).thenReturn(Optional.empty());

        assertThrows(InvalidArtistIdException.class, () -> artistService.getSpecifiedArtist(invalidArtistId));
    }


    @Test
    public void testGetMacedonians() throws Exception {
        Long artistId = 1L;
        ArtistDTO artist2 = new ArtistDTO(artistId,"1","1", LocalDate.of(1999,1,1),"English");
        ArtistDTO arrival = new ArtistDTO(artistId,"1","1", LocalDate.of(1999,1,1),"Macedonian");


        List<ArtistDTO> allArrivals = List.of(arrival);

        given(artistController.getMacedonianArtist()).willReturn(allArrivals);

        mvc.perform(get("/api/artist/macedonians")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(arrival.getName())));
    }

    /**
     * Most of my testing were manual. I sent HTTP requests with Postman to Test my API points.
     */
}