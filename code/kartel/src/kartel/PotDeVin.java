
package kartel;

/**
 * Classe qui modelisse les Pots de vin
 * un PotDeVin est un membre d'un Gang qui a une recompense
 * la recompense donneé pour un PotDeVin deppend de sa valeur
 * et l'état de liberté de son Boss (l'anti recompense d'un PotDeVin est zero par defaut)
 *
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Gang
 * @see Recompense
 * @see Showable
 */
public class PotDeVin extends Recompense {
    
    /**type de la class*/
    public static final String TYPE = "POT DE VIN";
    /**
     * Initialise un PotDeVin appartenant a un Gang et avec une recompense
     * @param gang Gang auquel il appartient
     * @param recompense valeur de la recompense
     */
    public PotDeVin(Gang gang, int recompense){
        super (gang, TYPE, 0, recompense);
    }
    
    /**
     * Initialise un PotDeVin appartenant a un Gang
     * et avec une recompense de 3 par défaut
     * @param gang Gang auquel il appartient
     */
    public PotDeVin(Gang gang){
        this(gang, 3);
    }
    
    /**
     * Renvoie le texte decriptif du PotDeVin
     * @return String avec la decription du PotDeVin
     */
    @Override
    public String toString() {
        return  TYPE + '('+this.getValeurBossNonPrisonnier()+"): "+getGang().getNom();
    }    

    /**
     * Renvoie le texte qui va représenter le PotDeVin lors du jeu
     * @return String avec ce qui va etre affiche lors du jeu
     * @see Showable
     */
    @Override
    public String show() {
        return  '$' + getGang().getAbb();
    }
}
