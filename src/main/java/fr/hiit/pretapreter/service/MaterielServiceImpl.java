package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.MaterielDto;
import fr.hiit.pretapreter.repository.MaterielRepository;
import fr.hiit.pretapreter.model.entity.Materiel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterielServiceImpl implements MaterielService {

    private final MaterielRepository materielRepository;

    public MaterielServiceImpl(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    @Override
    public MaterielDto creeMateriel(String nom, String reference, String etatMateriel,
                                    String commentaire, String categorie, LocalDateTime dateAjout) {

        if ("Mauvais".equals(etatMateriel)) {
            throw new IllegalStateException("Le matériel est défectueux, il ne peut pas être ajouté");
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
    public MaterielDto updateMateriel(MaterielDto materielDto) {
        Materiel existing = materielRepository.findById(materielDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Matériel à modifier inexistant"));

        existing.setNom(materielDto.getNom());
        existing.setReference(materielDto.getReference());
        existing.setEtatMateriel(materielDto.getEtatMateriel());
        existing.setCommentaire(materielDto.getCommentaire());
        existing.setCategorie(materielDto.getCategorie());
        existing.setDateAjout(LocalDateTime.parse(materielDto.getDateAjout()));

        return MaterielDto.toDtoMateriel(materielRepository.save(existing));
    }

    @Override
    public MaterielDto deleteMateriel(Long id) {
        Materiel existing = materielRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matériel inexistant, suppression impossible"));
        materielRepository.delete(existing);
        return MaterielDto.toDtoMateriel(existing);
    }

    @Override
    public List<MaterielDto> findMaterielById(Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matériel inexistant"));
        return Collections.singletonList(MaterielDto.toDtoMateriel(materiel));
    }

    @Override
    public List<MaterielDto> findAllMateriels() {
        return materielRepository.findAll()
                .stream()
                .map(MaterielDto::toDtoMateriel)
                .collect(Collectors.toList());
    }
}
