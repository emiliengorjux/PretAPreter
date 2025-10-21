package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.MaterielDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MaterielService {

    MaterielDto creeMateriel(String nom, String reference, String etatMateriel,
                             String commentaire, String categorie, LocalDateTime dateAjout);

    MaterielDto updateMateriel(MaterielDto materielDto);

    MaterielDto deleteMateriel (Long id);
    List<MaterielDto> findAllMateriels();
    List<MaterielDto> findMaterielById(Long id);

}
