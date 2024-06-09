
package mk.codeit.songslibrary.serviceLayerTest;
import mk.codeit.songslibrary.Model.DTO.ArtistDTO;
import mk.codeit.songslibrary.Web.ArtistController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.singletonList;
import static javax.xml.transform.OutputKeys.VERSION;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
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

    @Test
    public void getMacedonians() throws Exception {
        Long artistId = 1L;
        ArtistDTO artistDTO = new ArtistDTO();
        ArtistDTO arrival = new ArtistDTO(artistId,"1","1", LocalDate.of(1999,1,1),"Macedonian");
        artistDTO.setNationality("Macedonian");

        List<ArtistDTO> allArrivals = singletonList(arrival);

        given(artistController.getMacedonianArtist()).willReturn(allArrivals);

        mvc.perform(get("/api/artist/macedonians")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(arrival.getName())));
    }

    @Test
    public void getArrivalsById() throws Exception {
        Arrival arrival = new Arrival();
        arrival.setCity("Yerevan");

        given(arrivalController.getArrivalById(arrival.getId())).willReturn(arrival);

        mvc.perform(get(VERSION + ARRIVAL + arrival.getId())
                        .with(user("blaze").password("Q1w2e3r4"))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("city", is(arrival.getCity())));
    }
}