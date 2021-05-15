
package kartel;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Exception;
/**
 * Classe que modelisse une intelligence por jouer ou donner au
 * joueur courrant le "meilleur" coup pour à jouer tenant en compte le
 * resulat du de et d'autres parametres variants selon le type d'intelligence:
 * <br>
 * Intelligence niveau 0 (ni = 0):
 *      Niveau d'intelligence minimal, donne des coups alleatoires sans tenir en compte
 *      les Jetons sur le plateau ou l'escore actuel, 
 *      
 *      Max 12% de possibilites de gagner en implementat cette intelligence,
 *      par rapport aux autres intelligences (1,2)
 *      Moyenne 8%
 * <br>
 * Intelligence niveau 1 (ni = 1):
 *      Niveau d'intelligence moyenne, etude tous les coups possible lors du jeu et fait une liste
 *      triée l'ordre descendant avec les meilleur coups (ceux que donne le plus de points au joueur
 *      actuel par rapport à son etat) a fin que le joueur actuel augmente de score.
 *      choisie entre le meilleur coup et de rester avec le score
 *      actuel (coup = 0, pas bouger) si aucun coup de la liste n'est pas favorable.
 *      indique aussi ou se trouve les Gangs Membres et son type
 *      
 *      Max 51% de possibilites de gagner en implementat cette intelligence,
 *      par rapport aux autres intelligences (0,2)
 *      Moyenne 42%
 * <br>
 * Intelligence niveau 2 (ni = 2): En realité je sais pas comme elle fait pour gagner!
 *      Niveau d'intelligence un peu plus avance, etude tous les coups possible lors du jeu du jueur actuel,
 *      et ceux du joueur avec le meilleur score (si le joueur actuel est celui qui a le meilleur score
 *      le deuxieme meilleur score prend sa place) et fait une liste trie d'ordre descendant avec les meilleur coups
 *      (ceux que font avoir la meilleur difference de score entre les deux joeur) et comme ça essaiyer de tenir le joueur
 *      actuel dans la première position choisie entre le meilleur coup et de rester avec le score
 *      actuel (coup = 0, pas bouger) si aucun coup de la liste n'est pas favorable.
 *      indique aussi ou se trouve les Gangs Membres et son type
 *      
 *      Max 57% de possibilites de gagner en implementat cette intelligence,
 *      par rapport aux autres intelligences (0,1)
 *      Moyenne 50%
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see LesJoueurs
 * @see Joueur
 * @see Plateau
 * @see De
 * @see Detective
 * @see Prison
 */
public class KartelIntelligence {
    
    /**
     * Niveau d'intelligence
     * 0: Intelligence Aleatoire
     * 1: Intelligence normale
     * 2: Intelligence Avance
     */
    private int ni;
    
    /**
     * Volumen d'intelligence
     * 0: mute, seulement return le resultat
     * 1: seulement les recomendations et le resultat
     * 2: recomendatiosn, calculs et resultat
     */
    private int volumen;
    
    
    /**
     * Initialisation de l'intelligence avec un niveau
     * par defaut le volumen de l'intelligence est 2
     * @param ni int avec le niveau d'intelligence
     */
    public KartelIntelligence(int ni){
        this.ni = ni;
        volumen = 2;
    }

    /**
     * Fait l'appel aux autres méthodes en fonction du niveau d'intelligence choisie
     * et renvoit le resultat donné par ce méthode
     * @param de De actuel du jeu
     * @param plateau Plateau du jeu
     * @param joueurs LesJoueurs Liste de joueurs
     * @return int avec le coup obtenu selon le niveau d'intelligence
     * @see De
     * @see Plateau
     * @see LesJoueurs
     * @see KartelIntelligence#calculsIntelligence0(int) 
     * @see KartelIntelligence#calculsIntelligence1(int, kartel.Plateau, kartel.Joueur) 
     * @see KartelIntelligence#calculsIntelligence2(int, kartel.Plateau, kartel.LesJoueurs)
     * @see KartelIntelligence#calculsIntelligence2p1(int, kartel.Plateau, kartel.LesJoueurs) 
     * @see KartelIntelligence#hablar(java.lang.String, int) 
     */
    public int getCoup(De de, Plateau plateau,  LesJoueurs joueurs){
        int coup = 0;
        hablar("------------------ Kartel Intelligence ("+this.ni+") ------------------",2);
        switch(this.ni){
            case 0:
                coup = calculsIntelligence0(de.getFaceCourante());
                break;
            case 1:
                coup = calculsIntelligence1(de.getFaceCourante(), plateau, joueurs.getJoueurActuel());
                break;
            case 2:
                coup = calculsIntelligence2(de.getFaceCourante(), plateau, joueurs);

                break;
            case 3:
                coup = calculsIntelligence2p1(de.getFaceCourante(), plateau, joueurs);
                break;
        }
        
        hablar("------------------ ********************* ------------------",2);
        return coup;
    }

