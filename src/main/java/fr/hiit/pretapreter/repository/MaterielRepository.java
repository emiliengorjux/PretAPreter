package fr.hiit.pretapreter.repository;

import fr.hiit.pretapreter.model.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
}
