package fr.hiit.pretapreter.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.hiit.pretapreter.service.repository.entity.Emprunt;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long > {

    List<Emprunt> findByMaterielId(Long materielId);
}