    /**
     * Renvoie le resultat de l'intelligence niveau 0
     * qui est une chiffre aleatoire entre 0 et le resultat du De
     * @param de resultat actuel du De
     * @return int avec le coup calculé
     */
    private int calculsIntelligence0(int de){
        int calc = (int)(Math.random() * (de+1));
        hablar("Recomendation: "+ calc,1);
        return calc;
    }
    
    /**
     * Renvoie le resultat de l'intelligence niveau 1
     * etude toutes les score possible avec les differents
     * coups permis et renvoie le coup que donne le score max
     * 
     * @param de resultat actuel du De
     * @param plateau Plateau du jeu
     * @param joueur Joueur actuel
     * @return int avec le coup calculé
     * 
     * @see KartelIntelligence#getCoupsList(int, kartel.Plateau, kartel.Joueur)
     * @see KartelIntelligence#hablar(java.lang.String, int)
     * @see Detective#getPosition() 
     * @see Detective#getMax()  
     */
    private int calculsIntelligence1(int de, Plateau plateau, Joueur joueur){
        
        /**valeurs necessaires pour se deplacer entre le Jetons du plateau*/
        int detPos = plateau.getDetective().getPosition();
        int maxPlateau = plateau.getJetons().size();
     
        /**Obtention de la liste de coups*/
        ArrayList<Coup> coups = getCoupsList(de, plateau, joueur);
        
        /**Trie de la liste*/
        Collections.sort(coups);
        
        /**Impresion de resulatats de la liste triée*/
        if(this.volumen>1){
        hablar("Meilleurs coup:",2);
        for(Coup c: coups)
            hablar("\t" + c.toString(),2);
        }
        
        /**Recomendation*/
        String s = "Recomendation(1): ";
        if(coups.get(0).getValeur() < joueur.getScore()){
            //calc = 0;
            s+="Ne jeu pas aucun coup est convenient pour vous...";
        }else{
            GangMember gm = plateau.getJetons().get(fauxAvance(detPos, coups.get(0).getCoup() - 1,maxPlateau));
            s+= "Tapez " + coups.get(0).getCoup() + " et capture le "
                + gm.getType() + " " + gm.show() + " du Gang " + gm.getGang().getNom()
                + " pour augmenter votre score à " + coups.get(0).getValeur();
            
            //calc = coups.get(0).getCoup();
                    
        }
        hablar(s,1);
               
        return coups.get(0).getCoup();
    }
    
