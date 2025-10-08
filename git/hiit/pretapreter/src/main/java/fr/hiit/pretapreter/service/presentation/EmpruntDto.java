package fr.hiit.pretapreter.service.presentation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


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
