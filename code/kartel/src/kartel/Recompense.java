
package kartel;

/**
 * Classe qui modelisse les récompenses
 * Une récompense est un jeton qui donne une valeur x au score du joueur
 * 
 * la récompense dépend de l'état de liberté du Boss de Gans
 * Boss en liverte récompense = valeurBossNonPrisonnier
 * Boss en prison récompense = valeurBossPrisonnier
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see PotDeVin
 * @see Gangster
 */
public abstract class Recompense extends GangMember /*implements Cloneable*/{
    
    /**valeur si le boss est dans la prison*/
    private int valeurBossPrisonnier;
    
    /**valeur si le boss n'est pas dans la prison*/
    private int valeurBossNonPrisonnier;
    

    /**
     * Constructor que establit le Gang et le type de la récompense,
     * et ses valeurs par rapport à l'état de liberté du Boss
     * @param gang Gang auquel il appartient
     * @param type type de récompense
     * @param valeurBossPrisonnier valeur de la récompense si le boss est en prison
     * @param valeurBossNonPrisonnier valeur de la récompense si le boss n'est pas en prison
     */
    public Recompense(Gang gang, String type, int valeurBossPrisonnier, int valeurBossNonPrisonnier){
       super(gang, type);
       this.valeurBossPrisonnier = valeurBossPrisonnier;
       this.valeurBossNonPrisonnier = valeurBossNonPrisonnier;
    }
    
    
    /**
     * Renvoie la valeur de la récompense par rapport
     * à l'état de liberté du Boss
     * @return int valeur de la récompense
     */
    public int getRecompense(){
        if(getGang().isBossInPrison())
            return getValeurBossPrisonnier();
        else
            return getValeurBossNonPrisonnier();
    }

    /**
     * Renvoie la valeur de la récompense si le boss est en prison
     * @return int avec la valeur de la récompense si le boss est en prison
     */
    public int getValeurBossPrisonnier() {
        return valeurBossPrisonnier;
    }

    /**
     * Etabli la valeur de la récompense si le boss est en prison
     * @param valeurBossPrisonnier valeur de la récompense si le boss est en prison
     */
    public void setValeurBossPrisonnier(int valeurBossPrisonnier) {
        this.valeurBossPrisonnier = valeurBossPrisonnier;
    }

    /**
     * Renvoie la valeur de la récompense si le boss n'est pas en prison
     * @return int avec la valeur de la récompense si le boss est en prison
     */
    public int getValeurBossNonPrisonnier() {
        return valeurBossNonPrisonnier;
    }

    /**
     * Etabli la valeur de la récompense si le boss n'est pas en prison
     * @param valeurBossNonPrisonnier valeur de la récompense si le boss n'est pas en prison
     */
    public void setValeurBossNonPrisonnier(int valeurBossNonPrisonnier) {
        this.valeurBossNonPrisonnier = valeurBossNonPrisonnier;
    }
    
    
    /**
     * Intelligence3
     * @return
     * @throws CloneNotSupportedException 
     */
    /*@Override
    public Object clone() throws CloneNotSupportedException  {
            return super.clone();
    }*/
}
