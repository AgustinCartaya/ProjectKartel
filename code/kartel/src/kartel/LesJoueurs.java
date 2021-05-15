
package kartel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Cette classe modelise une liste de Joueurs et les méthodes necessaires
 * pour sa manipulation. la liste n'accepte pas répétés
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Joueur
 */
public class LesJoueurs {
    
    /**liste des joueurs*/
    private ArrayList<Joueur> joueurs;
    
    /**index du joueur actuel dans la liste*/
    private int indexJoueurActuel;


    
    
    /**
     * Initialisation de la liste de joueurs vide
     * et l'index du joueur actuel par defaut est -1
     */
    public LesJoueurs() {
        this.joueurs = new ArrayList<Joueur>();
        this.indexJoueurActuel = -1;
    }
    

    
    
    
    /**
     * passe au joueur suivant, revient au premie
     * si le précédent était le dernier
     */
    public void nextJoueur(){
        
        this.indexJoueurActuel++;

        if(this.indexJoueurActuel >= getNbJoueurs())
            this.indexJoueurActuel = 0;
    }
    
    /**
     * Renvoie une chaine de text avec une liste de positions
     * par example:
     *      1er lieu:
     *          Alex (5)
     *      2eme lieu
     *          Sam (3)
     *          Niko (3)
     *          ....
     * 
     * le nombre de positions est indiqué
     * @param ctt Cantité de positions à afficher si ctt est negative tous les resultats sont montrés 
     * @return String avec tous les positions 
     */
    public String afficherPositions(int ctt){
        ArrayList<Joueur> resultats =  getListResultats();
        String s="1er lieu:";
        int pos = 1, i = 0;
        if(ctt < 0)
            ctt = resultats.size();
        
        while (i < resultats.size() && pos<=ctt) {

                if(i>0 && resultats.get(i).getScore() != resultats.get(i-1).getScore()){
                    
                    pos++;
                    if(pos>ctt)
                        break;
                    
                    s += "\n"+pos +"ème lieu";
                }
                
                s+= "\n\t"+ resultats.get(i).toString();

                i++;
        }

         return s;
    } 
    
    /**
     * Renvoie une liste de joueurs tire par son scores
     * @return ArrayListe de joueurs trié
     */
    public ArrayList<Joueur> getListResultats(){
        ArrayList<Joueur> resultats = new ArrayList<>();

        for(Joueur j: this.joueurs)
            resultats.add(j);

          Collections.sort(resultats);
          return resultats;
      }
    
    /**
     * Ajout un joueur a la liste s'il n'est pas répété
     * @param j Joueur à ajoueter
     * @return troue si le joueur à été ajouté
     */
    public boolean addJoueur( Joueur j){

        if(!joueurRepete(j)){
            this.joueurs.add(j);
            if(getNbJoueurs() == 1)
                this.indexJoueurActuel = 0;
            return true;
        }
        
        return false;
    }
    
    /**
     * Renvoie le nombre de joueurs dans la liste
     * @return int avec le nombre de joueurs dans la liste
     */
    public int getNbJoueurs(){
        return this.joueurs.size();
    }
    
    /**
     * Renvoie un joueur par son indice dans la liste
     * si cet index existe dans la liste
     * @param index indice du Joueur dans la liste
     * @return Joueur trouve ou null dans le cas que l'index n'esxiste pas
     */
    public Joueur getJoueur(int index){
        if(index>=0 && index<getNbJoueurs())
            return this.joueurs.get(index);
        
        return null;
    }
    
    /**
     * Renvoi l'indice du joueur j dans la liste de joueurs
     * @param j joueur qui on cherche l'indice
     * @return int avec l'indice du joueur ou -1 si le joueur ne se trouve pas dans la liste
     */
    public int getIndexJoueur(Joueur j){
        return this.joueurs.indexOf(j);
    }
    
