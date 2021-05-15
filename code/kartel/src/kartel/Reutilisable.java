
package kartel;

/**
 * Interface qui etablit les méthodes nécessaires pour
 * les objets qui peuvent être utilisés plusieurs fois dans le jeu,
 * mais qui doivent reinitialiser quelques parametres
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 */
public interface Reutilisable {
    
    /**
     * Méthode que va réinitialiser les paramètres
     * nécessaires de l'objet qui implémente cette interface
     */
    void reset();
}
