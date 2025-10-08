package fr.hiit.pretapreter.service.presentation.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class EmpruntDto {

    private String materiel;
    private String emprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private LocalDate dateRetourEffectif;
    private String suiviEtatMateriel;
    private String commentaire;

}
