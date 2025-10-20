package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.presentation.dto.MaterielDto;
import fr.hiit.pretapreter.service.repository.entity.Materiel;

import java.time.LocalDateTime;
import java.util.List;

public interface MaterielService {

    MaterielDto creeMateriel(String nom, String reference, String etatMateriel,
                             String commentaire, String categorie, LocalDateTime dateAjout);

    Materiel updateMateriel(Materiel materiel);
    Materiel deleteMateriel (Long id);
    List<Materiel> findMaterielById(Long id);
    List<Materiel> findAllMateriel();

}
