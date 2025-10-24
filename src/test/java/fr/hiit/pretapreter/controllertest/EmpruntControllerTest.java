package fr.hiit.pretapreter.controllertest;

import fr.hiit.pretapreter.service.EmpruntService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
// @SpringBootTest
@MockitoBean
@WebMvcTest(UtilisateurController.class)

class EmpruntControllerTest {

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
    void should_Create_Emprunt() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Get_Emprunt() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Get_All_Emprunts() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Get_Emprunts_By_Utilisateur() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Get_Emprunts_By_Materiel() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Update_Emprunt() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Delete_Emprunt() {


        //Given

        //When

        //Then
    }
}