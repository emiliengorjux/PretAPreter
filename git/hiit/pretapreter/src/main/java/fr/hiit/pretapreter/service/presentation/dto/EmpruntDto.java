package fr.hiit.pretapreter.service.presentation.dto;

import java.time.LocalDate;

import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class EmpruntDto {

    private String emprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private LocalDate dateRetourEffectif;
    private String suiviEtatMateriel;
    private String commentaire;

    public static Emprunt toEntityEmprunt(EmpruntDto empruntDto) {
        Emprunt emprunt = new Emprunt();
        emprunt.setEmprunteur(empruntDto.getEmprunteur());
        emprunt.setDateEmprunt(empruntDto.getDateEmprunt());
        emprunt.setDateEmprunt(empruntDto.getDateRetourPrevu());
        emprunt.setDateEmprunt(empruntDto.getDateRetourEffectif());
        emprunt.setSuiviEtatMateriel(empruntDto.getSuiviEtatMateriel());
        emprunt.setCommentaire(empruntDto.getCommentaire());
        return emprunt;
    }

}