    /**
     * Supprime un joueur de la liste par son indice
     * @param index indice du joueur a supprimer
     * @return true si le joueur à été supprimé
     */
    public boolean remove(int index){
        if(index>=0 && index<getNbJoueurs()){
            this.joueurs.remove(index);
            return true;
        }
        if(getNbJoueurs() == 0)
            this.indexJoueurActuel = -1;
        return false;   
    }
    
    /**
     * Supprime un joueur de la liste par son nom
     * @param nom nom du joueur a supprimer
     * @return true si le joueur à été supprimé
     */
    public boolean remove(String nom){
        boolean trouve = false;
        int c = 0;
        while(!trouve && c<getNbJoueurs()) {
            if(getJoueur(c).getNom().equals(nom))
                trouve = remove(c);
        }
        return trouve;
    }
    
    /**
     * Verifie si le Joueur j existe dans la liste
     * @param j Joueur a vérifier
     * @return true si le Joueur existe dans la liste
     */   
    private boolean joueurRepete(Joueur j){
        boolean exist = false;
        int c = 0;
        while(!exist && c<getNbJoueurs()){
            exist = j.equals(getJoueur(c));
            c++;
        }  
        return exist;
    }
    
    
    /**
     * renvoi le joueur actuel
     * @return Joueur actuel
     */
    public Joueur getJoueurActuel(){
        if(this.indexJoueurActuel != -1 && getNbJoueurs() > this.indexJoueurActuel)
            return getJoueur(this.indexJoueurActuel);
        return null;
    }
    
    /**
     * Renvoie le premier gagnant 
     * utilisé seulement pour des test 
     * @return Joueur #1 dans la liste de gagnats
     * @deprecated 
     */
    public Joueur getJoueurGagnant(){
        return getListResultats().get(0);
    }
    
    /**
     * Renvoie le joueur avec le score max
     * non utilisé actuellement
     * @return non
     * @deprecated
     */
    public ArrayList<Joueur> getGagnant(){
        int posGagnant = 0;
        ArrayList<Joueur> gagnant = new ArrayList<Joueur> ();
        gagnant.add(this.joueurs.get(0));
        for (int i = 1; i < this.joueurs.size(); i++) 
            if(this.joueurs.get(i).getScore() == gagnant.get(0).getScore())
                gagnant.add(this.joueurs.get(i));
            else if(this.joueurs.get(i).getScore() > gagnant.get(0).getScore()){
                gagnant.clear();
                gagnant.add(this.joueurs.get(i));
            }
        return gagnant;
        
    }
    
    /**
     * Sipprime tous les joueurs de la liste
     */    
    public void removeAll(){
        this.joueurs.clear();
    }
    
    /**
     * Réinitialise tous le Joueurs de la liste
     * @see Reutilisable
     * @see Joueur#reset() 
     */
    public void resetJoueurs(){
        for(Joueur j: getJoueurs())
            j.reset();
    }
    
    /**
     * Renvoie un text contenant une liste avec le texte à montrer
     * de chaque joueur lors du jeu
     * @return String avec la liste de joueurs
     * @see Joueur#show() 
     * @see Showable
     */
    public String showJoueurs() {
        String s="";

        s = this.joueurs.stream().map((j) -> j.show()+"\n").reduce(s, String::concat);
        return s;
    }
    
    
        /**
     * Renvoie la liste de joueurs
     * @return ArrayList avec la liste de joueurs
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    /**
     * renvoie l'indice du joueur actuel
     * @return int avec l'indice du joueur actuel
     */
    public int getIndexJoueurActuel(){
        return this.indexJoueurActuel;
    }
    
    /**
     * etabli l'indice du joueur actuel
     * @param index avec l'indice du joueur actuel
     */
    public void setIndexJoueurActuel(int index){
        if(index>0 && index<getNbJoueurs())
            this.indexJoueurActuel = index;
    }

    
    /**
     * Renvoie le texte decriptif de la liste de Joueurs
     * @return String avec la decription de la liste de Gangs
     */  
    @Override
    public String toString(){
        String s="Joueurs: ";
        s = this.joueurs.stream().map((j) -> j.toString()+", ").reduce(s, String::concat);
        return s;
    }


}
