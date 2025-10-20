 package fr.hiit.pretapreter.service.repository;


import fr.hiit.pretapreter.service.repository.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

 public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
