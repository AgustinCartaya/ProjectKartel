/**
 * 
 * 
 * classe parent: Recompense
 */
package kartel;

/**
 * Classe qui modelisse les Gansters
 * un gangster est un membre d'un Gang qui a un niveu
 * la recompense donneé pour un gangster deppend de son niveau
 * et l'état de liberté de son Boss (l'anti recompense d'un Gangster est le negatif de sa recompense)
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Gang
 * @see Recompense
 * @see Showable
 */
public class Gangster extends Recompense{
    
    
    /**indique le niveau du gangster (il est utilisé pour établir la récompense)*/
    private int niveau;
    
    /**type de la class*/
    public static final String TYPE = "GANGSTER";
    
    /**
     * Initialise un Gangster appartenant a un Gang et avec un niveau
     * les récompenses est établi selon son niveau
     * @param gang Gang auquel il appartient
     * @param niveau niveau du gangster
     */
    public Gangster(Gang gang, int niveau){
        super(gang, TYPE, niveau, -niveau);
        this.niveau = niveau;
    }

    /**
     * Renvoie le niveau du Gangster
     * @return int avec le niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Établi le niveau du Gangster et ses recompenses
     * @param niveau int contenant le niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
        setValeurBossPrisonnier(niveau);
        setValeurBossNonPrisonnier(-niveau);
    }
  
     
    /**
     * Renvoie le texte decriptif du Gangster
     * @return String avec la decription du Gangster
     */
    @Override
    public String toString() {
        return  TYPE +'('+getNiveau()+"): "+getGang().getNom();
    }
   

    /**
     * Renvoie le texte qui va représenter le Gangster lors du jeu
     * @return String avec ce qui va etre affiche lors du jeu
     * @see Showable
     */
    @Override
    public String show() {
        return  niveau + getGang().getAbb();
    }
    

    
}
