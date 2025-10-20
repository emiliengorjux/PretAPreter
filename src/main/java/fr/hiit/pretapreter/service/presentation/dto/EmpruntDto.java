package fr.hiit.pretapreter.service.presentation.dto;

import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import java.time.LocalDate;

public class EmpruntDto {

    private Long id;
    private Long utilisateurId;
    private Long materielId;
    private String emprunteurNom; // optionnel pour affichage
    private String materielNom;   // optionnel pour affichage
    private LocalDate dateEmprunt;
    private LocalDate retourPrevu;
    private LocalDate retourEffectif;
    private String suiviEtatMateriel;
    private String commentaire;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }

    public Long getMaterielId() { return materielId; }
    public void setMaterielId(Long materielId) { this.materielId = materielId; }

    public String getEmprunteurNom() { return emprunteurNom; }
    public void setEmprunteurNom(String emprunteurNom) { this.emprunteurNom = emprunteurNom; }

    public String getMaterielNom() { return materielNom; }
    public void setMaterielNom(String materielNom) { this.materielNom = materielNom; }

    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public LocalDate getRetourPrevu() { return retourPrevu; }
    public void setRetourPrevu(LocalDate retourPrevu) { this.retourPrevu = retourPrevu; }

    public LocalDate getRetourEffectif() { return retourEffectif; }
    public void setRetourEffectif(LocalDate retourEffectif) { this.retourEffectif = retourEffectif; }

    public String getSuiviEtatMateriel() { return suiviEtatMateriel; }
    public void setSuiviEtatMateriel(String suiviEtatMateriel) { this.suiviEtatMateriel = suiviEtatMateriel; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    // --- Conversion Entity -> DTO ---
    public static EmpruntDto toDto(Emprunt emprunt) {
        EmpruntDto dto = new EmpruntDto();
        dto.setId(emprunt.getId());
        dto.setUtilisateurId(emprunt.getUtilisateur() != null ? emprunt.getUtilisateur().getId() : null);
        dto.setMaterielId(emprunt.getMateriel() != null ? emprunt.getMateriel().getId() : null);
        dto.setEmprunteurNom(emprunt.getUtilisateur() != null ? emprunt.getUtilisateur().getNom() : null);
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
