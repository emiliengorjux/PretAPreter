/*
package fr.hiit.pretapreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.hiit.pretapreter.service.repository.model.MaterielServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MaterielServiceTest {

    @Autowired
    private MaterielServiceImpl materielService;

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
*/
