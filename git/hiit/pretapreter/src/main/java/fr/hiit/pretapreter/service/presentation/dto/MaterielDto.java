package fr.hiit.pretapreter.service.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterielDto {
    private Long id;

    private String nom;
    private String reference;
    private String etatMateriel;
    private String commentaire;
    private String categorie;
    private String dateAjout;
}
