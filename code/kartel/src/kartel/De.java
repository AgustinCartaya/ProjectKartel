
package kartel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe qui modélise  le De
 * Un De est comporte par des faces avec des valeurs entiers
 * le De peut montrer seulement une face à la fois
 * la face a montré est choisie aléatoirement parmi toutes ses faces
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 */

public class De {
    
    /**Array d'entiers qui contiendra les faces du De*/
    private int []faces;
    
    /**Entier contenant la face courrant du De*/
    private int indexFaceCourante;

    /**
     * Initialisation du De avec les faces passe par paramètre
     * la face par defaut du De est l'entier de la position zéro
     * des faces passe par paramètre
     * @param faces Array d'entiers pour etablir les faces du De
     */
    public De(int [] faces) {
        this.faces = faces;
        this.indexFaceCourante = 0;
    }
    
    /**
     * Initialisations Du de avec six faces par defaut
     * (2,2,3,3,4,4) faces prédéfinis par le jeu Kartel
     */
    public De(){
        this(new int[]{2,2,3,3,4,4});
    }

    /**
     * renvoie une face du dé choisie aléatoirement
     * entre toutes les possibilités possibles
     * @return int face obtenue
     */
    public int lancer(){
        this.indexFaceCourante = (int) (Math.random() * faces.length);
        return this.faces[ this.indexFaceCourante ];
    }
    
    /**
     * Renvoie le nombre de faces du DE
     * @return int avec le nombre de faces du De
     */
    public int getNbFaces(){
        return this.faces.length;
    }
    
    /**
     * Reduit les faces du De a une taille plus petite
     * @param taille nouvelle taille du De
     * @return true si le de à été reduit
     */
    public boolean reduirDe(int taille){
        if(taille>0 && taille <=this.faces.length){
            this.faces = Arrays.copyOf(this.faces, taille);
            return true;
        }
        return false;
    }

    /**
     * Renvoie la valeur de la face courrante
     * @return int avec valeur de la face courrante
     */
    public int getFaceCourante() {
        return this.faces[ this.indexFaceCourante ];
    }

    /**
     * Renvoie l'indice de la face courrante
     * @return int avec l'indice de la face courrante
     */
    public int getIndexFaceCourante() {
        return indexFaceCourante;
    }

    /**
     * Établi l'indice de la face courrante
     * s'il existe entre les indices possibles
     * @param indexFaceCourante int avec l'indice à etablir
     */
    public void setIndexFaceCourante(int indexFaceCourante) {
        if (indexFaceCourante>0 && indexFaceCourante<this.faces.length)
            this.indexFaceCourante = indexFaceCourante;
    }

    /**
     * Renvoie le texte decriptif du De
     * @return String avec la decription du De
     */
    @Override
    public String toString() {
        String s = "De("+ faces.length + ") = ";
        for(int i: faces)
            s+=i+", ";
        return s;
    }
}
