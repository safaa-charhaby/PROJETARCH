package Services;
import java.util.Date;
import java.util.List;

import MODELS.ficheSalaire;
import MODELS.employe;
public interface interfacegestionSALAIRE {
    public void creerFicheSalaire(Date dateF, double nbHeures,  double montantBrut, double taxe, int matricule);
    public void modifierFicheSalaire(int numFiche, double nouvelleValeur);
    public void supprimerFicheSalaire(int numFiche);
    public double calculerMontantBrut(int numFiche);
    public double calculerMontantNet(int numFiche);
    public void mettreAJourTaxe(int numFiche, double taux);
    public void genererRapportSalaire(employe emp, Date date);
    public List<ficheSalaire> listerFichesSalaire();
    public List<ficheSalaire> rechercherFichesParEmploye(int employeMATRICULE);
    }

