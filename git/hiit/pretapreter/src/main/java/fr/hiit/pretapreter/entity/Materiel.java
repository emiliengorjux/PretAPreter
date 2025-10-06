package fr.hiit.pretapreter.entity;

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


    @Column (nullable = false)
    private String etatMateriel;

    @Column (nullable = false)
    private String categorie;

    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();

    public Materiel() {}

    public Materiel(String nom, String reference, String etatMateriel, String suiviEtatMateriel, String categorie) {
        this.nom = nom;
        this.reference = reference;
        this.etatMateriel = etatMateriel;
        this.categorie = categorie;
    }
    public Long getId() {
        return id; }

    public String getNom() {
        return nom; }
    public void setNom(String nom) {
        this.nom = nom; }

    public String getReference() {
        return reference; }

    public void setReference(String reference) {
        this.reference = reference; }

    public String getCategorie() {
        return categorie; }

    public void setCategorie(String categorie){
        this.categorie = categorie; }

    public Set<Emprunt> getEmprunts() {
        return emprunts; }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts; }

    public String getEtatMateriel() {
        return etatMateriel; }

    public void setEtatMateriel(String etatMateriel) {
        this.etatMateriel = etatMateriel; }

    

}