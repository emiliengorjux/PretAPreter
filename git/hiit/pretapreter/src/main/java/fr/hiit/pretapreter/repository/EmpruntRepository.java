package fr.hiit.pretapreter.repository;


import fr.hiit.pretapreter.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long > {
}
