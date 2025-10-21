package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.model.entity.Emprunt;

import java.time.LocalDate;
import java.util.List;

public interface EmpruntService {

    // Créer un emprunt pour un utilisateur et un matériel
    EmpruntDto createEmprunt(Long utilisateurId, Long materielId, LocalDate dateEmprunt, LocalDate dateRetourPrevu);

    // Mettre à jour un emprunt existant
    EmpruntDto updateEmprunt(EmpruntDto emprunt);

    // Supprimer un emprunt par ID
    void deleteEmprunt(Long id);


    // Trouver un emprunt par ID
    EmpruntDto findEmpruntById(Long id);

    // Lister tous les emprunts
    List<EmpruntDto> findAllEmprunts();

    // Lister les emprunts d'un utilisateur
    List<EmpruntDto> findAllByUtilisateurId(Long utilisateurId);

    // Lister les emprunts pour un matériel spécifique
    List<EmpruntDto> findAllByMaterielId(Long materielId);
}
