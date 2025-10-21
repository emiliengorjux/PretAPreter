package fr.hiit.pretapreter.repository;

import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurDto, Long> {

}
