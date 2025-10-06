package fr.hiit.pretapreter.entity;

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

    // Constructeur par défaut (nécessaire pour JPA)
    public Materiel(String number, String ordinateurPortable, String dell, String disponible, String bonÉtat, LocalDateTime now, String description) {}

    // Constructeur avec paramètres
    public Materiel(String nom, String reference, String etatMateriel, String commentaire, String categorie) {
        this.nom = nom;
        this.reference = reference;
        this.etatMateriel = etatMateriel;
        this.commentaire = commentaire;
        this.categorie = categorie;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getReference() {
        return reference;
    }

    public String getEtatMateriel() {
        return etatMateriel;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getCategorie() {
        return categorie;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setEtatMateriel(String etatMateriel) {
        this.etatMateriel = etatMateriel;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
