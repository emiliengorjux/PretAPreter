package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.presentation.dto.EmpruntDto;
import fr.hiit.pretapreter.service.repository.entity.Emprunt;

import java.time.LocalDate;
import java.util.List;

public interface EmpruntService {

    EmpruntDto createEmprunt (String utilisateur, Long materielId, LocalDate dateEmprunt, LocalDate dateRetourPrevu);
    Emprunt updateEmprunt (Emprunt emprunt);


    List <Emprunt> findAllEmprunts(Long id);
    Emprunt findEmpruntById(Long id);



    Emprunt deleteEmprunt (Long id);





}
