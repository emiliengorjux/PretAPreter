package fr.hiit.pretapreter.service.repository.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "materiel")
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true)
    private String reference;

    @Column(name = "etat_materiel", nullable = false)
    private String etatMateriel;

    private String commentaire;

    @Column(nullable = false)
    private String categorie;

    @Column(name = "date_ajout", nullable = false, updatable = false)
    private LocalDateTime dateAjout = LocalDateTime.now();

    // 🔁 Un matériel peut avoir plusieurs emprunts au fil du temps
    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Emprunt> emprunts = new HashSet<>();

    public Materiel() {
    }

    public Materiel(String reference, String nom, String categorie, String etatMateriel, String commentaire) {
        this.reference = reference;
        this.nom = nom;
        this.categorie = categorie;
        this.etatMateriel = etatMateriel;
        this.commentaire = commentaire;
        this.dateAjout = LocalDateTime.now();
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
