package fr.hiit.pretapreter.controllertest;

import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import fr.hiit.pretapreter.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
//@SpringBootTest
@MockitoBean
@WebMvcTest(UtilisateurController.class)
class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() { // mockMvc = classe propre a spring, simuler des requetes Http
        // standalone, seul le controlleur sera chargé, build construit l'instance
        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
    }


    @Test
    void should_Get_All_Utilisateurs() throws Exception {
        //Given
        UtilisateurDto utilisateur1 = new UtilisateurDto();
        utilisateur1.setNom("Mosbah");
        UtilisateurDto utilisateur2 = new UtilisateurDto();
        utilisateur2.setNom("Gorjux");
        List<UtilisateurDto> utilisateurslist = Arrays.asList(utilisateur1, utilisateur2);


        //When
        when(utilisateurService.getAllUtilisateurs(anyString())).thenReturn(utilisateurslist);


        //Then

        mockMvc.perform(get("/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("Mosbah")))
                //andExpect verifie le nom forunit dans value
                .andExpect(jsonPath("$[1].nom", is("Gorjux")));
                                            // $ signgifie la racine du document JSON


        verify(utilisateurService, times(2)).getAllUtilisateurs(anyString());
    }

    @Test
    void should_Get_Utilisateur_By_Id() throws Exception {
        //Given
        UtilisateurDto utilisateur1 = new UtilisateurDto();
        utilisateur1.setId(1L);
        utilisateur1.setPrenom("Emilien");


        //When
        when(utilisateurService.getUtilisateurById(1L)).thenReturn(utilisateur1);

        // Erreur de utilisateur, devrait retourner DTO ?? /!\ poser la question.


        //Then

        mockMvc.perform(get("/utilisateurs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("Emilien")));


        verify(utilisateurService, times(1)).getUtilisateurById(1L);


    }

    @Test
    void should_Create_Utilisateur() throws Exception {
        //Given
        Utilisateur utilisateurCree = new Utilisateur("Colle", "A bois", "colleabois@gmail.com", "admin");

        //When
        when(utilisateurService.createUtilisateur(any(UtilisateurDto.class))).thenReturn(utilisateurCree);


        //Then

        mockMvc.perform(post("/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", is("Colle")))
                .andExpect(jsonPath("$.prenom", is("A bois")))
                .andExpect(jsonPath("$.email", is("colleabois@gmail.com")))
                .andExpect(jsonPath("$.role", is("admin")));

        verify(utilisateurService).createUtilisateur(any(UtilisateurDto.class));
    }

    @Test
    void should_Update_Utilisateur() throws Exception {
        //Given
        Utilisateur utilisateurMaj = new Utilisateur("Terases", "preskill", "terasespreskill@gmail.com", "user");


        //When
        when(utilisateurService.updateUtilisateur(utilisateurMaj(UtilisateurDto.class))).thenReturn(utilisateurMaj);

        //Then
        mockMvc.perform(put("/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", is("Terrasse")))
                .andExpect(jsonPath("$.prenom", is("Presqu'ile")))
                .andExpect(jsonPath("$.email", is("terrassepresquile@gmail.com")))
                .andExpect(jsonPath("$.role", is("admin")));

        verify((utilisateurService).updateUtilisateur(any(UtilisateurDto.class));
    }

    @Test
    void should_Delete_Utilisateur() throws Exception {
        //Given

        //When

        //Then
    }
}