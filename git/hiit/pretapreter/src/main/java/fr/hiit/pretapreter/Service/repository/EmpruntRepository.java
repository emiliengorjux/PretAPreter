package fr.hiit.pretapreter.Service.repository;


import fr.hiit.pretapreter.Service.repository.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long > {
}
