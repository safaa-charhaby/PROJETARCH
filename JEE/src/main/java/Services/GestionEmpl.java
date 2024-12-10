package Services;

import MODELS.employe;
import java.util.ArrayList;
import java.util.List;

public class GestionEmpl implements interfacegestionEMPL {
    private final ArrayList<employe> employes = new ArrayList<>();

    @Override
    public void ajouterEmploye(employe employe) {
        employes.add(employe);
        System.out.println("Employé ajouté avec succès : " + employe);
    }

    @Override
    public void supprimerEmploye(int matricule) {
        boolean removed = employes.removeIf(employe -> employe.getEmployeMATRICULE() == matricule);
        if (removed) {
            System.out.println("Employé avec le matricule " + matricule + " supprimé.");
        } else {
            System.out.println("Aucun employé trouvé avec le matricule " + matricule + ".");
        }
    }

    @Override
    public List<employe> afficherEmployes() {
        if (employes.isEmpty()) {
            System.out.println("Aucun employé enregistré.");
        } else {
            System.out.println("Liste des employés :");
            for (employe employe : employes) {
                System.out.println(employe);
            }
        }
        return new ArrayList<>(employes); // Returning a copy of the list
    }

    /**
     * Recherche un employé par matricule.
     * @param matricule Matricule de l'employé à rechercher.
     * @return L'employé correspondant ou null s'il n'existe pas.
     */
    public employe rechercherEmployeParMatricule(int matricule) {
        for (employe emp : employes) {
            if (emp.getEmployeMATRICULE() == matricule) {
                System.out.println("Employé trouvé : " + emp);
                return emp;
            }
        }
        System.out.println("Aucun employé trouvé avec le matricule " + matricule + ".");
        return null;
    }
}

