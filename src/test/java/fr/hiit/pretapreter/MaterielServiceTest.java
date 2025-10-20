package fr.hiit.pretapreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hiit.pretapreter.service.MaterielService;

@SpringBootTest
public class MaterielServiceTest {

    @Autowired
    private MaterielService materielService;

    @Test
    void testCreeMateriel() {
        var materiel = materielService.creeMateriel(
                "Ordinateur Portable",
                "Dell XPS 13",
                "Bon",
                null,
                "Informatique",
                null
        );  

        assertNotNull(materiel);
        assertEquals("Ordinateur Portable", materiel.getNom());
    }
    
}
