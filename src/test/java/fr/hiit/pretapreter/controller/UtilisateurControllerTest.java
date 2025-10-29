package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UtilisateurControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
    }

    @Test
    void should_get_all_utilisateurs() throws Exception {
        // Given
        UtilisateurDto utilisateur1 = new UtilisateurDto();
        utilisateur1.setNom("Mosbah");
        UtilisateurDto utilisateur2 = new UtilisateurDto();
        utilisateur2.setNom("Gorjux");
        List<UtilisateurDto> utilisateursList = Arrays.asList(utilisateur1, utilisateur2);

        // When
        doReturn(utilisateursList).when(utilisateurService).getAllUtilisateurs(any());

        // Then
        mockMvc.perform(get("/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("Mosbah")))
                .andExpect(jsonPath("$[1].nom", is("Gorjux")));

        verify(utilisateurService, times(1)).getAllUtilisateurs(any());
    }

    @Test
    void should_get_utilisateur_by_id() throws Exception {
        // Given
        UtilisateurDto utilisateur1 = new UtilisateurDto();
        utilisateur1.setId(1L);
        utilisateur1.setPrenom("Emilien");

        // When
        when(utilisateurService.getUtilisateurById(1L)).thenReturn(Optional.of(utilisateur1));

        // Then
        mockMvc.perform(get("/utilisateurs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prenom", is("Emilien")));

        verify(utilisateurService, times(1)).getUtilisateurById(1L);
    }

    @Test
    void should_create_utilisateur() throws Exception {
        // Given
        UtilisateurDto utilisateurCree = new UtilisateurDto();
        utilisateurCree.setNom("Colle");
        utilisateurCree.setPrenom("A bois");
        utilisateurCree.setEmail("colleabois@gmail.com");

        // When
        when(utilisateurService.createUtilisateur(any(UtilisateurDto.class))).thenReturn(utilisateurCree);

        // Then
        mockMvc.perform(post("/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(utilisateurCree)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Colle")))
                .andExpect(jsonPath("$.prenom", is("A bois")))
                .andExpect(jsonPath("$.email", is("colleabois@gmail.com")));

        verify(utilisateurService).createUtilisateur(any(UtilisateurDto.class));
    }

    @Test
    void should_update_utilisateur() throws Exception {
        // Given
        UtilisateurDto utilisateurIncorrect = new UtilisateurDto();
        utilisateurIncorrect.setId(1L);
        utilisateurIncorrect.setNom("Terase");
        utilisateurIncorrect.setPrenom("Preskill");
        utilisateurIncorrect.setEmail("terasepreskill@gmail.com");

        UtilisateurDto utilisateurCorrige = new UtilisateurDto();
        utilisateurCorrige.setId(1L);
        utilisateurCorrige.setNom("Terrasse");
        utilisateurCorrige.setPrenom("Presqu'ile");
        utilisateurCorrige.setEmail("terrassepresquile@gmail.com");


        // When
        when(utilisateurService.updateUtilisateur(any(UtilisateurDto.class))).thenReturn(utilisateurCorrige);
        // ANY : Accepte n'importe quel objet de type UtilisateurDto comme 2eme argu.

        
        // Then
        mockMvc.perform(put("/utilisateurs/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(utilisateurIncorrect)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom", is("Terrasse")))
                .andExpect(jsonPath("$.prenom", is("Presqu'ile")))
                .andExpect(jsonPath("$.email", is("terrassepresquile@gmail.com")));


        verify(utilisateurService, times(1)).updateUtilisateur(any(UtilisateurDto.class));
    }

    @Test
    void should_delete_utilisateur() throws Exception {
        // Given
        Long utilisateurId = 1L;

        // When
        doNothing().when(utilisateurService).deleteUtilisateur(utilisateurId);

        // Then
        mockMvc.perform(delete("/utilisateurs/{id}", utilisateurId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).deleteUtilisateur(utilisateurId);
    }


}
