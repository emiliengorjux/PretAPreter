package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.UtilisateurDto;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    // Créer un utilisateur
    UtilisateurDto createUtilisateur(UtilisateurDto utilisateur);

    // Récupérer un utilisateur par ID
    Optional<UtilisateurDto> getUtilisateurById(Long id);

    // Récupérer tous les utilisateurs
    List<UtilisateurDto> getAllUtilisateurs();

    // Mettre à jour un utilisateur
    UtilisateurDto updateUtilisateur(Long id, UtilisateurDto utilisateur);

    // Supprimer un utilisateur
    void deleteUtilisateur(Long id);

    // Récupérer les emprunts d’un utilisateur avec le matériel associé
    List<UtilisateurDto> getUtilisateursWithEmprunts();
}