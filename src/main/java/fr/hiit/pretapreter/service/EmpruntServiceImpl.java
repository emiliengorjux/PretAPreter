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

    public EmpruntDto createEmprunt(EmpruntDto empruntDto) {

        // On récupère les IDs directement depuis le DTO
        Long utilisateurId = empruntDto.getUtilisateurId();
        Long materielId = empruntDto.getMaterielId();

        // On vérifie que l'utilisateur et le matériel existent
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Materiel materiel = materielRepository.findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Matériel non trouvé"));

        // On convertit le DTO en entité
        Emprunt emprunt = EmpruntDto.toEntity(empruntDto);

        // On lie les entités
        emprunt.setUtilisateur(utilisateur);
        emprunt.setMateriel(materiel);

        //Ajoute la date automatiquement a la creation
        if (emprunt.getDateEmprunt() == null) {
            emprunt.setDateEmprunt(LocalDate.now());
        }

        // validations métier
        LocalDate dateEmprunt = emprunt.getDateEmprunt();
        LocalDate dateRetourPrevu = emprunt.getRetourPrevu();

        intervalleEmprunt(empruntDto, dateEmprunt, dateRetourPrevu);

        if (empruntDto.getRetourPrevu() == null) {
            throw new IllegalArgumentException("Les dates d'emprunt et de retour sont obligatoires.");
        }

        if (dateRetourPrevu != null && dateRetourPrevu.isBefore(dateEmprunt)) {
            throw new IllegalArgumentException("La date de retour prévue doit être après la date d'emprunt.");
        }

        // Vérifier les autres emprunts existants du même matériel
        List<Emprunt> empruntsExistants = empruntRepository.findByMaterielId(materielId);

        for (Emprunt e : empruntsExistants) {
            LocalDate debut = e.getDateEmprunt();
            LocalDate fin = e.getRetourEffectif() != null ? e.getRetourEffectif() : e.getRetourPrevu();

            // Si la nouvelle date d'emprunt est comprise entre le début et la fin d’un emprunt existant
            if (!dateEmprunt.isAfter(fin) && !dateRetourPrevu.isBefore(debut)) {
                throw new IllegalArgumentException(
                        "Le matériel est déjà emprunté pendant cette période (" + debut + " → " + fin + ")"
                );
            }

            // Vérifie spécifiquement si la date d'emprunt est exactement la même
            if (dateEmprunt.isEqual(debut)) {
                throw new IllegalArgumentException("La date d'emprunt ne doit pas être la même qu'un autre emprunt existant");
            }
        }

        // Sauvegarde
        Emprunt saved = empruntRepository.save(emprunt);

        return EmpruntDto.toDto(saved);
    }

    public EmpruntDto intervalleEmprunt(EmpruntDto empruntDto, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {

        long dureeEnJours = ChronoUnit.DAYS.between(dateEmprunt, dateRetourPrevu);

        Emprunt emprunt = EmpruntDto.toEntity(empruntDto);

        if (dureeEnJours < 3) {
            throw new IllegalArgumentException("La durée minimale d'emprunt est de 3 jours.");
        }

        if (dureeEnJours > 60) {
            throw new IllegalArgumentException("La durée maximale d'emprunt est de 2 mois (60 jours).");
        }
        Emprunt saved = empruntRepository.save(emprunt);

        return EmpruntDto.toDto(saved);
    }


    public EmpruntDto createRenduEmprunt(EmpruntDto empruntDto, LocalDate retourEffectif, String suiviEtatMateriel, String commentaire) {

        Long utilisateurId = empruntDto.getUtilisateurId();
        Long materielId = empruntDto.getMaterielId();

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Materiel materiel = materielRepository.findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Matériel non trouvé"));

        Emprunt emprunt = EmpruntDto.toEntity(empruntDto);

        emprunt.setUtilisateur(utilisateur);
        emprunt.setMateriel(materiel);

        // on met à jour les infos du rendu
        emprunt.setRetourEffectif(retourEffectif);
        emprunt.setSuiviEtatMateriel(suiviEtatMateriel);
        emprunt.setCommentaire(commentaire);

        //Ajoute la date automatiquement a la creation
        if (emprunt.getRetourEffectif() == null) {
            emprunt.setRetourEffectif(LocalDate.now());
        }

        // validations
        if (retourEffectif == null || suiviEtatMateriel == null) {
            throw new IllegalArgumentException("La date de retour et le suivi d'état du matériel sont obligatoires.");
        }

        if (retourEffectif.isBefore(emprunt.getDateEmprunt())) {
            throw new IllegalArgumentException("La date de retour ne peut pas être avant la date d'emprunt.");
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
