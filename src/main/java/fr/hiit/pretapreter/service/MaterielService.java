package fr.hiit.pretapreter.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import fr.hiit.pretapreter.service.presentation.dto.MaterielDto;
import fr.hiit.pretapreter.service.repository.EmpruntRepository;
import fr.hiit.pretapreter.service.repository.MaterielRepository;
import fr.hiit.pretapreter.service.repository.entity.Materiel;

@Service
public class MaterielService {

    private final EmpruntRepository empruntRepository;
    private final MaterielRepository materielRepository;

    public MaterielService(EmpruntRepository empruntRepository, MaterielRepository materielRepository) {
        this.empruntRepository = empruntRepository;
        this.materielRepository = materielRepository;
    }

    public MaterielRepository getMaterielRepository() {
        return materielRepository;
    }

    public EmpruntRepository getEmpruntRepository() {
        return empruntRepository;
    }


    public MaterielDto creeMateriel(String nom, String reference, String etatMateriel,
                                    String commentaire, String categorie, LocalDateTime dateAjout) {
        Materiel materielR = (Materiel) materielRepository;

        String defectueux = materielR.getEtatMateriel();
        if (defectueux.equals("Mauvais")) {
            throw new IllegalStateException("Le matériel est défectueux il ne peut pas être emprunté");
        }


        Materiel materiel = new Materiel();
        materiel.setNom(nom);
        materiel.setReference(reference);
        materiel.setEtatMateriel(etatMateriel);
        materiel.setCommentaire(commentaire);
        materiel.setCategorie(categorie);
        materiel.setDateAjout(dateAjout);

        Materiel savedMateriel = materielRepository.save(materiel);


        return MaterielDto.toDtoMateriel(savedMateriel);


    }


}

