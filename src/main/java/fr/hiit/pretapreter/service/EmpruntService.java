package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.presentation.dto.EmpruntDto;
import fr.hiit.pretapreter.service.repository.entity.Emprunt;

import java.time.LocalDate;
import java.util.List;

public interface EmpruntService {

    EmpruntDto createEmprunt (String emprunteur, Long materielId, LocalDate dateEmprunt, LocalDate dateRetourPrevu);
    Emprunt updateEmprunt (Emprunt emprunt);
    Emprunt findEmprunt (Long id);
    List<Emprunt> findAllEmprunt (Long id);
    Emprunt deleteEmprunt (Long id);


}
