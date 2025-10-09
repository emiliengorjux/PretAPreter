package fr.hiit.pretapreter.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.hiit.pretapreter.service.repository.entity.Materiel;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {

}