    /**
     * Renvoie le resultat de l'intelligence niveau 1
     * etude toutes les difference de score possible entre le joueur actuel
     * et le joueur avec le score max (voir la description de la classe)
     * renvoi le coup que fait la plus cande difference
     * 
     * @param de resultat actuel du De
     * @param plateau Plateau du jeu
     * @param joueurs Liste de joueurs contenenat les deux joueurs neccesaires
     * @return int avec le coup calculé
     * 
     * @see KartelIntelligence#getCoupsList(int, kartel.Plateau, kartel.Joueur)
     * @see KartelIntelligence#hablar(java.lang.String, int)
     * @see Detective#getPosition() 
     * @see Detective#getMax() 
     * @see LesJoueurs#getListResultats() 
     * @see LesJoueurs#getJoueurActuel() 
     */
    private int calculsIntelligence2(int de, Plateau plateau, LesJoueurs joueurs){
        
        /**Liste de joueurs triee par leur score*/
        ArrayList<Joueur> resultats = joueurs.getListResultats();
        
        /**dans le cas que le jeu a un seul jeueur*/
        if(resultats.size()>1){
            
            /**liste qui stockera les coups*/
            ArrayList<Coup> coups = new ArrayList<>();
            
            /**valeurs necessaires pour se deplacer entre le Jetons du plateau*/
            int detPos = plateau.getDetective().getPosition();
            int maxPlateau = plateau.getJetons().size();
            
            /**Joueurs à étudier*/
            Joueur joueurVs;
            Joueur joueurYop = joueurs.getJoueurActuel();
            
            /**change de joueur si le joueur actuel a la mailleur score*/
            if(resultats.get(0)!=joueurs.getJoueurActuel())
                joueurVs = resultats.get(0);
            else
                 joueurVs = resultats.get(1);
        
            hablar("Joueur refference: "+ joueurVs.getNom(),2);
            
            /**différence de score actuelle*/
            int maxDifference = joueurYop.getScore() - joueurVs.getScore();
            
            hablar("Differece de score: " + maxDifference,2);
            
            /**obtention des scores des joueurs selon tous les possibles coups*/
            ArrayList<Coup> coupsYop = getCoupsList( de, plateau, joueurYop);
            ArrayList<Coup> coupsVs = getCoupsList( de, plateau, joueurVs);
            
            /**Obtention de coups en tenant en compte les Bosses pour etablir les differences*/
            for (int i = 0; i < coupsYop.size(); i++)
                coups.add( new Coup( i+1, coupsYop.get(i).getValeur() - coupsVs.get(i).getValeur()));
            
            
            /**Obtention de coups sans tenir en compte les Bosses pour etablir les differences*/
           for (Coup p: coupsYop)
                coups.add( new Coup( p.getCoup(), p.getValeur() - joueurVs.getScore()));
           
            /**Trie de la liste de coups*/
            Collections.sort(coups);
            
            
            /**Effacer les coups doubles si le joueur demmande de les montrer*/
            if(this.volumen>1){
                ArrayList<Coup> montrer = new ArrayList<>();
                for(Coup c: coups){
                    boolean exist = false;
                    for (int i = 0; i < montrer.size(); i++){ 
                        if(c.equals( montrer.get(i)))
                            exist = true;
                    }
                        if(!exist)
                            montrer.add(c);
                }

                hablar("Meilleurs coup2:",2);
                for(Coup c: montrer)
                    hablar("\t" + c.toString(),2);
            }
            
            /**Recomendation*/
            String s = "Recomendation(2): ";
            if(coups.get(0).getValeur() == maxDifference){
                //calc = 0;
                s+="Ne jeu pas aucun coup est convenient pour vous...";
            }else{
                GangMember gm = plateau.getJetons().get(fauxAvance(detPos, coups.get(0).getCoup() - 1,maxPlateau));
                s+= "Tapez " + coups.get(0).getCoup() + " et capture le "
                    + gm.getType() + " " + gm.show() + " du Gang " + gm.getGang().getNom()
                    + " reduir la difference de score à " + coups.get(0).getValeur();

                //calc = coups.get(0).getCoup();

            }
            hablar(s,1);
            return coups.get(0).getCoup();
                
        }
        else
            return calculsIntelligence1(de, plateau, joueurs.getJoueurActuel());
        
        
    }
    
