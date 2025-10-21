package fr.hiit.pretapreter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.hiit.pretapreter.model.entity.Emprunt;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpruntDto {

    private Long id;
    private Long utilisateurId;
    private Long materielId;
    private String emprunteurPrenom;
    private String materielNom;
    private LocalDate dateEmprunt;
    private LocalDate retourPrevu;
    private LocalDate retourEffectif;
    private String suiviEtatMateriel;
    private String commentaire;

    // --- Conversion Entity -> DTO ---
    public static EmpruntDto toDto(Emprunt emprunt) {
        EmpruntDto dto = new EmpruntDto();
        dto.setId(emprunt.getId());
        dto.setUtilisateurId(emprunt.getUtilisateur() != null ? emprunt.getUtilisateur().getId() : null);
        dto.setMaterielId(emprunt.getMateriel() != null ? emprunt.getMateriel().getId() : null);
        dto.setEmprunteurPrenom(emprunt.getUtilisateur() != null ? emprunt.getUtilisateur().getPrenom(): null);
        dto.setMaterielNom(emprunt.getMateriel() != null ? emprunt.getMateriel().getNom() : null);
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setRetourPrevu(emprunt.getRetourPrevu());
        dto.setRetourEffectif(emprunt.getRetourEffectif());
        dto.setSuiviEtatMateriel(emprunt.getSuiviEtatMateriel());
        dto.setCommentaire(emprunt.getCommentaire());
        return dto;
    }

    // --- Conversion DTO -> Entity ---
    public static Emprunt toEntity(EmpruntDto dto) {
        Emprunt e = new Emprunt();
        e.setDateEmprunt(dto.getDateEmprunt());
        e.setRetourPrevu(dto.getRetourPrevu());
        e.setRetourEffectif(dto.getRetourEffectif());
        e.setSuiviEtatMateriel(dto.getSuiviEtatMateriel());
        e.setCommentaire(dto.getCommentaire());
        return e;
    }
}
