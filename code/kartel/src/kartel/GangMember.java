
package kartel;

/**
 * Classe qui modélise tous le membres appartenant à un Gang
 * chaque membre comporte son type et son Gang
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Gang
 * @see Boss
 * @see Recompense
 * @see Gangster
 * @see PotDeVin
 * @see Showable
 */
public abstract class GangMember implements Showable{

    /**Type de membre*/
    private final String type;
    
    /**gang auquel il appartient*/
    private Gang gang;
    
    /**
     * Initialisation du membre avec un Gang et son type
     * @param gang Gang auquel il appartient
     * @param type String avec le type de membre
     */
    public GangMember (Gang gang, String type){
        this.gang = gang;
        this.type = type;
    }
    
    /**
     * Renvoie le Gang auquel le membre appartient
     * @return Gang auquel le membre appartient
     * @see Gang
     */
    public Gang getGang() {
        return this.gang;
    }
    
    /**
     * Renvoie le type de membre
     * @return String avec le type
     */
    public String getType() {
        return type;
    } 
}
