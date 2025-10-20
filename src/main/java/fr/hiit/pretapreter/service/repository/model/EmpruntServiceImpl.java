package fr.hiit.pretapreter.service.repository.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.hiit.pretapreter.service.EmpruntService;
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

    public EmpruntServiceImpl(MaterielRepository materielRepository, EmpruntRepository empruntRepository) {
        this.materielRepository = materielRepository;
        this.empruntRepository = empruntRepository;
    }

    public EmpruntRepository getEmpruntRepository() {

        return empruntRepository;
    }

    public MaterielRepository getMaterielRepository() {

        return materielRepository;
    }

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
        emprunt.setEmprunteur(emprunteur);
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
    public Emprunt updateEmprunt(Emprunt emprunt) {
        if(student.getId() == null || student.getPrenom() == null || student.getNom() == null || student.getEmail() == null){
            throw new IllegalArgumentException("Id, Nom, Prenom et Email obligatoires ! ");
        }
        if(students.get(student.getId()) == null ){
            throw new RuntimeException("Emprunt à modifier inexistant");
        }
        students.put(student.getId(), student);

        return student;

    }

    @Override
    public Emprunt findEmprunt(Long id) {
        List<Emprunt> empruntById = new ArrayList<>();
        if(id == null){
            empruntById.addAll(students.values());
        } else {
            for(Student student : students.values()) {
                if (student.getNom().contains(nom)) {
                    empruntById.add(student);
                }
            }
        }
        return empruntById;
    }

    @Override
    public List<Emprunt> findAllEmprunt(Long id) {
        List<Emprunt> allEmpruntById = new ArrayList<>();
        if(id == null){
            allEmpruntById.addAll(students.values());
        } else {
            for(Student student : students.values()) {
                if (student.getNom().contains(nom)) {
                    allEmpruntById.add(student);
                }
            }
        }
        return allEmpruntById;

        return List.of();
    }

    @Override
    public Emprunt deleteEmprunt(Long id) {
        if(Emprunt.get(id) == null){
            throw new IllegalArgumentException("Emprunt inexistant, suppression impossible");
        }
        return Emprunt.remove(id);

    }

}
