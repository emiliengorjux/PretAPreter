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
    private LocalDate RetourPrevu;
    private LocalDate RetourEffectif;
    private String suiviEtatMateriel;
    private String commentaire;

    public static Emprunt toEntityEmprunt(EmpruntDto empruntDto) {
        Emprunt emprunt = new Emprunt();
        emprunt.setEmprunteur(empruntDto.getEmprunteur());
        emprunt.setDateEmprunt(empruntDto.getDateEmprunt());
        emprunt.setRetourPrevu(empruntDto.getRetourPrevu());
        emprunt.setRetourEffectif(empruntDto.getRetourEffectif());
        emprunt.setSuiviEtatMateriel(empruntDto.getSuiviEtatMateriel());
        emprunt.setCommentaire(empruntDto.getCommentaire());
        return emprunt;
    }

}
