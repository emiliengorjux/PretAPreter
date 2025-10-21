package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.MaterielDto;
import fr.hiit.pretapreter.model.entity.Materiel;
import fr.hiit.pretapreter.repository.MaterielRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterielServiceImpl implements MaterielService {

    private final MaterielRepository materielRepository;

    public MaterielServiceImpl(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    @Override
    public MaterielDto creeMateriel(MaterielDto materielDto) {
        Materiel materiel = MaterielDto.toEntity(materielDto);
        if ("Mauvais".equals(materiel.getEtatMateriel())) {
            throw new IllegalStateException("Le matériel est défectueux, il ne peut pas être ajouté");
        }

        Materiel savedMateriel = materielRepository.save(materiel);
        return MaterielDto.toDto(savedMateriel);
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

        Materiel updatedMateriel = materielRepository.save(existing);
        return MaterielDto.toDto(updatedMateriel);
    }

    @Override
    public void deleteMateriel(Long id) {
        Materiel existing = materielRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matériel inexistant, suppression impossible"));
        materielRepository.delete(existing);
    }

    @Override
    public MaterielDto findMaterielById(Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matériel inexistant"));
        return MaterielDto.toDto(materiel);
    }

    @Override
    public List<MaterielDto> findAllMateriels() {
        return materielRepository.findAll()
                .stream()
                .map(MaterielDto::toDto)
                .collect(Collectors.toList());
    }
}
