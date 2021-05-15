
package kartel;

import java.util.ArrayList;

/**
 * Cette classe modelise une liste de Gangs et les méthodes necessaires
 * pour sa manipulation, ainsi qu’il est possible d’établir s’il peut avoir
 * des Joueurs égales dans la liste
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Gang
 */
public class LesGangs {
    
    /**Liste de Gangs*/
    private ArrayList<Gang> gangs;
    
    
    
    
    /**
     * Initialisation de las liste de Gagsters 
     */
    public LesGangs(){
        this.gangs = new ArrayList<>();
    }
    


     
    
    /**
     * Ajoute un Gang a la liste s'il n'esxiste dejà
     * @param g Gang à ajouter
     * @return boolean true si le Gang a été ajoute dans la liste
     */
    public boolean addGang(Gang g){
        if(!gangRepete(g)){
            this.gangs.add(g);
            return true;
        }
        
        return false;
    }
    
    /**
     * Ajoute une liste de Gangs 
     * eoute ceux qui ne sont pas dejà dans la liste
     * @param gs LesGans liste de gangs à ajouter
     * @return int avec la quantité de gangs ajoutés
     */
    public int addGangs(LesGangs gs){
        int c = 0;

        for(Gang g: gs.getGangs()){
            if(addGang(g))
                c++;
        }
        return c;
    }
    
    /**
     * Renvoie un gang par son indice dans la liste
     * si cet index existe dans la liste
     * @param index indice du gang dans la liste
     * @return Gang trouve ou null dans le cas que l'index n'esxiste pas
     */
    public Gang getGang(int index){

        if(index > 0 && index < getNbGangs())
            return this.gangs.get(index);
        return null;
    }
    
    /**
     * Renvoi le gang da la lista avec le pseudo abb
     * @param abb pseudo du Gang a renvoiyer
     * @return Gang trouve ou null
     */
    public Gang getGang(String abb){

        Gang g = null;
        int c = 0;
        while(g == null || c<getNbGangs()){
            if(abb.equals( getGang(c).getAbb()))
                g = getGang(c);
            c++;
        }
        
        return g;
    }
    
    /**
     * Renvoie la quantité de gangs dans la liste
     * @return int avec la quantité de gangs dans la liste
     */
    public int getNbGangs(){
        return this.gangs.size();
    }
    
    /**
     * Verifie si le Gang g existe dans la liste
     * @param g Gang a verifier
     * @return true si le gang existe dans la liste
     */    
    private boolean gangRepete(Gang g){
        boolean exist = false;
        int c = 0;
        while(!exist && c<getNbGangs()){
            exist = g.equals(getGang(c));
            c++;
        }  
        return exist;
    }
    
    /**
     * Réinitialise tous le Gangs dans la liste
     * @see Reutilisable
     * @see Gang#reset() 
     */
    public void resetGangs(){
        for(Gang g: getGangs())
            g.reset();
    }
    
        /**
     * Cree une nouevelle liste avec tous les GangsMembres de chaque gang
     * la taille par defaut de la lista est la cantite de Gaangs multiplie par 6
     * car il est panse pour optimiser le jeu par defaut
     * @return ArrayList de GangMember avec tous les gangs membres
     */
    public ArrayList<GangMember> getGangsMembers(){
        ArrayList<GangMember> gms = new ArrayList(this.gangs.size() * 6);
        for(Gang gm: this.gangs)
            gms.addAll(gm.getGangMembres());
        //System.out.println(gms.get(0));
        return gms;
    }


    
    /**
     * Renvoie la liste de gangs
     * @return ArrayList avec tous le gangs
     */
    public ArrayList<Gang> getGangs() {
        return gangs;
    }
    

    /**
     * Renvoie le texte decriptif de la liste de Gangs
     * @return String avec la decription de la liste de Gangs
     */    
    @Override
    public String toString(){
        String s="Gangs:";
        for(Gang g:this.gangs)
            s+="\n\t" + g.toString();
            
        return s;
    }
    
}