    /**
     * Cette intelligence devrais etre plus intelligente mais par rapport a l'intelligence 
     * 1 et 2 ne l'est pas ça doit etre por la manier de jouer
     * @param de
     * @param plateau
     * @param joueurs
     * @return 
     */
    private int calculsIntelligence2p1(int de, Plateau plateau, LesJoueurs joueurs){
        
        /**Liste de joueurs triee par leur score*/
        ArrayList<Joueur> resultats = joueurs.getListResultats();
        
        /**dans le cas que le jeu a un seul jeueur*/
        if(resultats.size()>1){
          
            /**liste qui stockera les coups*/
            ArrayList<Coup> coups = new ArrayList<>();
            
            /**valeurs necessaires pour se deplacer entre le Jetons du plateau*/
            int detPos = plateau.getDetective().getPosition();
            int maxPlateau = plateau.getJetons().size();
            
            /**Joueurs à étudier*/
            Joueur joueurVs;
            Joueur joueurYop = joueurs.getJoueurActuel();
            
            /**change de joueur si le joueur actuel a la mailleur score*/
            if(resultats.get(0)!=joueurs.getJoueurActuel())
                joueurVs = resultats.get(0);
            else
                 joueurVs = resultats.get(1);
        
            hablar("Joueur refference: "+ joueurVs.getNom(),2);
            
            /**différence de score actuelle*/
            int maxDifference = joueurYop.getScore() - joueurVs.getScore();
            
            hablar("Differece de score: " + maxDifference,2);
            
            
            /**Obtention de coups en tenant en compte les Bosses pour etablir les differences*/
            int term = -1;
            for (int i = 1; i <= de; i++){
                int monScore = getScoreByPas(i, joueurYop, plateau);
                if( plateau.getJetons().get(fauxAvance(detPos, i-1, maxPlateau)).getType().equals(Boss.TYPE)){

                        
                        int scoreMax = getScoreByPas(i, joueurVs, plateau);

                        for(Joueur j: joueurs.getJoueurs()){
                            if(!j.equals(joueurYop) && !j.equals(joueurVs) && getScoreByPas(i, j, plateau)>scoreMax){
                                scoreMax = getScoreByPas(i, j, plateau);

                            }
                        }
                        if(plateau.getPrison().getMaxPrisoniers()-1 == plateau.getPrison().getNbBosses())
                          if(monScore - scoreMax>=0)
                            term = i;
                          else
                                if(i==de)
                                        term = 0;
                                else
                                    term = i+1;
                          
                        
                        coups.add( new Coup( i, monScore - scoreMax));

                }else
                    coups.add( new Coup( i, monScore - joueurVs.getScore()));

            }
            
            /**Trie de la liste de coups*/
            Collections.sort(coups);
             
            /**Effacer les coups doubles si le joueur demmande de les montrer*/
            if(this.volumen>1){
                ArrayList<Coup> montrer = new ArrayList<>();
                for(Coup c: coups){
                    boolean exist = false;
                    for (int i = 0; i < montrer.size(); i++){ 
                        if(c.equals( montrer.get(i)))
                            exist = true;
                    }
                        if(!exist)
                            montrer.add(c);
                }

                hablar("Meilleurs coup2:",2);
                for(Coup c: montrer)
                    hablar("\t" + c.toString(),2);
            }
            
            /**Recomendation*/
            String s = "Recomendation(2): ";
            if(term !=-1){
                hablar(s + "Il fait sauver la partie!!!",1);
                return term;
            }
            else if(coups.get(0).getValeur() == maxDifference){
                //calc = 0;
                s+="Ne jeu pas aucun coup est convenient pour vous...";
            }else{
                GangMember gm = plateau.getJetons().get(fauxAvance(detPos, coups.get(0).getCoup() - 1,maxPlateau));
                s+= "Tapez " + coups.get(0).getCoup() + " et capture le "
                    + gm.getType() + " " + gm.show() + " du Gang " + gm.getGang().getNom()
                    + " reduir la difference de score à " + coups.get(0).getValeur();

                //calc = coups.get(0).getCoup();

            }
            hablar(s,1);
            return coups.get(0).getCoup();
                
        }
        else
            return calculsIntelligence1(de, plateau, joueurs.getJoueurActuel());
        
        
    }

    
    private int getScoreByPas(int pas, Joueur joueur, Plateau plateau){

        /**valeurs necessaires pour se deplacer entre le Jetons du plateau*/
        int detPos = plateau.getDetective().getPosition();
        int maxPlateau = plateau.getJetons().size();

            GangMember gm = plateau.getJetons().get(fauxAvance(detPos, pas-1, maxPlateau ) );
            /**variable que enregistre le score selon le coup i*/
            int score = 0;
            switch(gm.getType()){
                case Boss.TYPE:
                    for(Recompense r: joueur.getRecompenses())
                        score += (r.getGang().getBoss().equals(gm))?r.getValeurBossPrisonnier():r.getRecompense();
                    hablar("Boss at : "+(pas) + " avec lui votre score sera: " + score,2);                    
                    break;
                    
                case Gangster.TYPE:
                    score = joueur.getScore() + ((Recompense)gm).getRecompense();
                    hablar("Ganster At: "+(pas)+ " avec lui votre score sera: " + score,2);
                    break;
                    
                case PotDeVin.TYPE:
                     score = joueur.getScore() + ((Recompense)gm).getRecompense();
                    hablar("pot de vin at: "+(pas) + " avec lui votre score sera: " + score,2);
                    break;
                    
            }
            return score;
    }
    
