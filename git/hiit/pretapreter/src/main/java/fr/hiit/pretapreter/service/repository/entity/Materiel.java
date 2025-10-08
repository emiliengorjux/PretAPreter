package fr.hiit.pretapreter.service.repository.entity;

import java.time.LocalDateTime;
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
@Table(name = "materiel")
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true, nullable = true)
    private String reference;

    @Column(nullable = false)
    private String etatMateriel;

    @Column(nullable = true)
    private String commentaire;

    @Column(nullable = false)
    private String categorie;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateAjout = LocalDateTime.now();

    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();


    public Materiel(String reference, String nom, String categorie, String etatMateriel, String commentaire, LocalDateTime dateAjout) {
        this.reference = reference;
        this.nom = nom;
        this.categorie = categorie;
        this.etatMateriel = etatMateriel;
        this.commentaire = commentaire;
        this.dateAjout = LocalDateTime.now();
    }


    public Materiel() {

    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getEtatMateriel() {
        return etatMateriel;
    }

    public void setEtatMateriel(String etatMateriel) {
        this.etatMateriel = etatMateriel;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }


    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }


    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
