package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.dto.UtilisateurDto;
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

    @Override
    public EmpruntDto createEmprunt(Long utilisateurId, Long materielId, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {

        UtilisateurDto utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Materiel materiel = materielRepository.findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Matériel non trouvé"));

        if (dateRetourPrevu.isBefore(dateEmprunt)) {
            throw new IllegalArgumentException("La date de retour prévue doit être après la date d'emprunt.");
        }

        // Vérifier disponibilité
        List<Emprunt> empruntsExistants = empruntRepository.findByMaterielId(materielId);
        for (Emprunt e : empruntsExistants) {
            LocalDate debut = e.getDateEmprunt();
            LocalDate fin = e.getRetourEffectif() != null ? e.getRetourEffectif() : e.getRetourPrevu();
            if (!(dateRetourPrevu.isBefore(debut) || dateEmprunt.isAfter(fin))) {
                throw new IllegalStateException("Le matériel est déjà emprunté pendant cette période.");
            }
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setUtilisateur(utilisateur);
        emprunt.setMateriel(materiel);
        emprunt.setDateEmprunt(dateEmprunt);
        emprunt.setRetourPrevu(dateRetourPrevu);
        emprunt.setSuiviEtatMateriel("Bon état");

        return EmpruntDto.toDto(empruntRepository.save(emprunt));
    }

    @Override
    public EmpruntDto updateEmprunt(Emprunt emprunt) {
        if (emprunt.getId() == null) throw new IllegalArgumentException("ID obligatoire");
        Emprunt existing = empruntRepository.findById(emprunt.getId())
                .orElseThrow(() -> new IllegalArgumentException("Emprunt non trouvé"));
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
        return empruntRepository.findById(utilisateurId)
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
