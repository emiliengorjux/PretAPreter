package fr.hiit.pretapreter.repository;

import fr.hiit.pretapreter.model.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    Optional<Materiel> findByReference(String reference);
}
