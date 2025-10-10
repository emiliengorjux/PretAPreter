package fr.hiit.pretapreter.service.repository;


import fr.hiit.pretapreter.service.repository.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {

    List<Materiel> findByDisponibleTrue();




}
