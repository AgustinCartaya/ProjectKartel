
package kartel;

/**
 * Une implémentation de cette classe crée un objet de type Boss
 * un Boss est un GangMember et ce sont les Jetons principals du jeu
 * un Boss appartient à un gang et il peut être libre ou non,
 * l'état de liberté d'un groupe de boss met fin au jeu
 *
 * @author Manel Meftah
 * @author Agustin Cartaya
 *
 * @see Gang
 * @see GangMember
 */
public class Boss extends GangMember{
    
    /**true si le boss est prisoner*/
    private boolean prisonnier; 
    
    /**type de la class*/
    public static final String TYPE = "BOSS";

    /**
     * Initialisation du Boss appartenant à un Gang
     * le Boss n'est pas prisoner par defaut
     * @param gang Gang auquel il appartient
     */
    public Boss(Gang gang){
        super(gang, TYPE);
        this.prisonnier = false;
    }

    /**
     * Renvoie l'état de liberté du Boss
     * @return true si le Boss est prisoner
     */
    public boolean isPrisonnier() {
        return prisonnier;
    }
    
    /**
     * Établi l'état de liberté du Boss
     * @param prisonnier boolean pour etablir l'état
     */
    public void setPrisonnier(boolean prisonnier) {
        this.prisonnier = prisonnier;
    }
    
    
    /**
     * Renvoie le texte decriptif du Boss
     * @return String avec la decription du Boss
     */
    @Override
    public String toString() {
        return  TYPE +": "+getGang().getNom();
    }

    /**
     * Renvoie le texte qui va représenter le Boss lors du jeu
     * @return String avec ce qui va etre affiche lors du jeu
     * @see Showable
     */
    @Override
    public String show() {
        return  '[' + getGang().getAbb()+ ']';
    }

}
