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

<<<<<<< HEAD
<<<<<<< HEAD
    @Column (nullable = true)
    private String commentaire;

=======
>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)
    @Column (nullable = false)
    private String categorie;

    @Column(nullable = false , updatable = false)
=======
    @Column(nullable = true)
    private String commentaire;

    @Column(nullable = false)
    private String categorie;

    @Column(nullable = false, updatable= false)
>>>>>>> 8db55db (ajout attribut aux entités)
    private LocalDateTime dateAjout = LocalDateTime.now();

    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();

    public Materiel() {
    }

<<<<<<< HEAD
    public Materiel(String nom, String reference, String etatMateriel, String commentaire,
                    String categorie, LocalDateTime dateAjout) {
=======
    public Materiel(String nom, String reference, String etatMateriel, String suiviEtatMateriel, String categorie , LocalDateTime dateAjout, String commentaire) {
>>>>>>> 8db55db (ajout attribut aux entités)
        this.nom = nom;
        this.reference = reference;
        this.etatMateriel = etatMateriel;
<<<<<<< HEAD
        this.commentaire = commentaire;
=======
>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)
        this.categorie = categorie;
<<<<<<< HEAD
        this.dateAjout = LocalDateTime.now();
=======
        this.dateAjout = dateAjout;
        this.commentaire = commentaire;
>>>>>>> 8db55db (ajout attribut aux entités)
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

        this.nom = nom; }
    public void getNom(String nom) {
        this.nom = nom; }


        this.nom = nom;
    

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }


    public String getEtatMateriel() {
        return etatMateriel;
    }

    public void setEtatMateriel(String etatMateriel) {
        this.etatMateriel = etatMateriel;
    }
    public LocalDateTime getDateAjout() {
        return dateAjout;
    }
    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public String getCommentaire() {
        return commentaire; }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire; }

    public LocalDateTime getDateAjout() {
        return dateAjout; }
        
    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout; }  
=======
    
>>>>>>> b6a6fa1 (modif entité utilisateur mis de coté)
=======
    public String getCommentaire() {
        return commentaire;
    }
>>>>>>> 8db55db (ajout attribut aux entités)

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}
