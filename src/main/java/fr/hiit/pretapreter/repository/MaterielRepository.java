package fr.hiit.pretapreter.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.hiit.pretapreter.model.entity.Materiel;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {

}
