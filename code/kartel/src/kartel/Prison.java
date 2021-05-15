
package kartel;

import java.util.ArrayList;

/**
 * Classe qui modélise une prison qui peut contenir des Bosses
 * la prison a un nombre max de bosses que peut contenir,
 * si le nombre est atteint, le jeu s'arrête
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Boss
 * @see Plateau
 * @see Reutilisable
 * @see Showable
 */
public class Prison implements Reutilisable, Showable{
    
    /**liste des boss en prison*/
    private ArrayList<Boss> bosses;
    
    /**nombre max de prisonniers*/
    private int maxPrisonniers;



    /**
     * Initilaisation de la prision avec un nombre max de prisonniers
     * la liste de bosses prisonniers est vide
     * @param maxPrisonniers nombre des prisonniers max qui peuvent avoir dans la prison
     * @see Boss
     */
    public Prison(int maxPrisonniers){
        this.maxPrisonniers = maxPrisonniers;
        this.bosses = new ArrayList<>();
    }
    
    

    
    /**
     * Ajout un Boss Prisoner a la liste
     * et informa au Gang du boss qu'il est prisonnier
     * 
     * @param boss Boss à ajouter à la liste
     * @return true si le boss été ajouté à la liste de prisonniers
     * 
     * @see Gang#bossInPrision(boolean) 
     */
    public boolean addBoss(Boss boss){
        if(getNbBosses() == maxPrisonniers)
           return false;
        
        boss.getGang().bossInPrision(true);
        this.bosses.add( boss );
        return true;
    }

    /**
     * Renvoie la quantité de prisonniers dans la liste
     * @return int avec la quantité de prisonniers dans la liste
     */
    public int getNbBosses() {
        return this.bosses.size();
    }
    
    /**
     * Renvoi true si le nombre de prisioniers est 
     * égal au nombre de bosses dans la lise 
     * @return true si la prison est complete
     */
    public boolean isPrisonComplete(){
        return getNbBosses() >= this.maxPrisonniers;
    }
    
    
    /**
     * Renvoie un boss par son indice dans la liste de prisonnier
     * si cet index existe dans la liste
     * @param index indice du boss dans la liste
     * @return Boss trouvé ou null dans le cas que l'index n'esxiste pas
     */
    public Boss getBoss(int index){
        if(index >=0 && index< getNbBosses())
            return this.bosses.get(index);
        return null;
    }
    
    /**
     * Renvoie la quantité maximal de prisonniers qui peuvent etre dans la prison
     * @return int avec la quantité maximal
     */
    public int getMaxPrisoniers() {
        return maxPrisonniers;
    }

    /**
     * Etabli la quantité maximal de prisonniers qui peuvent etre dans la prison
     * @param maxPrisonniers quantité maximal de prisonniers qui peuvent etre dans la prison
     */
    public void setMaxPrisonniers(int maxPrisonniers) {
        this.maxPrisonniers = maxPrisonniers;
    }
    

    /**
     * Renvoi la liste de bosses en prison
     * @return ArrayList de Boss en prison
     */
    public ArrayList<Boss> getBosses(){
        return this.bosses;
    }
    
    /**
     * méthode pour réinitialiser la Prison après d'un jeu
     * @see Reutilisable
     */
    @Override
    public void reset() {
        for(Boss b:this.bosses)
            b.setPrisonnier(false);
        this.bosses.clear();
    }

    /**
     * Renvoie le texte decriptif de la Prison
     * @return String avec la decription de la Prison
     */
    @Override
    public String toString() {
        String s = "Prison: max Boss acceptées(" + getMaxPrisoniers() +"): " ;
        for (int i = 0; i < getNbBosses(); i++) {
            s+= getBoss(i).show() + " - ";
        }
        return  s;
         
    }
    
    /**
     * Renvoie le texte qui va représenter la Prison lors du jeu
     * @return String avec ce qui va etre affiche lors du jeu
     * @see Showable
     */
    @Override
    public String show() {
        String s = "Prison:";
        for (int i = 0; i < getNbBosses(); i++) {
            s+= getBoss(i).show() + " - ";
        }
        return  s;
    }



    
}
