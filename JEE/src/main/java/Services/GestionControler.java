package Services;
import Services.GestionEmpl;
import Services.GestionEmplv2;
import Services.GestionSalaire;
import Services.GestionSalairesv2;
public class GestionControler {
    private GestionEmplv2 gestionEmplJDBC;
    private GestionSalairesv2 gestionSalaireJDBC;
    private GestionEmpl GestionEmplLISTE;
    private GestionSalaire GestionSalaireLISTE;

    public GestionControler(boolean useJDBC) {
        if (useJDBC) {
            this.gestionEmplJDBC = new GestionEmplv2();
            this.gestionSalaireJDBC = new GestionSalairesv2();
        } else {
            this.GestionEmplLISTE= new GestionEmpl();
            this.GestionSalaireLISTE = new GestionSalaire();
        }
    }}