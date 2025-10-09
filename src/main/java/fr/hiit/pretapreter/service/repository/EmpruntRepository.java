package fr.hiit.pretapreter.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.hiit.pretapreter.service.repository.entity.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long > {
    
}

