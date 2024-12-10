package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MySqlConnection;
import MODELS.employe;

public class GestionEmplv2 implements interfacegestionEMPL {
    private Connection connection;

    public GestionEmplv2() {
        this.connection = MySqlConnection.getConnectionMysql();
    }

    @Override
    public void ajouterEmploye(employe employe) {
        String sql = "INSERT INTO employes (Matricule, nom, prenom, Adresse) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, employe.getEmployeMATRICULE());
            pstmt.setString(2, employe.getNom());
            pstmt.setString(3, employe.getPrenom());
            pstmt.setString(4, employe.getAdresse());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerEmploye(int matricule) {
        String sql = "DELETE FROM employes WHERE Matricule = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, matricule);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<employe> afficherEmployes() {
        List<employe> employes = new ArrayList<>();
        String sql = "SELECT * FROM employes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int matricule = rs.getInt("Matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                employes.add(new employe(matricule, nom, prenom, adresse));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (employe emp : employes) {
            System.out.println(emp);
        }
        return employes;
    }

    @Override
    public employe rechercherEmployeParMatricule(int matricule) {
        List<employe> employes = afficherEmployes();
        for (employe emp : employes) {
            if (emp.getEmployeMATRICULE() == matricule) {
                return emp;
            }
        }
        return null; // If no employee is found
    }
}
