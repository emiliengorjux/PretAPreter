package fr.hiit.pretapreter.service.repository.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.hiit.pretapreter.service.EmpruntService;
import fr.hiit.pretapreter.service.repository.UtilisateurRepository;
import fr.hiit.pretapreter.service.repository.entity.Utilisateur;
import org.springframework.stereotype.Service;

import fr.hiit.pretapreter.service.presentation.dto.EmpruntDto;
import fr.hiit.pretapreter.service.repository.EmpruntRepository;
import fr.hiit.pretapreter.service.repository.MaterielRepository;
import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import fr.hiit.pretapreter.service.repository.entity.Materiel;

@Service
public class EmpruntServiceImpl implements EmpruntService {
    private final MaterielRepository materielRepository;
    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository utilisateurRepository;


    private final Map<Long, Emprunt> emprunts = new HashMap<>();


    public EmpruntServiceImpl(MaterielRepository materielRepository,
                              EmpruntRepository empruntRepository,
                              UtilisateurRepository utilisateurRepository) {
        this.materielRepository = materielRepository;
        this.empruntRepository = empruntRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
            .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));


    Materiel materiel = materielRepository.findById(materielId)
            .orElseThrow(() -> new IllegalArgumentException("Matériel non trouvé"));



    public EmpruntDto createEmprunt(String emprunteur, Long materielId, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {

        // Vérifier que le matériel existe
        Materiel materiel = materielRepository
                .findById(materielId)
                .orElseThrow(() -> new IllegalArgumentException("Le matériel n'existe pas"));

        // Vérifier que les dates sont valides
        if (dateRetourPrevu.isBefore(dateEmprunt)) {
            throw new IllegalArgumentException("La date de retour prévu doit être anterieur à la date d'emprunt.");
        }

        // Vérifier les chevauchements d'emprunt
        List<Emprunt> empruntsExistants = empruntRepository.findAll().stream()
                .filter(e -> e.getMateriel().getId().equals(materielId))
                .toList();

        for (Emprunt e : empruntsExistants) {
            LocalDate debut = e.getDateEmprunt();
            LocalDate fin = e.getRetourEffectif() != null ? e.getRetourEffectif() : e.getRetourPrevu();

        boolean chevauchement = !(dateRetourPrevu.isBefore(debut) || dateEmprunt.isAfter(fin));
        if (chevauchement) {
            throw new IllegalStateException("Le matériel est déjà emprunté pendant cette période.");
        }
    }

        // Créer l'emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setMateriel(materiel);
        emprunt.setDateEmprunt(dateEmprunt);
        emprunt.setRetourPrevu(dateRetourPrevu);
        emprunt.setRetourEffectif(null); // pas encore retourné
        emprunt.setSuiviEtatMateriel("Bon état"); // ou la valeur par défaut que tu veux

        // Sauvegarder
        Emprunt savedEmprunt = empruntRepository.save(emprunt);

        // Retourner le DTO
        return EmpruntDto.toDto(savedEmprunt);


    }

    @Override
    public Emprunt findEmpruntById(Long id) {
        return empruntRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emprunt non trouvé"));
    }

    @Override
    public List<Emprunt> findAllEmprunts(Long id) {
        return List.of();
    }



    @Override
    public Emprunt updateEmprunt(Emprunt emprunt) {
        if(emprunt.getId() == null || emprunt.getMateriel() == null || emprunt.getDateEmprunt() == null){
            throw new IllegalArgumentException("Id, Nom, Prenom et Email obligatoires ! ");
        }
        if(emprunts.get(emprunt.getId()) == null ){
            throw new RuntimeException("Emprunt à modifier inexistant");
        }
        emprunts.put(emprunt.getId(), emprunt);

        return emprunt;

    }

    @Override
    public Emprunt deleteEmprunt(Long id) {
        if(emprunts.get(id) == null){
            throw new IllegalArgumentException("Emprunt inexistant, suppression impossible");
        }
        return emprunts.remove(id);

    }

}
