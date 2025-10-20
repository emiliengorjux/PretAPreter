package fr.hiit.pretapreter.service.repository.model;


import fr.hiit.pretapreter.service.MaterielService;
import fr.hiit.pretapreter.service.presentation.dto.MaterielDto;
import fr.hiit.pretapreter.service.repository.entity.Materiel;
import org.springframework.stereotype.Service;

import fr.hiit.pretapreter.service.repository.EmpruntRepository;
import fr.hiit.pretapreter.service.repository.MaterielRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MaterielServiceImpl implements MaterielService {
    private final Map<Long, Materiel> materiels = new HashMap<>();

    private final EmpruntRepository empruntRepository;
    private final MaterielRepository materielRepository;

    public MaterielServiceImpl(EmpruntRepository empruntRepository, MaterielRepository materielRepository) {
        this.empruntRepository = empruntRepository;
        this.materielRepository = materielRepository;
    }

    @Override
    public MaterielDto creeMateriel(String nom, String reference, String etatMateriel,
                                    String commentaire, String categorie, LocalDateTime dateAjout) {

        Materiel materielR = (Materiel) materielRepository;


        String defectueux = materielR.getEtatMateriel();
        if (defectueux.equals("Mauvais")) {
            throw new IllegalStateException("Le matériel est défectueux, il ne peut pas être emprunté");
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

    @Override
    public Materiel updateMateriel(Materiel materiel) {
        if(materiel.getId() == null || materiel.getNom() == null || materiel.getReference() == null || materiel.getCategorie() == null){
            throw new IllegalArgumentException("Nom, Catégorie et Reference du matériel obligatoires ! ");
        }
        if(materiels.get(materiel.getId()) == null ){
            throw new RuntimeException("Matériel à modifier inexistant");
        }
        materiels.put(materiel.getId(), materiel);

        return materiel;
    }

    @Override
    public Materiel deleteMateriel(Long id) {
        if(materiels.get(id) == null){
            throw new IllegalArgumentException("Matériel inéxistant, suppression impossible");
        }
        return materiels.remove(id);
    }

    @Override
    public List<Materiel> findMaterielById(Long id) {
        return Collections.singletonList(materielRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Materiel inexistant")));
    }

    @Override
    public List<Materiel> findAllMateriels() {
        return materielRepository.findAll();
    }


}

