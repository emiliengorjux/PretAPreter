package fr.hiit.pretapreter.service.repository;


import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long > {
}
