package fr.hiit.pretapreter.service.repository.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materiel_id", nullable = false)
    private Materiel materiel;

    @Column(nullable = false)
    private LocalDate dateEmprunt;

    @Column(nullable = false)
    private LocalDate retourPrevu;

    @Column(nullable = true)
    private LocalDate retourEffectif;

    @Column(nullable = false)
    private String suiviEtatMateriel;

    @Column(nullable = true)
    private String commentaire;


    @OneToMany(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private Set<Utilisateur> utilisateur = new HashSet<>();

    public Emprunt(Materiel materiel, LocalDate dateEmprunt,
                   LocalDate retourPrevu, LocalDate retourEffectif, String suiviEtatMateriel, String commentaire) {
        this.materiel = materiel;
        this.dateEmprunt = dateEmprunt;
        this.retourPrevu = retourPrevu;
        this.retourEffectif = retourEffectif;
        this.suiviEtatMateriel = suiviEtatMateriel;
        this.commentaire = commentaire;
    }

    public Emprunt() {
    }


    public Long getId() {
        return id;
    }


    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
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

    public Set<Utilisateur> getUtilisateur() {
        return utilisateur;

    }
}
