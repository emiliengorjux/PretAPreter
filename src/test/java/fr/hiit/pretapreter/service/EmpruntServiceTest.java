package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.model.entity.Emprunt;
import fr.hiit.pretapreter.model.entity.Materiel;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import fr.hiit.pretapreter.repository.EmpruntRepository;
import fr.hiit.pretapreter.repository.MaterielRepository;
import fr.hiit.pretapreter.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpruntServiceTest extends AbstractServiceTest {

    @Mock
    private EmpruntRepository empruntRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private MaterielRepository materielRepository;

    @InjectMocks
    private EmpruntServiceImpl empruntServiceImpl;

    @Test
    void should_create_Emprunt() {
        // Given
        Utilisateur utilisateurPourCree = new Utilisateur();
        utilisateurPourCree.setId(1L);

        Materiel materielPourCree = new Materiel();
        materielPourCree.setId(2L);

        Emprunt empruntCree = new Emprunt();
        empruntCree.setUtilisateur(utilisateurPourCree);
        empruntCree.setMateriel(materielPourCree);
        empruntCree.setDateEmprunt(stringToLocalDate("2025-10-21"));
        empruntCree.setRetourPrevu(stringToLocalDate("2025-10-28"));

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateurPourCree));
        when(materielRepository.findById(2L)).thenReturn(Optional.of(materielPourCree));
        when(empruntRepository.save(any(Emprunt.class))).thenReturn(empruntCree);

        // When
        EmpruntDto result = empruntServiceImpl.createEmprunt(
                1L, 2L,
                stringToLocalDate("2025-10-21"),
                stringToLocalDate("2025-10-28")
        );

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getUtilisateurId());
        assertEquals(2L, result.getMaterielId());
        assertEquals(stringToLocalDate("2025-10-21"), result.getDateEmprunt());
        assertEquals(stringToLocalDate("2025-10-28"), result.getRetourPrevu());
        System.out.println("Emprunt cree avec succès !" + empruntCree);

}


@Test
    void should_update_Emprunt() {
        // Given

        // When

        // Then
    }

    @Test
    void should_delete_Emprunt() {
        // Given
        Emprunt empruntCreeASuppr = new Emprunt();

        // When

        // Then
    }

    @Test
    void should_find_Emprunt_By_Id() {
        // Given

        // When

        // Then
    }

    @Test
    void should_find_All_Emprunts() {
        // Given

        // When

        // Then
    }

    @Test
    void should_find_All_By_UtilisateurId() {
        // Given

        // When

        // Then
    }

    @Test
    void should_find_All_By_MaterielId() {
        // Given

        // When

        // Then
    }
}