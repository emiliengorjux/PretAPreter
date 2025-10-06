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


    // @ManyToOne
    // @JoinColumn(name = "utilisateur_id", nullable = false)
    // private Utilisateur utilisateur;


    @ManyToOne
    @JoinColumn(name = "materiel_id", nullable = false)
    private Materiel materiel;

    @Column(nullable = false)
    private String emprunteur;

    @Column(nullable = false)
    private LocalDateTime dateEmprunt;

    @Column (nullable = false)
    private String suiviEtatMateriel;

    @Column(nullable = true)
    private String commentaire;

    @Column(nullable = false)
    private LocalDateTime retourPrevu;

    private LocalDateTime retoutEffectif;



    public Emprunt( Materiel materiel, LocalDateTime dateEmprunt, LocalDateTime retourPrevu, String suiviEtatMateriel, String commentaire, String emprunteur) {
        // this.utilisateur = utilisateur; a ajouter dans les parentheses au dessus
        this.materiel = materiel;
        this.dateEmprunt = dateEmprunt;
        this.retourPrevu = retourPrevu;
        this.suiviEtatMateriel = suiviEtatMateriel;
        this.commentaire = commentaire;
        this.emprunteur = emprunteur;

    }


    public Long getId() {
        return id; }

    // public Utilisateur getUtilisateur() {
    //     return utilisateur; }
    // public void setUtilisateur(Utilisateur utilisateur) {
    //     this.utilisateur = utilisateur; }

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

        public String getSuiviEtatMateriel() {
        return suiviEtatMateriel; }

    public void setSuiviEtatMateriel(String suiviEtatMateriel) {
        this.suiviEtatMateriel = suiviEtatMateriel; }

    public String getCommentaire() {
        return commentaire; }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire; }

    public String getEmprunteur() {
        return emprunteur; }
        
    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur; }
}