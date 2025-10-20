 package fr.hiit.pretapreter.service.repository.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

 @Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
     @JoinColumn(name = "materiel_id", nullable = false)
     private Materiel materiel;

     @ManyToOne
     @JoinColumn(name = "emprunt_id", nullable = false)
     private Materiel emprunt;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;


    public Utilisateur() {}

    public Utilisateur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;

    }

    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }

    public String getNom() {
        return nom; }
    public void setNom(String nom) {
        this.nom = nom; }

    public String getPrenom() {
        return prenom; }
    public void setPrenom(String prenom) {
        this.prenom = prenom; }

    public String getEmail() {
        return email; }
    public void setEmail(String email) {
        this.email = email; }
}