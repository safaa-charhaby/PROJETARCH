package Services;

import java.util.List;
import MODELS.employe;

public interface interfacegestionEMPL {
    void ajouterEmploye(employe employe);
    void supprimerEmploye(int matricule);
    List<employe> afficherEmployes();
    employe rechercherEmployeParMatricule(int matricule); // New method added
}
