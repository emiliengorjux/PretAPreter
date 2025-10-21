package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.model.entity.Emprunt;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import fr.hiit.pretapreter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto) {
        // Conversion DTO -> entity
        Utilisateur utilisateur = UtilisateurDto.toEntity(utilisateurDto);
        // Sauvegarde en base
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        // Conversion entity -> DTO
        return UtilisateurDto.toDto(saved);
    }


    @Override
    public Optional<UtilisateurDto> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::toDto);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurDto::toDto)
                .toList();
    }


    @Override
    public UtilisateurDto updateUtilisateur(Long id, UtilisateurDto utilisateurDto) {
        return utilisateurRepository.findById(id).map(existing -> {
            existing.setNom(utilisateurDto.getNom());
            existing.setPrenom(utilisateurDto.getPrenom());
            existing.setEmail(utilisateurDto.getEmail());

            // Mettre à jour les emprunts si nécessaire
            if (utilisateurDto.getEmprunts() != null) {
                existing.getEmprunts().clear();
                for (EmpruntDto eDto : utilisateurDto.getEmprunts()) {
                    Emprunt e = EmpruntDto.toEntity(eDto);
                    e.setUtilisateur((existing));
                    existing.getEmprunts().add(e);
                }
            }

            Utilisateur saved = utilisateurRepository.save(existing);
            return UtilisateurDto.toDto(saved);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
    }


    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public List<Utilisateur> getUtilisateursWithEmprunts() {
        // Charge tous les utilisateurs avec leurs emprunts et le matériel associé
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        // Hibernate chargera automatiquement les emprunts et les matériels si FetchType.EAGER ou via JOIN FETCH
        return utilisateurs;
    }
}
