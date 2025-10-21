package fr.hiit.pretapreter.dto;

import fr.hiit.pretapreter.model.entity.Materiel;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

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

    // Conversion DTO → Entité
    public static Materiel toEntity(MaterielDto materielDto) {
        Materiel materiel = new Materiel();
        materiel.setId(materielDto.getId());
        materiel.setNom(materielDto.getNom());
        materiel.setReference(materielDto.getReference());
        materiel.setEtatMateriel(materielDto.getEtatMateriel());
        materiel.setCommentaire(materielDto.getCommentaire());
        materiel.setCategorie(materielDto.getCategorie());
        materiel.setDateAjout(LocalDateTime.parse(materielDto.getDateAjout()));
        return materiel;
    }

    // Conversion Entité → DTO
    public static MaterielDto toDto(Materiel materiel) {
        MaterielDto materielDto = new MaterielDto();
        materielDto.setId(materiel.getId());
        materielDto.setNom(materiel.getNom());
        materielDto.setReference(materiel.getReference());
        materielDto.setEtatMateriel(materiel.getEtatMateriel());
        materielDto.setCommentaire(materiel.getCommentaire());
        materielDto.setCategorie(materiel.getCategorie());
        materielDto.setDateAjout(materiel.getDateAjout().toString());
        return materielDto;
    }
}
