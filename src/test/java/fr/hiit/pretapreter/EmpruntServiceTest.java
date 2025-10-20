/*
package fr.hiit.pretapreter;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hiit.pretapreter.service.repository.model.EmpruntServiceImpl;

@SpringBootTest
public class EmpruntServiceTest {

    @Autowired
    private EmpruntServiceImpl empruntService;

           @Test
    void testMaterielExistantEmprunt() {
        var emprunt = empruntService.createEmprunt(
                "Jean Dupont",
                999L,
                LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 11, 15)
        );

        assertNotNull(emprunt);
        assertEquals("Jean Dupont", emprunt.getEmprunteur());
    }

    @Test
    void testCreateEmprunt() {
        var emprunt = empruntService.createEmprunt(
                "Jean Dupont",
                1L,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 11, 15)
        );

        assertNotNull(emprunt);
        assertEquals("Jean Dupont", emprunt.getEmprunteur());
    }

        @Test
    void testDateAnterieurEmprunt() {
        var emprunt = empruntService.createEmprunt(
                "Jean Dupont",
                4L,
                LocalDate.of(2025, 11, 20),
                LocalDate.of(2025, 10, 15)
        );

        assertNotNull(emprunt);
        assertEquals("Jean Dupont", emprunt.getEmprunteur());
    }

         @Test
    void testDateChevauchementEmprunt() {
        var emprunt = empruntService.createEmprunt(
                "Jean Dupont",
                1L,
                LocalDate.of(2025, 10, 07),
                LocalDate.of(2025, 10, 10)
        );

        assertNotNull(emprunt);
        assertEquals("Jean Dupont", emprunt.getEmprunteur());
    }


}
*/
