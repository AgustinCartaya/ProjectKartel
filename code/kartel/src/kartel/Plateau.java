
package kartel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Implementacion de cette class cree un plateau qui contiendra les jetons, le detective et la
 * prison pour jouer au Kartel, le plateau a les methodes necessaires por deplacer le Detective,
 * eliminer les jetons et ajouter les boss a la prison,
 * 
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 */

public class Plateau implements Reutilisable, Showable{
    
    private LesGangs gangs;
    //Liste des GangMembers 
    private ArrayList <GangMember> jetons;
    
    //Détective
    private Detective detective;
    
    //Prison
    private Prison prison;
    
    
    // Constructeur
    /**
     * Initialisation du plateau avec la liste de Gangs qui seron dans la partie
     * et le nombre max de prisonnier permis par la prison, si la liste de gangs est vide
     * le melange et la creation de jetons ne se fait pas
     * @param gangs liste de gags por initialiser le plateau
     * @param maxPrisonniers int avec la quantité max de prisionnier acceptés
     */
    public Plateau(LesGangs gangs, int maxPrisonniers){

        //Création de la prison
        this.prison = new Prison(maxPrisonniers);
        this.jetons = new ArrayList <>();
        this.gangs = gangs;
        
        //creation de jetons
        if(gangs.getNbGangs()>0)
            createJetons();
        
        //Création du detective
        detective =  new Detective ();

        

    }
    
    /**
     * Initialisation du plateau avec la liste de Gangs qui seron dans la partie
     * et avec un max de 5 prisoniers
     * @param gangs liste de gags por initialiser le plateau
     */
    public Plateau(LesGangs gangs){
        this(gangs, 5);
    }

    /**
     * eface la liste de gangs actuel et Etabli les
     * nouveaux gangs poru jouer, apres cree la liste de jetons
     * @param gangs LesGangs Liste de gansg pour jouer
     * @see Plateau#createJetons() 
     */
    public void setGangs(LesGangs gangs){
        this.gangs.getGangs().clear();
        this.gangs.addGangs(gangs);
        createJetons();

    }
    
    /**
     * Remplisage de la liste de jetons avec tous les GangsMembres
     * pour apres la melanger de maniere aleatoire
     * les parametres du detective comment sa position initial
     * et la position max sont etabli 
     */
    private void createJetons(){
       this.jetons = this.gangs.getGangsMembers();
        //Mélange de la liste des jetons
        melanger(jetons);
        this.detective.setPosition(0);
        this.detective.setMax(this.jetons.size());

    }
    
    /**
     * Renvoie la quantite total de jetons su re tableau
     * @return int avec l quantite de jetons
     */
    public int getNbJetons(){
        return this.jetons.size();
    }
    //fonctions internes
    
    /**
     * Méthode qui fait avancer le détéctive en fonction du nombe de pas saisi,
     * supprime et renvoie le gangMember de la position où le détective s'est arrêté,
     * s'il est un boss, il entre en prison
     * @param pas cantité de pas a avancer
     * @return GangMember elimine de la liste de jetons
     */
    public GangMember next( int pas ){
        if(pas == 0)
            return null;
        //On fait avancer le détéctive :
        System.out.println("Posicion Aterior " + this.detective.getPosition());
        this.detective.avancer(pas);
        int pos = detective.getPosition();
	//Si le gangMember qui se situe à cette position est un boss: on le met en prison

        if(this.jetons.get(pos).getType().equals(Boss.TYPE))
            this.prison.addBoss((Boss)this.jetons.get(pos));

        this.detective.setMax(jetons.size()-1);
        
        return jetons.remove(pos);
    }

    /**
     * méthode qui mélange les jetons
     * @param list liste a melanger
     */
    public void melanger(List<GangMember> list) {
	final long seed = System.nanoTime();
	Collections.shuffle(list, new Random(seed));
    }
    
    
    /**
     * Renvoie la liste de jetosn sur le plateau
     * @return ArrayList de GangMembres avec tous le Jetons
     */
    public ArrayList<GangMember> getJetons() {
        return jetons;
    }

    /**
     * Renvoie le detective du plateau
     * @return Detective
     */
    public Detective getDetective() {
        return detective;
    }

    /**
     * Renvoie La prision du jeu
     * @return La prision jeu
     */
    public Prison getPrison() {
        return prison;
    }
    
    /**
     * Renvoie la liste de Gangs
     * @return LesGangs liste de Gangs
     */
    public LesGangs getgangs() {
        return gangs;
    }
    
    /**
     * Renvoie le texte decriptif du plateau
     * @return String avec la decription du plateau
     */
    @Override
    public String toString(){
       return "Plateau: Jetons Restants "+ getNbJetons() + " Position Detective: " +getDetective().getPosition();
    }
    
    /**
     * Renvoie le texte qui va représenter le Boss lors du plateau
     * @return String avec ce qui va etre affiche lors du plateau
     * @see Showable
     */
    @Override
    public String show(){
        String s = "";
        for (int i = 0; i <= this.jetons.size(); i++) {
            
            if(i==this.detective.getPosition())
                s+=this.detective.show() +" - ";
            if(i<this.jetons.size())
                s+= this.jetons.get(i).show()+" - ";
            
        }
        s += '\n' + this.prison.show();
        return s;
    }

    /**
     * méthode pour réinitialiser le plateau après d'un jeu,
     * les jetons sont cree de nouvaeu et la presion est réinitialiser
     * @see Plateau#createJetons() 
     * @see Prison#reset() 
     * @see Reutilisable
     * @see Boss#setPrisonnier(boolean) 
     */
    @Override
    public void reset() {
        createJetons();
        getPrison().reset();
    }


}
