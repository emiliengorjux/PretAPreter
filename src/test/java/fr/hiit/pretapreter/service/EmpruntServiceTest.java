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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        // --- GIVEN ---
        Utilisateur utilisateurPourCree = new Utilisateur();
        utilisateurPourCree.setId(1L);

        Materiel materielPourCree = new Materiel();
        materielPourCree.setId(2L);

        Emprunt empruntCree = new Emprunt();
        empruntCree.setId(10L);
        empruntCree.setUtilisateur(utilisateurPourCree);
        empruntCree.setMateriel(materielPourCree);
        empruntCree.setDateEmprunt(stringToLocalDate("2025-10-21"));
        empruntCree.setRetourPrevu(stringToLocalDate("2025-10-28"));

        EmpruntDto empruntDto = new EmpruntDto();
        empruntDto.setUtilisateurId(1L);
        empruntDto.setMaterielId(2L);
        empruntDto.setDateEmprunt(stringToLocalDate("2025-10-21"));
        empruntDto.setRetourPrevu(stringToLocalDate("2025-10-28"));

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateurPourCree));
        when(materielRepository.findById(2L)).thenReturn(Optional.of(materielPourCree));
        when(empruntRepository.save(any(Emprunt.class))).thenReturn(empruntCree);

        // --- WHEN ---
        EmpruntDto result = empruntServiceImpl.createEmprunt(empruntDto);

        // --- THEN ---
        assertNotNull(result);
        assertEquals(1L, result.getUtilisateurId());
        assertEquals(2L, result.getMaterielId());
        assertEquals(stringToLocalDate("2025-10-21"), result.getDateEmprunt());
        assertEquals(stringToLocalDate("2025-10-28"), result.getRetourPrevu());

        System.out.println("Emprunt créé avec succès : " + result);
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

        Long empruntId = 1L;
        Emprunt empruntCreeASuppr = new Emprunt();
        empruntCreeASuppr.setUtilisateur(new Utilisateur());
        empruntCreeASuppr.setMateriel(new Materiel());
        empruntCreeASuppr.setDateEmprunt(stringToLocalDate("2025-10-21"));
        empruntCreeASuppr.setRetourPrevu(stringToLocalDate("2025-10-28"));

        // When

        when(empruntRepository.findById(empruntId)).thenReturn(Optional.of(empruntCreeASuppr));
        doNothing().when(empruntRepository).delete(empruntCreeASuppr);

        // Then
        assertDoesNotThrow(() -> empruntServiceImpl.deleteEmprunt(empruntId));

        //verify(empruntRepository, times(1)).findById(empruntId);
        //verify(empruntRepository, times(1)).delete(empruntCreeASuppr);
        // Ici les verify sont commenté pour evité le casser l'effet boite noir des T.U
        // /!\ POSER LA QUESTION DE BONNE PRATIQUE !!

    }

    @Test
    void should_find_Emprunt_By_Id() {
        // Given
        Long empruntId = 3L;
        EmpruntDto empruntDtoById = new EmpruntDto();
        empruntDtoById.setId(empruntId);

        Emprunt empruntFoundById = new Emprunt();
        empruntFoundById.setId(empruntId);

        when(empruntRepository.findById(empruntId)).thenReturn(Optional.of(empruntFoundById));

        // When
        EmpruntDto result = empruntServiceImpl.findEmpruntById(empruntId);

        // Then
        assertNotNull(result);
        assertEquals(empruntDtoById.getId(), result.getId());
    }


    @Test
    void should_find_All_Emprunts() {
        // Given
        EmpruntDto empruntCree1 = new EmpruntDto();
        empruntCree1.setId(1L);
        EmpruntDto empruntCree2 = new EmpruntDto();
        empruntCree2.setId(2L);
        EmpruntDto empruntCree3 = new EmpruntDto();
        empruntCree3.setId(3L);

        List<EmpruntDto> empruntsCreeList = Arrays.asList(empruntCree1, empruntCree2);

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