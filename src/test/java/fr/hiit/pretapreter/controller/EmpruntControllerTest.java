package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.service.EmpruntService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmpruntControllerTest extends AbstractControllerTest {


    @Mock
    private EmpruntService empruntService;

    @InjectMocks
    private EmpruntController empruntController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(empruntController).build();
    }

    @Test
    void should_create_emprunt() throws Exception {
        // GIVEN
        EmpruntDto emprunt1 = new EmpruntDto();
        emprunt1.setId(1L);
        emprunt1.setUtilisateurId(3L);
        emprunt1.setMaterielId(5L);
        emprunt1.setDateEmprunt(LocalDate.now());
        emprunt1.setRetourPrevu(LocalDate.now().plusDays(7));

        when(empruntService.createEmprunt(
                any(EmpruntDto.class))
        ).thenReturn(emprunt1);

        // WHEN + THEN
        mockMvc.perform(post("/emprunts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emprunt1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.utilisateurId").value(3))
                .andExpect(jsonPath("$.materielId").value(5));
    }


    @Test
    void should_get_emprunt_by_id() throws Exception {
        // GIVEN
        EmpruntDto emprunt1 = new EmpruntDto();
        emprunt1.setId(1L);

        when(empruntService.findEmpruntById(1L)).thenReturn(emprunt1);

        // WHEN + THEN
        mockMvc.perform(get("/emprunts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void should_get_all_emprunts() throws Exception {
        // GIVEN
        EmpruntDto emprunt1 = new EmpruntDto();
        emprunt1.setId(1L);
        emprunt1.setUtilisateurId(3L);
        emprunt1.setMaterielId(5L);
        emprunt1.setDateEmprunt(LocalDate.now());
        emprunt1.setRetourPrevu(LocalDate.now().plusDays(7));

        EmpruntDto emprunt2 = new EmpruntDto();
        emprunt2.setId(2L);
        emprunt2.setUtilisateurId(2L);
        emprunt2.setMaterielId(6L);
        emprunt2.setDateEmprunt(LocalDate.now());
        emprunt2.setRetourPrevu(LocalDate.now().plusDays(7));

        List<EmpruntDto> emprunts = List.of(emprunt1, emprunt2);
        when(empruntService.findAllEmprunts()).thenReturn(emprunts);

        // WHEN + THEN
        mockMvc.perform(get("/emprunts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].materielId").value(5))
                .andExpect(jsonPath("$[0].utilisateurId").value(3))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].materielId").value(6))
                .andExpect(jsonPath("$[1].utilisateurId").value(2));
    }

    @Test
    void should_get_emprunts_by_utilisateur() throws Exception {
        // GIVEN
        Long utilisateurId = 1L;
        EmpruntDto emprunt1 = new EmpruntDto();
        emprunt1.setUtilisateurId(utilisateurId);
        List<EmpruntDto> emprunts = List.of(emprunt1);

        when(empruntService.findAllByUtilisateurId(utilisateurId)).thenReturn(emprunts);

        // WHEN + THEN
        mockMvc.perform(get("/emprunts/utilisateur/{id}", utilisateurId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].utilisateurId").value(utilisateurId));
    }

    @Test
    void should_get_emprunts_by_materiel() throws Exception {
        // GIVEN
        Long materielId = 1L;
        EmpruntDto emprunt1 = new EmpruntDto();
        emprunt1.setMaterielId(materielId);
        List<EmpruntDto> emprunts = List.of(emprunt1);

        when(empruntService.findAllByMaterielId(materielId)).thenReturn(emprunts);

        // WHEN + THEN
        mockMvc.perform(get("/emprunts/materiel/{id}", materielId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].materielId").value(materielId));
    }

    @Test
    void should_update_emprunt() throws Exception {

        // GIVEN
        EmpruntDto emprunt = new EmpruntDto();
        emprunt.setId(1L);
        emprunt.setUtilisateurId(4L);
        emprunt.setMaterielId(8L);

        EmpruntDto empruntModif = new EmpruntDto();
        empruntModif.setId(1L);
        empruntModif.setUtilisateurId(2L);
        empruntModif.setMaterielId(6L);

        // WHEN + THEN
        when(empruntService.updateEmprunt(any(EmpruntDto.class))).thenReturn(empruntModif);

        // WHEN + THEN
        mockMvc.perform(put("/emprunts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emprunt)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.materielId").value(6))
                .andExpect(jsonPath("$.utilisateurId").value(2));
    }

    @Test
    void should_delete_emprunt() throws Exception {
        // GIVEN
        Long empruntId = 1L;
        doNothing().when(empruntService).deleteEmprunt(empruntId);

        // WHEN + THEN
        mockMvc.perform(delete("/emprunts/{id}", empruntId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(empruntService, times(1)).deleteEmprunt(empruntId);
    }
}
