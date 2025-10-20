package fr.hiit.pretapreter.service.repository.model;


import fr.hiit.pretapreter.service.MaterielService;
import fr.hiit.pretapreter.service.presentation.dto.MaterielDto;
import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import fr.hiit.pretapreter.service.repository.entity.Materiel;
import org.springframework.stereotype.Service;

import fr.hiit.pretapreter.service.repository.EmpruntRepository;
import fr.hiit.pretapreter.service.repository.MaterielRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaterielServiceImpl implements MaterielService {
    private final Map<Long, Materiel> materiels = new HashMap<>();

    private final EmpruntRepository empruntRepository;
    private final MaterielRepository materielRepository;

    public MaterielServiceImpl(EmpruntRepository empruntRepository, MaterielRepository materielRepository) {
        this.empruntRepository = empruntRepository;
        this.materielRepository = materielRepository;
    }

    public MaterielRepository getMaterielRepository() {

        return materielRepository;
    }

    public EmpruntRepository getEmpruntRepository() {

        return empruntRepository;
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
    public Materiel updateMateriel(Materiel materiels) {
        if(materiels.getId() == null || materiel.getNom() == null || materiel.getReference() == null || materiel.getCategorie() == null){
            throw new IllegalArgumentException("Nom, Catégorie et Reference du matériel obligatoires ! ");
        }
        if(materiels.get(materiels.getId()) == null ){
            throw new RuntimeException("Matériel à modifier inexistant");
        }
        materiels.put(materiels.getId(), materiel);

        return materiels.save(materiel);
    }


    @Override
    public List<Materiel> findMaterielById(Long id) {
        List<Materiel> MaterielById = new ArrayList<>();
        if(id == null){
            MaterielById.addAll(Materiel.values());
        } else {
            for(Materiel materiel : Materiel.values()) {
                if (student.getNom().contains(nom)) {
                    MaterielById.add(student);
                }
            }
        }
        return List.of(MaterielById);
    }

    @Override
    public List<Materiel> findAllMateriel() {
        List<Materiel> allMaterielById = new ArrayList<>();
        if(id == null){
            allMaterielById.addAll(students.values());
        } else {
            for(Materiel student : students.values()) {
                if (student.getNom().contains(nom)) {
                    allMaterielById.add(student);
                }
            }
        }
        return allMaterielById;
    }

    @Override
    public Materiel deleteMateriel(Long id) {
        if(Materiel.get(id) == null){
            throw new IllegalArgumentException("Matériel inéxistant, suppression impossible");
        }
        return Materiel.remove(id);
    }
}

