package fr.hiit.pretapreter.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


<<<<<<< HEAD
   /* @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;
*/
=======
    // @ManyToOne
    // @JoinColumn(name = "utilisateur_id", nullable = false)
    // private Utilisateur utilisateur;

>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)

    @ManyToOne
    @JoinColumn(name = "materiel_id", nullable = false)
    private Materiel materiel;

    @Column(nullable = false)
    private String emprunteur;

<<<<<<< HEAD

=======
>>>>>>> 8db55db (ajout attribut aux entités)
    @Column(nullable = false)
    private LocalDateTime dateEmprunt;

    @Column (nullable = false)
    private String suiviEtatMateriel;

    @Column(nullable = true)
    private String commentaire;

    @Column(nullable = false)
    private LocalDateTime retourPrevu;

    private LocalDateTime retoutEffectif;

<<<<<<< HEAD
    @Column(nullable = false)
    private String suiviEtatMateriel;

    @Column(nullable = true)
    private String commentaire;


<<<<<<< HEAD
    public Emprunt(Materiel materiel, LocalDateTime dateEmprunt, LocalDateTime retourPrevu,
                   String suiviEtatMateriel, String commentaire) {
        //this.utilisateur = utilisateur;
=======
    public Emprunt( Materiel materiel, LocalDateTime dateEmprunt, LocalDateTime retourPrevu, String statut) {
=======


    public Emprunt( Materiel materiel, LocalDateTime dateEmprunt, LocalDateTime retourPrevu, String suiviEtatMateriel, String commentaire, String emprunteur) {
>>>>>>> 8db55db (ajout attribut aux entités)
        // this.utilisateur = utilisateur; a ajouter dans les parentheses au dessus
>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)
        this.materiel = materiel;
        this.dateEmprunt = dateEmprunt;
        this.retourPrevu = retourPrevu;
        this.suiviEtatMateriel = suiviEtatMateriel;
<<<<<<< HEAD

    }

    public Emprunt() {
=======
        this.commentaire = commentaire;
        this.emprunteur = emprunteur;
>>>>>>> 8db55db (ajout attribut aux entités)

    }


    public Long getId() {
        return id;
    }

<<<<<<< HEAD
    /* public Utilisateur getUtilisateur() {
        return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur; }
*/
=======
    // public Utilisateur getUtilisateur() {
    //     return utilisateur; }
    // public void setUtilisateur(Utilisateur utilisateur) {
    //     this.utilisateur = utilisateur; }

>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)
    public Materiel getMateriel() {
        return materiel; }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel; }


    public LocalDateTime getDateEmprunt() {
        return dateEmprunt; }

    public void setDateEmprunt(LocalDateTime dateEmprunt) {
        this.dateEmprunt = dateEmprunt; }

    public LocalDateTime getRetourPrevu() {
        return retourPrevu; }

    public void setRetourPrevu(LocalDateTime retourPrevu) {
        this.retourPrevu = retourPrevu; }

    public LocalDateTime getRetourEffectif() {
        return retoutEffectif; }

    public void setRetourEffectif(LocalDateTime retoutEffectif) {
        this.retoutEffectif = retoutEffectif; }

<<<<<<< HEAD
<<<<<<< HEAD

    public void getSuiviEtatMateriel(String suiviEtatMateriel) {
        this.suiviEtatMateriel = suiviEtatMateriel; }
    public void setSuiviEtatMateriel(String suiviEtatMateriel) {
        this.suiviEtatMateriel = suiviEtatMateriel; }

    public String getCommentaire() {
        return commentaire;}
    public void setCommentaire() {
        this.commentaire = commentaire; }
=======
    public String getStatut() {
        return statut; }
    public void setStatut(String statut) {
        this.statut = statut; }

=======
>>>>>>> 8db55db (ajout attribut aux entités)
        public String getSuiviEtatMateriel() {
        return suiviEtatMateriel; }

    public void setSuiviEtatMateriel(String suiviEtatMateriel) {
        this.suiviEtatMateriel = suiviEtatMateriel; }
<<<<<<< HEAD
>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)
=======

    public String getCommentaire() {
        return commentaire; }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire; }

    public String getEmprunteur() {
        return emprunteur; }
        
    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur; }
>>>>>>> 8db55db (ajout attribut aux entités)
}