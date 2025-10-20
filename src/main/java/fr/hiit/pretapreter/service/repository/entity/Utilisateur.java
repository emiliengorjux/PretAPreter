 package fr.hiit.pretapreter.service.repository.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;



    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<Materiel> materiel = new HashSet<>();


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

    public Set<Emprunt> getEmprunts() {
        return emprunts; }
    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts; }

    public Set<Materiel> getMateriel() {
        return materiel;
    }

    public void setMateriel(Set<Materiel> materiel) {
        this.materiel = materiel;
    }
}