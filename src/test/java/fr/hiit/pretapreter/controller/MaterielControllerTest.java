package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.MaterielDto;
import fr.hiit.pretapreter.service.MaterielService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class MaterielControllerTest extends AbstractControllerTest {

    @Mock
    private MaterielService materielService;

    @InjectMocks
    private MaterielController materielController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(materielController).build();
    }

    @Test
    void should_get_all_materiel() throws Exception {
        //Given
        MaterielDto materielDto1 = new MaterielDto();
        materielDto1.setId(1L);
        MaterielDto materielDto2 = new MaterielDto();
        materielDto2.setId(2L);

        List<MaterielDto> materielDtoList = Arrays.asList(materielDto1, materielDto2);

        //When

        doReturn(materielDtoList).when(materielService).findAllMateriels();

        //Then

        mockMvc.perform(get("/materiels")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));


    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        MaterielDto materielDto1 = new MaterielDto();
        materielDto1.setId(1L);
        materielDto1.setNom("Switch,  console de jeu");

        //When
        when(materielService.findMaterielById(1L)).thenReturn(materielDto1);

        //Then
        mockMvc.perform(get("/materiels/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

    }

    @Test
    void should_create_materiel() throws Exception {
        //Given

        MaterielDto materielDtoCree1 = new MaterielDto();
        materielDtoCree1.setId(1L);
        materielDtoCree1.setNom("Ecran");
        materielDtoCree1.setReference("LG");
        materielDtoCree1.setEtatMateriel("Bon");
        materielDtoCree1.setCategorie("informatique");
        materielDtoCree1.setDateAjout(String.valueOf(LocalDateTime.of(2025, 10,13, 12, 0)));

        //When
        when(materielService.creeMateriel(any(MaterielDto.class))).thenReturn(materielDtoCree1);

        //Then

        mockMvc.perform(post("/materiels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(materielDtoCree1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Ecran")))
                .andExpect(jsonPath("$.reference", is("LG")))
                .andExpect(jsonPath("$.etatMateriel", is("Bon")))
                .andExpect(jsonPath("$.categorie", is("informatique")))
                .andExpect(jsonPath("$.dateAjout").value("2025-10-13T12:00"));

        verify(materielService, times(1)).creeMateriel(any(MaterielDto.class));


    }

    @Test
    void should_update_materiel() throws Exception {
        //Given
        MaterielDto materielDtoIncorrect = new MaterielDto();
        materielDtoIncorrect.setId(1L);
        materielDtoIncorrect.setNom("Ecroune");
        materielDtoIncorrect.setReference("LAG");
        materielDtoIncorrect.setEtatMateriel("Bwien");
        materielDtoIncorrect.setCategorie("information");
        materielDtoIncorrect.setDateAjout(String.valueOf(LocalDateTime.of(2015, 5,1,23, 50)));

        MaterielDto materielDtoMaj =  new MaterielDto();
        materielDtoMaj.setId(1L);
        materielDtoMaj.setNom("Ecran");
        materielDtoMaj.setReference("LG");
        materielDtoMaj.setEtatMateriel("Bon");
        materielDtoMaj.setCategorie("informatique");
        materielDtoMaj.setDateAjout(String.valueOf(LocalDateTime.of(2025, 10,13, 12, 0)));

        //When

        when(materielService.updateMateriel(any(MaterielDto.class))).thenReturn(materielDtoMaj);

        //Then

        mockMvc.perform(put("/materiels/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(materielDtoIncorrect)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Ecran"))
                .andExpect(jsonPath("$.reference").value("LG"))
                .andExpect(jsonPath("$.etatMateriel").value("Bon"))
                .andExpect(jsonPath("$.categorie").value("informatique"))
                .andExpect(jsonPath("$.dateAjout").value("2025-10-13T12:00"));


    }

    @Test
    void should_delete_materiel() throws Exception {
        // Given
        Long materielId = 1L;

        // When
        doNothing().when(materielService).deleteMateriel(materielId);

        // Then
        mockMvc.perform(delete("/materiels/{id}", materielId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(materielService, times(1)).deleteMateriel(materielId);
    }
}