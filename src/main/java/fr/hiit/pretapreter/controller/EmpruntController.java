package fr.hiit.pretapreter.controller;

import java.time.LocalDate;

import fr.hiit.pretapreter.dto.EmpruntDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hiit.pretapreter.service.EmpruntService;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {


    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @PostMapping
    public EmpruntDto createEmprunt(
            @RequestParam String emprunteur,
            @RequestParam Long materielId,
            @RequestParam String dateEmprunt,
            @RequestParam String retourPrevu
    ) {
        return empruntService.createEmprunt(
                emprunteurNom,
                materielId,
                LocalDate.parse(dateEmprunt),
                LocalDate.parse(retourPrevu)
        );
    }
}



