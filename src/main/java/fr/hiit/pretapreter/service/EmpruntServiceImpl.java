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
import java.time.temporal.ChronoUnit;
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
    public EmpruntDto createEmprunt(EmpruntDto empruntDto) {
        Long utilisateurId = empruntDto.getUtilisateurId();
        Long materielId = empruntDto.getMaterielId();

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Materiel materiel = materielRepository.findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Matériel non trouvé"));

        Emprunt emprunt = EmpruntDto.toEntity(empruntDto);
        emprunt.setUtilisateur(utilisateur);
        emprunt.setMateriel(materiel);

        // Si la date d’emprunt n’est pas précisée, on met la date du jour
        if (emprunt.getDateEmprunt() == null) {
            emprunt.setDateEmprunt(LocalDate.now());
        }

        // Vérifie la validité de l’intervalle AVANT sauvegarde
        boolean intervalleValide = intervalleEmprunt(
                emprunt.getDateEmprunt(),
                emprunt.getRetourPrevu(),
                materielId
        );

        if (!intervalleValide) {
            throw new IllegalArgumentException("L’intervalle d’emprunt est invalide.");
        }

        // Sauvegarde de l’emprunt
        Emprunt saved = empruntRepository.save(emprunt);
        return EmpruntDto.toDto(saved);
    }

    public EmpruntDto createRenduEmprunt(EmpruntDto empruntDto) {

        // Récupère l’emprunt existant
        Emprunt emprunt = empruntRepository.findById(empruntDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Emprunt non trouvé"));

        // Récupère les champs depuis le DTO
        LocalDate retourEffectif = empruntDto.getRetourEffectif() != null ? empruntDto.getRetourEffectif() : LocalDate.now();
        String suiviEtatMateriel = empruntDto.getSuiviEtatMateriel();
        String commentaire = empruntDto.getCommentaire();

        // Validation obligatoire
        if (suiviEtatMateriel == null) {
            throw new IllegalArgumentException("Le suivi d'état du matériel est obligatoire.");
        }

        if (retourEffectif.isBefore(emprunt.getDateEmprunt())) {
            throw new IllegalArgumentException("La date de retour ne peut pas être avant la date d'emprunt.");
        }

        // Calcul du retard
        long joursDeRetard = 0;

        if (emprunt.getRetourPrevu() != null) {
            if (retourEffectif.isAfter(emprunt.getRetourPrevu())) {
                joursDeRetard = ChronoUnit.DAYS.between(emprunt.getRetourPrevu(), retourEffectif);
            }
        }

        // Mise à jour de l’emprunt
        emprunt.setRetourEffectif(retourEffectif);
        emprunt.setSuiviEtatMateriel(suiviEtatMateriel);
        emprunt.setCommentaire((commentaire != null ? commentaire +"\n " : "") +
                (joursDeRetard > 0 ? "Retour en retard de " + joursDeRetard + " jours." : ""));

        // Sauvegarde
        Emprunt saved = empruntRepository.save(emprunt);
        return EmpruntDto.toDto(saved);
    }


    public boolean intervalleEmprunt(LocalDate dateEmprunt, LocalDate dateRetourPrevu, Long materielId) {
        if (dateEmprunt == null || dateRetourPrevu == null) {
            throw new IllegalArgumentException("Les dates d'emprunt et de retour sont obligatoires.");
        }

        // Empêche une date d'emprunt dans le passé
        if (dateEmprunt.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date d'emprunt ne peut pas être antérieure à aujourd'hui.");
        }

        if (dateRetourPrevu.isBefore(dateEmprunt)) {
            throw new IllegalArgumentException("La date de retour prévue doit être après la date d'emprunt.");
        }

        long duree = ChronoUnit.DAYS.between(dateEmprunt, dateRetourPrevu);

        if (duree <= 3) {
            throw new IllegalArgumentException("La durée minimale d'un emprunt est de 3 jours.");
        }

        if (duree >= 60) {
            throw new IllegalArgumentException("La durée maximale d'un emprunt est de 2 mois (60 jours).");
        }

        // Vérifie les chevauchements avec d'autres emprunts du même matériel
        List<Emprunt> empruntsExistants = empruntRepository.findByMaterielId(materielId);
        for (Emprunt e : empruntsExistants) {
            LocalDate debut = e.getDateEmprunt();
            LocalDate fin = e.getRetourEffectif() != null ? e.getRetourEffectif() : e.getRetourPrevu();

            boolean chevauchement = !dateEmprunt.isAfter(fin) && !dateRetourPrevu.isBefore(debut);
            if (chevauchement) {
                throw new IllegalArgumentException(
                        "Le matériel est déjà emprunté ou réservé pendant cette période (" + debut + " → " + fin + ")."
                );
            }
        }

        // Si tout est OK
        return true;
    }

    public long retardRetourEffectif(LocalDate dateRetourPrevu, LocalDate retourEffectif) {
        if (retourEffectif == null) {
            throw new IllegalArgumentException("La date de retour effective est obligatoire.");
        }

        if (dateRetourPrevu == null) {
            throw new IllegalArgumentException("La date prévue de retour est manquante.");
        }

        // Si rendu avant ou le jour même → pas de retard
        if (!retourEffectif.isAfter(dateRetourPrevu)) {
            return 0;
        }

        // Sinon on calcule le nombre de jours de retard
        return ChronoUnit.DAYS.between(dateRetourPrevu, retourEffectif);
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
