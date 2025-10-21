package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.MaterielDto;
import java.util.List;

public interface MaterielService {
    MaterielDto creeMateriel(String nom, String reference, String etatMateriel, String commentaire, String categorie);
    MaterielDto updateMateriel(MaterielDto materielDto);
    void deleteMateriel(Long id);
    MaterielDto findMaterielById(Long id);
    List<MaterielDto> findAllMateriels();
}
