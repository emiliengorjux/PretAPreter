package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.dto.MaterielDto;
import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.model.entity.Emprunt;
import fr.hiit.pretapreter.model.entity.Materiel;
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
        Utilisateur utilisateur = UtilisateurDto.toEntity(utilisateurDto);
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return UtilisateurDto.toDto(saved);
    }

    @Override
    public Optional<UtilisateurDto> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::toDto);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs(String prenom) {
        List<Utilisateur> utilisateurs;

        if (prenom != null && !prenom.isBlank()) {
            utilisateurs = utilisateurRepository
                    .findByPrenom(prenom);
        } else {
            utilisateurs = utilisateurRepository.findAll();
        }

        return utilisateurs.stream()
                .map(UtilisateurDto::toDto)
                .toList();
    }

    @Override
    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur existing = utilisateurRepository.findById(utilisateurDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur inexistant"));
                existing.setNom(utilisateurDto.getNom());
                existing.setPrenom(utilisateurDto.getPrenom());
                existing.setEmail(utilisateurDto.getEmail());

        Utilisateur updatedUtilisateur = utilisateurRepository.save(existing);
        return UtilisateurDto.toDto(updatedUtilisateur);

    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public List<UtilisateurDto> getUtilisateursWithEmprunts() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurDto::toDto)
                .toList();
    }
}
