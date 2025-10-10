package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.repository.EmpruntRepository;
import fr.hiit.pretapreter.service.repository.MaterielRepository;
import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import fr.hiit.pretapreter.service.repository.entity.Materiel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmpruntService {
    private final MaterielService materielService;
    private final EmpruntRepository empruntRepository;
    private final MaterielRepository materielRepository;

    public EmpruntService(MaterielService materielService, EmpruntRepository empruntRepository, MaterielRepository materielRepository) {
        this.materielService = materielService;
        this.empruntRepository = empruntRepository;
        this.materielRepository = materielRepository;
    }

    public EmpruntRepository getEmpruntRepository() {
        return empruntRepository;
    }

    public MaterielService getMaterielService() {
        return materielService;
    }

    public Emprunt emprunterMateriel(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("L'emprunt non disponible"));
        if (new LocalDate(emprunt.getDateEmprunt()).isAfter(emprunt.getRetourPrevu())) {
            throw new RuntimeException("L'emprunt n'est pas possible, matériel déjà emprunté");
        }

    }
}




/*Vérifier si le retour est en retard
if (LocalDateTime.now().isAfter(emprunt.getDateRetourPrevue())) {
        long joursRetard = ChronoUnit.DAYS.between(emprunt.getDateRetourPrevue(), LocalDateTime.now());
        // Envoyer un avertissement (ex : email, notification)
        notificationService.envoyerAvertissementRetard(emprunt.getNomEmprunteur(), joursRetard);
    }

