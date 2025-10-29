package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.repository.EmpruntRepository;
import fr.hiit.pretapreter.repository.MaterielRepository;
import fr.hiit.pretapreter.repository.UtilisateurRepository;
import fr.hiit.pretapreter.model.entity.Emprunt;
import fr.hiit.pretapreter.model.entity.Materiel;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntServiceImpl implements EmpruntService {

    private final MaterielRepository materielRepository;
    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository utilisateurRepository;

    public EmpruntServiceImpl(MaterielRepository materielRepository,
                              EmpruntRepository empruntRepository,
                              UtilisateurRepository utilisateurRepository) {
        this.materielRepository = materielRepository;
        this.empruntRepository = empruntRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public EmpruntDto createEmprunt(EmpruntDto empruntDto) {
        // On convertit le DTO en entité
        Emprunt emprunt = EmpruntDto.toEntity(empruntDto);

        // On récupère les IDs directement depuis le DTO
        Long utilisateurId = empruntDto.getUtilisateurId();
        Long materielId = empruntDto.getMaterielId();

        // On vérifie que l'utilisateur et le matériel existent
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Materiel materiel = materielRepository.findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Matériel non trouvé"));

        emprunt.setUtilisateur(utilisateur);
        emprunt.setMateriel(materiel);

        //(optionnel) validations métier
        LocalDate dateEmprunt = emprunt.getDateEmprunt();
        LocalDate dateRetourPrevu = emprunt.getRetourPrevu();

        if (dateRetourPrevu != null && dateRetourPrevu.isBefore(dateEmprunt)) {
            throw new IllegalArgumentException("La date de retour prévue doit être après la date d'emprunt.");
        }

        // Sauvegarde
        Emprunt saved = empruntRepository.save(emprunt);

        return EmpruntDto.toDto(saved);
    }


    @Override
    public EmpruntDto updateEmprunt(EmpruntDto emprunt) {

        Emprunt existing = empruntRepository.findById(emprunt.getId())
                .orElseThrow(() -> new IllegalArgumentException("Emprunt inexistant"));
        existing.setDateEmprunt(emprunt.getDateEmprunt());
        existing.setRetourPrevu(emprunt.getRetourPrevu());
        existing.setRetourEffectif(emprunt.getRetourEffectif());
        existing.setSuiviEtatMateriel(emprunt.getSuiviEtatMateriel());
        existing.setCommentaire(emprunt.getCommentaire());
        return EmpruntDto.toDto(empruntRepository.save(existing));
    }
    @Override
    public EmpruntDto findEmpruntById(Long id) {
        return EmpruntDto.toDto(empruntRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emprunt non trouvé")));
    }

    @Override
    public List<EmpruntDto> findAllEmprunts() {
        return empruntRepository.findAll()
                .stream()
                .map(EmpruntDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpruntDto> findAllByUtilisateurId(Long utilisateurId) {
        return empruntRepository.findByUtilisateurId(utilisateurId)
                .stream()
                .map(EmpruntDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpruntDto> findAllByMaterielId(Long materielId) {
        return empruntRepository.findByMaterielId(materielId)
                .stream()
                .map(EmpruntDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmprunt(Long id) {
        Emprunt existing = empruntRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emprunt non trouvé"));
        empruntRepository.delete(existing);
    }
}
