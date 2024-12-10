package Services;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import MODELS.employe;
import MODELS.ficheSalaire;
import tools.MySqlConnection;


public class GestionSalairesv2 implements interfacegestionSALAIRE {
    private Connection conn;

    public GestionSalairesv2() {
        this.conn = MySqlConnection.getConnectionMysql();
    }

    @Override
    public void creerFicheSalaire(Date dateF, double nbHeures,  double montantBrut, double taxe, int matricule) {
        // Check if the matricule exists in the employes table
        String checkMatriculeSQL = "SELECT COUNT(*) FROM employes WHERE matricule = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkMatriculeSQL)) {
            checkStmt.setInt(1, matricule);

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    // Matricule exists, proceed to create the fiche de salaire
                    String sql = "INSERT INTO salaires (datef, nbheures, Montantbrut, Taxe, Montantnet, Matricule) VALUES (?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setDate(1, new java.sql.Date(dateF.getTime()));
                        stmt.setDouble(2, nbHeures);
                        stmt.setDouble(3, montantBrut);
                        stmt.setDouble(4, taxe);
                        stmt.setDouble(5, montantBrut - (montantBrut * taxe / 100)); // Calculate Montantnet
                        stmt.setInt(6, matricule);

                        stmt.executeUpdate();
                        System.out.println("Fiche de salaire créée avec succès !");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Erreur : Le matricule " + matricule + " n'existe pas dans la table des employés.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierFicheSalaire(int numFiche, double nouvelleValeur) {
        String sql = "UPDATE salaires SET Montantbrut = ?, Montantnet = Montantbrut - (Montantbrut * Taxe / 100) WHERE Numfiche = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nouvelleValeur);
            stmt.setInt(2, numFiche);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Fiche de salaire modifiée avec succès !");
            } else {
                System.out.println("Aucune fiche trouvée avec ce numéro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerFicheSalaire(int numFiche) {
        String sql = "DELETE FROM salaires WHERE Numfiche = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numFiche);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Fiche de salaire supprimée avec succès !");
            } else {
                System.out.println("Aucune fiche trouvée avec ce numéro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculerMontantBrut(int numFiche) {
        String sql = "SELECT Montantbrut FROM salaires WHERE Numfiche = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numFiche);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Montantbrut");
            } else {
                System.out.println("Aucune fiche trouvée avec ce numéro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public double calculerMontantNet(int matricule) {
        String sql = "SELECT Montantnet FROM salaires WHERE Matricule = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricule);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Montantnet");
            } else {
                System.out.println("Aucune fiche trouvée pour ce matricule.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public void mettreAJourTaxe(int numFiche, double taux) {
        String sql = "UPDATE salaires SET Taxe = ?, Montantnet = Montantbrut - (Montantbrut * ? / 100) WHERE Numfiche = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, taux);
            stmt.setDouble(2, taux);
            stmt.setInt(3, numFiche);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Taux de taxe mis à jour avec succès !");
            } else {
                System.out.println("Aucune fiche trouvée avec ce numéro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ficheSalaire> listerFichesSalaire() {
        List<ficheSalaire> ficheSalaireList = new ArrayList<>();
        String sql = "SELECT * FROM salaires";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Create ficheSalaire object from result set
                ficheSalaire fiche = new ficheSalaire(
                        rs.getInt("Numfiche"),              // Numfiche
                        rs.getDate("datef"),                // Date
                        rs.getDouble("Montantbrut"),        // Montantbrut
                        rs.getDouble("Taxe"),               // Taxe
                        rs.getDouble("Montantnet"),         // Montantnet
                        rs.getInt("Matricule")              // Matricule
                );

                // Add the ficheSalaire object to the list
                ficheSalaireList.add(fiche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ficheSalaireList;
    }


    @Override
    public List<ficheSalaire> rechercherFichesParEmploye(int employeId) {
        String sql = "SELECT * FROM salaires WHERE Matricule = ?";
        List<ficheSalaire> fiches = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ficheSalaire fiche = new ficheSalaire(
                        rs.getInt("Numfiche"),
                        rs.getDate("datef"),
                        rs.getDouble("Montantbrut"),
                        rs.getDouble("nbheures"),
                        rs.getDouble("taxe"),
                        rs.getInt("Matricule")
                );
                fiches.add(fiche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fiches;
    }

    public void genererRapportSalaire(employe emp, Date date) {
        String nom = emp.getNom();
        String prenom = emp.getPrenom();
        int matricule = emp.getEmployeMATRICULE();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);

        String sql = "SELECT * FROM salaires WHERE Matricule = ? AND Datef = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set parameters for the query
        stmt.setInt(1, matricule);
        stmt.setString(2, formattedDate);

        // Execute the query
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Retrieve the data from the result set
                double montantBrut = rs.getDouble("Montantbrut");
                double montantNet = rs.getDouble("Montantnet");
                double taxe = rs.getDouble("Taxe");
                double nbHeures = rs.getDouble("NbHeures");

                // Generate the salary report
                String rapport = "Rapport de Salaire - " + nom + " " + prenom + "\n";
                rapport += "Matricule: " + matricule + "\n";
                rapport += "Date: " + date.toString() + "\n";
                rapport += "Nombre d'heures travaillées: " + nbHeures + " heures\n";
                rapport += "Montant brut: " + montantBrut + " EUR\n";
                rapport += "Taxe: " + taxe + " EUR\n";
                rapport += "Montant net: " + montantNet + " EUR\n";

                // Display the report in the console
                System.out.println(rapport);
            } else {
                System.out.println("Aucune fiche de salaire trouvée pour cet employé à la date spécifiée.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur lors de la récupération de la fiche de salaire.");
    }

}
}