package fr.hiit.pretapreter.service.repository.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Le matériel emprunté
    @ManyToOne(optional = false)
    @JoinColumn(name = "materiel_id", nullable = false)
    private Materiel materiel;

    // 🔗 L’utilisateur qui a fait cet emprunt
    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "date_emprunt", nullable = false)
    private LocalDate dateEmprunt;

    @Column(name = "retour_prevu", nullable = false)
    private LocalDate retourPrevu;

    @Column(name = "retour_effectif")
    private LocalDate retourEffectif;

    @Column(name = "suivi_etat_materiel", nullable = false)
    private String suiviEtatMateriel;

    private String commentaire;

    public Emprunt() {}

    public Emprunt(Materiel materiel, Utilisateur utilisateur, LocalDate dateEmprunt,
                   LocalDate retourPrevu, LocalDate retourEffectif, String suiviEtatMateriel, String commentaire) {
        this.materiel = materiel;
        this.utilisateur = utilisateur;
        this.dateEmprunt = dateEmprunt;
        this.retourPrevu = retourPrevu;
        this.retourEffectif = retourEffectif;
        this.suiviEtatMateriel = suiviEtatMateriel;
        this.commentaire = commentaire;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getRetourPrevu() {
        return retourPrevu;
    }

    public void setRetourPrevu(LocalDate retourPrevu) {
        this.retourPrevu = retourPrevu;
    }

    public LocalDate getRetourEffectif() {
        return retourEffectif;
    }

    public void setRetourEffectif(LocalDate retourEffectif) {
        this.retourEffectif = retourEffectif;
    }

    public String getSuiviEtatMateriel() {
        return suiviEtatMateriel;
    }

    public void setSuiviEtatMateriel(String suiviEtatMateriel) {
        this.suiviEtatMateriel = suiviEtatMateriel;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
