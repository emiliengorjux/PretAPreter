package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.presentation.dto.UtilisateurDto;
import fr.hiit.pretapreter.service.repository.entity.Utilisateur;
import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    // Créer un utilisateur
    UtilisateurDto createUtilisateur(UtilisateurDto utilisateur);

    // Récupérer un utilisateur par ID
    Optional<Utilisateur> getUtilisateurById(Long id);

    // Récupérer tous les utilisateurs
    List<Utilisateur> getAllUtilisateurs();

    // Mettre à jour un utilisateur
    UtilisateurDto updateUtilisateur(Long id, UtilisateurDto utilisateur);

    // Supprimer un utilisateur
    void deleteUtilisateur(Long id);

    // Récupérer les emprunts d’un utilisateur avec le matériel associé
    List<Utilisateur> getUtilisateursWithEmprunts();
}