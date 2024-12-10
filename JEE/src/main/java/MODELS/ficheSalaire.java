package MODELS;

import java.util.Date;

public class ficheSalaire {
    private double nbrheures;
    private int numFiche;
    private Date dateFiche;
    private double montantBrut;
    private double montantNet;
    private double tauxTaxe; // Pourcentage (par ex. 20 pour 20%)
    private int employeMATRICULE;

    // Constructeur
    public ficheSalaire(int numFiche, Date dateFiche, double montantBrut, double nbrheures, double tauxTaxe, int employeMATRICULE) {
        this.numFiche = numFiche;
        this.dateFiche = dateFiche;
        this.nbrheures = nbrheures;
        this.montantBrut = montantBrut;
        this.tauxTaxe = tauxTaxe;
        this.employeMATRICULE = employeMATRICULE;
        this.montantNet = calculerMontantNet(); // Calcul automatique du montant net
    }

    // Getters et Setters
    public int getNumFiche() {
        return numFiche;
    }

    public void setNumFiche(int numFiche) {
        this.numFiche = numFiche;
    }

    public Date getDateFiche() {
        return dateFiche;
    }

    public void setDateFiche(Date dateFiche) {
        this.dateFiche = dateFiche;
    }

    public double getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(double montantBrut) {
        this.montantBrut = montantBrut;
        this.montantNet = calculerMontantNet(); // Recalculer le montant net
    }

    public double getTauxTaxe() {
        return tauxTaxe;
    }

    public void setTauxTaxe(double tauxTaxe) {
        this.tauxTaxe = tauxTaxe;
        this.montantNet = calculerMontantNet(); // Recalculer le montant net
    }

    public int getEmployeMATRICULE() {
        return employeMATRICULE;
    }

    public void setEmployeMATRICULE(int employeMATRICULE) {
        this.employeMATRICULE = employeMATRICULE;
    }

    public double getMontantNet() {
        return montantNet;
    }

    public double getNbrheures() {
        return nbrheures;
    }

    public void setNbrheures(double nbrheures) {
        this.nbrheures = nbrheures;
    }

    // Méthode pour calculer le montant net
    public double calculerMontantNet() {
        double taxe = montantBrut * (tauxTaxe / 100); // Calcul de la taxe
        return montantBrut - taxe; // Montant brut moins la taxe
    }

    // Méthode toString
    @Override
    public String toString() {
        return "ficheSalaire{" +
                "nbrheures=" + nbrheures +
                ", numFiche=" + numFiche +
                ", dateFiche=" + dateFiche +
                ", montantBrut=" + montantBrut +
                ", montantNet=" + montantNet +
                ", tauxTaxe=" + tauxTaxe +
                ", employeMATRICULE=" + employeMATRICULE +
                '}';
    }
}
