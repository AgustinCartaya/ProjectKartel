/**
 * Classe qui modélise les joueurs
 * 
 * interfaces implémentées:
 * Comparable
 */
package kartel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Une implémentation de cette classe crée un objet de type
 * Joueur qui modélise les joueurs du jeu,
 * ces joueurs ont un nom, et une liste avec les recompenses obtenues
 * 
 * un joueur peut faire appel a une intelligence lors du jeu s'il le desire,
 * en paremetrisant le niveau de l'intellegence et le volument de celle-ci
 *  
 * si le joueur est automatique l'intelligence prend tout le control
 * du joueur lors du jeu
 *
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Recompense
 * @see KartelIntelligence
 * @see Comparable
 * @see Reutilisable
 * @see Showable
 */
public class Joueur implements Comparable, Reutilisable, Showable{


    private String nom;
    
    /**récompenses du joueur*/
    private ArrayList<Recompense> recompenses;
    
    /**quantité de jeux gagnes*/
    private int jeuxGagnes;
    
    
    /**Parametres pour l'intelligence
    /**
     * si true le joueur est totalment
     * controle pour l'intelligence
     */
    private boolean automatique;
    
    /**
     * Niveau d'intelligence de cette joueur
     * 0: Intelligence Aleatoire
     * 1: Intelligence normale
     * 2: Intelligence Avance
     */
    private int iNiveau;
    
    /**
     * Volumen d'intelligence
     * 0: mute
     * 1: seulement les recomendations
     * 2: recomendatiosn et calculs
     */
    private int iVolumen;
    
    
    
    
    
    /**
     * Initialise le joueur avec un nom donné par paramètre
     * initialiser la liste de recompenses vide et les jeux gange a zero
     * 
     * le joeurs sont pas automatiques par defaut
     * et ils sont une intelligence zéro (minimal)
     * qui est par defaut en mute
     * 
     * @param nom String avec le nom du joueur
     * @see KartelIntelligence#getNi() 
     * @see KartelIntelligence#getVolumen() 
     */
    public Joueur(String nom) {
        this.nom = nom;
        recompenses = new ArrayList<Recompense>();
        jeuxGagnes=0;
        this.automatique = false;
        this.iNiveau = 0;
        this.iVolumen = 0;
    }
    
    /**
     * Incremente les jeux ganges
     */
    public void addJeuGange() {
        this.jeuxGagnes++;
    }
    
    /**
     * Ajout une recompense à la liste
     * @param recompense Recompense à ajoueter
     * @see Recompense
     */
    public void addRecompense( Recompense recompense){
        recompenses.add(recompense);
    }
    
    /**
     * Renvoie la somme de tous les recompenses
     * @return int avec la somme total de recompenses
     * @see Recompense#getRecompense() 
     */
    public int getScore(){
        int c=0;
        for(Recompense r:recompenses )
            c+=r.getRecompense();
        return c;
    }
    
    /**
     * Renvoie la liste de recompenses
     * @return ArrayList de Recompenses
     * @see Recompense
     */
    public ArrayList<Recompense> getRecompenses() {
        return recompenses;
    }
    
    /**
     * Renvoie le nom du joueur
     * @return String avec le nom du joueur
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Renvoie les jeux gangees par le joueur
     * @return int avec les jeux gangees par le joueur
     */
    public int getJeuxGagnes() {
        return jeuxGagnes;
    }
    
    /**
     * Renvoie true contrôlé par l'intelligence et false sinon 
     * @return true si le joueur est contrôlé par l'intelligence
     */
    public boolean isAutomatique() {
        return automatique;
    }

    /**
     * Etablit si le joueur va etre controle par l'intelligence
     * @param automatique true pour etre controle par l'intelligence
     * @see KartelIntelligence
     */
    public void setAutomatique(boolean automatique) {
        this.automatique = automatique;
    }

    /**
     * Renvoie le niveau d'intelligence qui a le joueur
     * @return int avec le niveu
     * @see KartelIntelligence#getNi() 
     */
    public int getiNiveau() {
        return iNiveau;
    }

    /**
     * Etablit le niveau d'intelligence qui veut le joueur
     * @param iNiveau le niveau d'intelligence voulu par le joueur
     * @see KartelIntelligence#setNi(int) 
     */
    public void setiNiveau(int iNiveau) {
        this.iNiveau = iNiveau;
    }

    /**
     * Renvoie le volumen d'intelligence qui a le joueur
     * @return int avec le volumen de l'intelligence
     */
    public int getiVolumen() {
        return iVolumen;
    }

    /**
     * Etablit le volumen d'intelligence qui veut le joueur
     * @param iVolumen le volumen d'intelligence voulu par le joueur
     */
    public void setiVolumen(int iVolumen) {
        this.iVolumen = iVolumen;
    }



    /**
     * Renvoie le texte decriptif du joueur
     * @return String avec la decription du joueur
     */
    @Override
    public String toString() {
        return nom + "(Sc: "+getScore()+")";
    }

    /**
     * compare le score de cette joueur avec celui d'un autre
     * utilise pour trier les listes de joueurs et trouve le(s)gagnats
     * @param o Joueur a comparer
     * @return entier avec la subtraction 
     */
    @Override
    public int compareTo(Object o) {
        Joueur j2 = (Joueur) o;
        return j2.getScore() - this.getScore();
    }

    /**
     * méthode pour réinitialiser le Joueur après d'un jeu
     * @see Reutilisable
     */
    @Override
    public void reset() {
        this.recompenses.clear();
    }

    /**
     * Renvoie le texte qui va représenter le joueur lors du jeu
     * @return String avec ce qui va etre affiche lors du jeu
     * @see Showable
     * @see Recompense#show()
     */
    @Override
    public String show() {
        String s=nom + "("+getScore()+") : ";
        for(Recompense r: this.recompenses){
            s+=r.show()+" - ";
        }
        return s;
    }

    
    /**
     * Chnge du hashCode tenant en compte le nom du joueur
     * @return int avec le hashCode 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    /**
     * Renvoie true si deux joueurs son les memes,
     * ou ils ont le meme nom
     * @param obj Gang à comparer
     * @return true si les deux Gangs ont le meme pseudo (abb)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Joueur other = (Joueur) obj;
        if (!this.nom.equals(other.nom)) {
            return false;
        }
        return true;
    }
    
    

}
