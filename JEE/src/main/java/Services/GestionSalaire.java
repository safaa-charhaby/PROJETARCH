package Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import MODELS.ficheSalaire;
import MODELS.employe;

public class GestionSalaire implements interfacegestionSALAIRE {

    private List<ficheSalaire> fichesSalaire;

    public GestionSalaire() {
        this.fichesSalaire = new ArrayList<>();
    }

    // Ajouter une fiche de salaire
    @Override
    public void creerFicheSalaire(Date date, double nbHeures, double montantBrut, double taxe, int matricule) {
        int numFiche = generateUniqueFicheNumber(); // Implement this method to generate a unique number
        ficheSalaire fiche = new ficheSalaire(numFiche, date, nbHeures, montantBrut, taxe, matricule);
        fichesSalaire.add(fiche);
        System.out.println("Fiche de salaire créée avec succès : " + fiche);
    }

    // Example of generating a unique number (simplified)
    private int generateUniqueFicheNumber() {
        return fichesSalaire.size() + 1; // Simple implementation; can be replaced with more robust logic
    }


    // Modifier une fiche de salaire par numéro
    @Override
    public void modifierFicheSalaire(int numFiche, double nouvelleValeur) {
        for (int i = 0; i < fichesSalaire.size(); i++) {
            if (fichesSalaire.get(i).getNumFiche() == numFiche) {
                fichesSalaire.get(i).setMontantBrut(nouvelleValeur); // Assuming new value is for montant brut
                fichesSalaire.get(i).calculerMontantNet(); // Recalculate the net salary
                System.out.println("Fiche de salaire modifiée avec succès !");
                return;
            }
        }
        System.out.println("Fiche de salaire non trouvée !");
    }

    // Supprimer une fiche de salaire par numéro
    @Override
    public void supprimerFicheSalaire(int numFiche) {
        fichesSalaire.removeIf(fiche -> fiche.getNumFiche() == numFiche);
        System.out.println("Fiche de salaire supprimée avec succès !");
    }

    // Lister toutes les fiches de salaire
    @Override
    public List<ficheSalaire> listerFichesSalaire() {
        return new ArrayList<>(fichesSalaire);
    }

    // Rechercher une fiche de salaire par employé et date
    @Override
    public List<ficheSalaire> rechercherFichesParEmploye(int employeMATRICULE) {
        List<ficheSalaire> result = new ArrayList<>();
        for (ficheSalaire fiche : fichesSalaire) {
            if (fiche.getEmployeMATRICULE() == employeMATRICULE) {
                result.add(fiche);
            }
        }
        return result;
    }

    // Calculer le montant brut d'une fiche
    @Override
    public double calculerMontantBrut(int numFiche) {
        for (ficheSalaire fiche : fichesSalaire) {
            if (fiche.getNumFiche() == numFiche) {
                return fiche.getMontantBrut();
            }
        }
        return 0.0;
    }

    // Calculer le montant net d'une fiche
    @Override
    public double calculerMontantNet(int numFiche) {
        for (ficheSalaire fiche : fichesSalaire) {
            if (fiche.getNumFiche() == numFiche) {
                return fiche.getMontantNet();
            }
        }
        return 0.0;
    }

    // Mettre à jour le taux de taxe pour une fiche
    @Override
    public void mettreAJourTaxe(int numFiche, double taux) {
        for (ficheSalaire fiche : fichesSalaire) {
            if (fiche.getNumFiche() == numFiche) {
                fiche.setTauxTaxe(taux);
                fiche.calculerMontantNet(); // Recalculate net amount after tax change
                System.out.println("Taux de taxe mis à jour avec succès !");
                return;
            }
        }
        System.out.println("Fiche de salaire non trouvée !");
    }

    // Générer un rapport de salaire pour un employé à une date donnée
    @Override
    public void genererRapportSalaire(employe emp, Date date) {
        for (ficheSalaire fiche : fichesSalaire) {
            if (fiche.getEmployeMATRICULE() == emp.getEmployeMATRICULE() && fiche.getDateFiche().equals(date)) {
                System.out.println("Rapport de salaire pour l'employé " + emp.getNom() + " le " + date.toString());
                System.out.println("Montant brut: " + fiche.getMontantBrut());
                System.out.println("Montant net: " + fiche.getMontantNet());
                System.out.println("Taux de taxe: " + fiche.getTauxTaxe());
                return;
            }
        }
        System.out.println("Aucune fiche de salaire trouvée pour cet employé à cette date.");
    }
}
