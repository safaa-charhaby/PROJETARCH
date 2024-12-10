package MODELS;

public class employe {
    private int employeMATRICULE;
    private String nom;
    private String prenom;
    private String adresse;

    public employe(int employeMATRICULE, String nom, String prenom, String adresse) {
        this.employeMATRICULE = employeMATRICULE;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public int getEmployeMATRICULE() {
        return employeMATRICULE;
    }

    public void setEmployeMATRICULE(int employeMATRICULE) {
        this.employeMATRICULE = employeMATRICULE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return employeMATRICULE+":" +nom + " " + prenom + " - Adresse: " + adresse;
    }
}
