/**
 * 
 */
package kartel;

/**
 * Classe qui modelisse le detective du jeu.
 * le détective est le jeton avec laquelle tous les joueurs vont jouer,
 * un detective peut avancer sur le Plateau passant pour la position
 * des autres jetons, et si alors qu'il avance
 * dépasse la position max il revient a la première position
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Plateau
 * @see GangMember
 */

public class Detective implements Showable{
    
    /**position courante du détective sur le plateau*/
    private int position;
    
    /**position maximale à laquelle le détective peut atteindre*/
    private int max;
    
     /**
     * Initialisation du Détective avec position maximal
     * donné par paramètre et la position actuelle égale à zéro
     * @param max int avec la cantite max de pas qui peut faire le detective
     */
    public Detective(int max){
        this.max = max;
        this.position =0;
    }
    
    /**
     * Initialisation du Détective avec une position maximal et
     * position actuelle égales à zéro
     */
    public Detective(){
        this(0);
    }

    /**
     * avance le détective selon les pas passé par paramètre,
     * revenant au début si le maximum est passe
     * @param deplacement int avec les pas à deplacer pour le detective
     */
    public void avancer(int deplacement) {
        
        this.position += deplacement-1;
        if(this.position >= this.max)
            this.position %=this.max ;

    }
    
   /**
    * Renvoie la position actuelle du détective
    * @return int avec  la position actuelle
    */ 
    public int getPosition() {
        return position;
    }
    
    /**
     * Établi la position actuelle du détective
     * si c'est permis
     * @param pos int avec la position à établir
     */
    public void setPosition( int pos){
        if(pos>=0 && pos<getMax())
            this.position = pos;
    }

    /**
     * Renvoie la position maximal que le détective peut atteindre
     * @return int avec la position maximal
     */
    public int getMax() {
        return max;
    }

    /**
     * Établi la position maximal que le détective peut atteindre
     * @param max int contannt la position maximal
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Renvoie le texte decriptif du détective
     * @return String avec la decription du détective
     */
    @Override
    public String toString() {
        return "Detective: " + "position = " + position + ", max = " + max ;
    }
    
    /**
     * Renvoie le texte qui va représenter le détective lors du jeu
     * @return String avec ce qui va etre affiche lors du jeu
     * @see Showable
     */
    @Override
    public String show() {
        return "<>";
    }
    
    
}
