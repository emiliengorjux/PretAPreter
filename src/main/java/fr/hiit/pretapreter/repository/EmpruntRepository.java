package fr.hiit.pretapreter.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.hiit.pretapreter.model.entity.Emprunt;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long > {

    List<Emprunt> findByUtilisateurId(Long utilisateurId);
    List<Emprunt> findByMaterielId(Long materielId);
}

