package fr.hiit.pretapreter.controllertest;

import fr.hiit.pretapreter.service.MaterielService;
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
@WebMvcTest(MaterielController.class)
class MaterielControllerTest {

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
    void should_Get_All_Materiel() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Get_By_Id() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Create_Materiel() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Update_Materiel() {


        //Given

        //When

        //Then
    }

    @Test
    void should_Delete_Materiel() {


        //Given

        //When

        //Then
    }
}