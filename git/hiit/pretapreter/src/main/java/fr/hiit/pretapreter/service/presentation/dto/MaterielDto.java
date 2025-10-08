package fr.hiit.pretapreter.service.presentation.dto;

import fr.hiit.pretapreter.service.repository.entity.Materiel;
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

    public static Materiel toEntityMateriel(MaterielDto materielDto) {
        Materiel materiel = new Materiel();
        materiel.setNom(materielDto.getNom());
        materiel.setReference(materielDto.getReference());
        materiel.setEtatMateriel(materielDto.getEtatMateriel());
        materiel.setCommentaire(materielDto.getCommentaire());
        materiel.setCategorie(materielDto.getCategorie());
        return materiel;
    }
}