    /**
     * Renvoie une de Coups avec tous les possibles scores en fonction
     * des differents coups possibles
     * 
     * @param de resultat actuel du De
     * @param plateau Plateau du jeu
     * @param joueur Joueur actuel
     * 
     * @return ArrayList de Coups avec tous les coups possibles
     * @see Coup
     */
    private ArrayList<Coup> getCoupsList(int de, Plateau plateau, Joueur joueur){
        ArrayList<Coup> coups = new ArrayList<>();

        /**valeurs necessaires pour se deplacer entre le Jetons du plateau*/
        int detPos = plateau.getDetective().getPosition();
        int maxPlateau = plateau.getJetons().size();
        
        /**Etudes de scores en function de differents coups*/
        for (int i = 0; i <de; i++) {
            GangMember gm = plateau.getJetons().get(fauxAvance(detPos, i, maxPlateau ) );
            /**variable que enregistre le score selon le coup i*/
            int valeur = 0;
            switch(gm.getType()){
                case Boss.TYPE:
                    valeur = 0;
                    for(Recompense r: joueur.getRecompenses())
                        valeur += (r.getGang().getBoss().equals(gm))?r.getValeurBossPrisonnier():r.getRecompense();
                    hablar("Boss at : "+(i+1) + " avec lui votre score sera: " + valeur,3);                    
                    break;
                    
                case Gangster.TYPE:
                    valeur = joueur.getScore() + ((Recompense)gm).getRecompense();
                    hablar("Ganster At: "+(i+1)+ " avec lui votre score sera: " + valeur,3);
                    break;
                    
                case PotDeVin.TYPE:
                     valeur = joueur.getScore() + ((Recompense)gm).getRecompense();
                    hablar("pot de vin at: "+(i+1) + " avec lui votre score sera: " + valeur,3);
                    break;
                    
            }
            coups.add( new Coup(i+1,valeur) );
      
        }
        return coups;
 
    }
    

    
    /**
     * Montre en console l'information si celle ci est autorise selon
     * le volimen de l'intelligence
     * @param frase String à montrer
     * @param volumen int avec le volumen de l'information
     * @see KartelIntelligence#getVolumen() 
     */
    private void hablar(String frase, int volumen){
        if(this.volumen >= volumen)
            System.out.println(frase);
    }
    
    /**
     * Utilise pour faire le role du detective entre les Jetons du plateau
     * @param pos int avec la psition actuel
     * @param pas int avec la pas a faire
     * @param max int avec la position max que peut être atteinte
     * @return 
     */
    private int fauxAvance(int pos, int pas, int max){
        pos += pas;
        if( pos >= max)
            pos %= max ;
        return pos;
    }
    
    /**
     * Renvoie le niveau d'intelligence
     * 
     * 0: Intelligence Aleatoire
     * 1: Intelligence normale
     * 2: Intelligence Avance
     * 
     * @return int avec la niveu d'intelligence
     */
    public int getNi() {
        return ni;
    }

    /**
     * Etablit le niveau d'intelligence
     * 
     * 0: Intelligence Aleatoire
     * 1: Intelligence normale
     * 2: Intelligence Avance
     * 
     * @param ni int avec le niveau d'intelligence voulu
     */
    public void setNi(int ni) {
        this.ni = ni;
    }
    
    /**
     * Renvoi le volumen de l'intelligence
     * 
     * 0: mute, seulement return le resultat
     * 1: seulement les recomendations et le resultat
     * 2: recomendatiosn, calculs et resultat
     * 
     * @return int avec le volumen de l'intelligence
     */
    public int getVolumen() {
        return volumen;
    }

    /**
     * Etablit le volumen de l'intelligence
     * 
     * 0: mute, seulement return le resultat
     * 1: seulement les recomendations et le resultat
     * 2: recomendatiosn, calculs et resultat
     * 
     * @param volumen int avec le volumen de l'intelligence voulu
     */
    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }
    
    /**
     * Classe que modelise les Coups
     * en coup est composse d;une cantite de pas
     * en une valeur par rapport à ces pas
     */
    private class Coup implements Comparable{

            /**pas du coup*/
            private final int coup;
            
            /**valeur par rapport aux pas*/
            private final int valeur;
            
            /**
             * Initialisation di coup avec une cantite de pas
             * et une valeur
             * @param coup int avec la cantite de pas
             * @param valeur int avec la valeur
             */
            public Coup(int coup, int valeur) {
                this.coup = coup;
                this.valeur = valeur;
            }

            /**
             * Obtention des pas
             * @return 
             */
            public int getCoup() {
                return coup;
            }

            /**
             * Obtention de la valeur
             * @return 
             */
            public int getValeur() {
                return valeur;
            }

            /**
             * Utilisé pour trier les coups
             * @param o coup a comparer
             * @return entier avec sa substracion des valeur de Coups
             */
            @Override
            public int compareTo(Object o) {
                Coup c = (Coup) o;
                return c.getValeur() - this.getValeur();
            }

            @Override
            public String toString() {
                return  "Coup: " + coup + "; Resultat: " + valeur;
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash = 59 * hash + this.coup;
                return hash;
            }

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
                final Coup other = (Coup) obj;
                if (this.coup != other.coup) {
                    return false;
                }
                return true;
            }

    }    
    
}
