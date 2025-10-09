package fr.hiit.pretapreter;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hiit.pretapreter.service.EmpruntService;

@SpringBootTest
public class EmpruntServiceTest {

    @Autowired
    private EmpruntService empruntService;

    @Test
    void testCreateEmprunt() {
        var emprunt = empruntService.createEmprunt(
                "Jean Dupont",
                1L,
                LocalDate.of(2025, 11, 10),
                LocalDate.of(2025, 11, 15)
        );

        assertNotNull(emprunt);
        assertEquals("Jean Dupont", emprunt.getEmprunteur());
    }
}
