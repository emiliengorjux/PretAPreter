package fr.hiit.pretapreter.entity;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;



@Repository
public class UtilisateursDao {
    public void ajoutUtilisateur(String nom, String prenom, String email) {
        String sql = "INSERT INTO utilisateurs (nom, prenom, email) VALUES (?, ?, ?)";
        try (Connection connection = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom); // remplace ? de values par nom
            preparedStatement.setString(2, prenom); // remplace ? de values par prenom
            preparedStatement.setString(3, email); //remplace ? de values par email
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) ajoutée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUtilisateur(String ancienNom, String nouveauNom, String nouveauPrenom, String nouvelEmail) {
        String updateSql = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ? WHERE nom = ?";
        try (Connection connection = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, nouveauNom); // comme plus haut, mais ici update
            preparedStatement.setString(2, nouveauPrenom);
            preparedStatement.setString(3, nouvelEmail);
            preparedStatement.setString(4, ancienNom);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " lignes mises à jour.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(String nom) {
        String sql = "DELETE FROM utilisateurs WHERE nom = ?";
        try (Connection connection = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " lignes supprimées.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherUtilisateurs() {
        String sql = "SELECT nom, prenom, email FROM utilisateurs";
        try (Connection connection = DataBaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Liste des utilisateurs :");
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                System.out.println("Nom: " + nom + ", Prénom: " + prenom + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

