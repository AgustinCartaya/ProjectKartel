
package kartel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe qui modelisse les Gangs
 * un gang est compose par un Boss unique et d'autres membres
 * comment Gansters ou pots de vin
 * chaque Gang doit etre son nom et son pseudo (abb)
 * un Gang doit etre unique et reutilisable lors du jeu
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see GangMember
 * @see Boss
 * @see Recompense
 * @see Gangster
 * @see PotDeVin
 * @see Reutilisable
 */
public class Gang implements Reutilisable {
    
    /**nom et pseudo du Gang*/
    private String nom;
    private String abb;
                   
    
    /**Membres du gang*/
    private ArrayList<GangMember> gangMembers;
    
    /**
     * Initilaisation du Gang avec un nom et un pseudo
     * la creation du Boss est fait par defaut 
     * @param nom String contenant le nom du Gang
     * @param abb String contenant le pseudo du Gang
     * 
     * @see Boss
     */
    public Gang(String nom, String abb){

        this.nom = nom;
        this.abb = abb;
        this.gangMembers = new ArrayList<>(7);
        this.gangMembers.add(new Boss (this));
    }

    /**
     * Une appelle à cette méthode initialise
     * des Gangs membres par defaut
     * @see Recompense
     * @see Gangster
     * @see PotDeVin
     */
    public void initDefaultGangMembres(){
        
        gangMembers.add(new Gangster(this, 1));
        gangMembers.add(new Gangster(this, 2));
        gangMembers.add(new Gangster(this, 2));
        gangMembers.add(new Gangster(this, 3));
        
        gangMembers.add(new PotDeVin(this));
    }
        
    /**
     * Méthode utilise pour changer  l'état de liberté du Boss
     * @param etat boolean avec l'état de liberté du Boss
     * @see Boss#setPrisonnier(boolean) 
     */
    public void bossInPrision(boolean etat){
        getBoss().setPrisonnier(etat); 
    }
    
    /**
     * Renvoie l'état de liberté du Boss
     * @return boolean avec l'état de liberté du Boss
     * @see Boss#isPrisonnier() 
     */
    public boolean isBossInPrison(){
        return getBoss().isPrisonnier();
    }
    
    /**
     * Ajout un membre à la liste de membres du Gang
     * le membre à ajouter ne peut pas etre un Boss
     * @param g Gangster à ajouter
     * @see Gangster
     */
    public void addGangMember(GangMember g){
        if(!g.getType().equals(Boss.TYPE))
            this.gangMembers.add(g);
    }
    
    /**
     * Renvoie le Boss du Gang
     * @return Boss du Gang
     * @see Boss
     */
    public Boss getBoss() {
        //if(this.gangMembers.size()>0 && this.gangMembers.get(0).getType().equals(Boss.TYPE))
            return (Boss)this.gangMembers.get(0);
    }

    /**
     * Renvoie la liste de Gangsters du Gang
     * @return ArrayList avec tous les Gangsters
     * @see Gangster
     */
    public ArrayList<Gangster> getGangsters() {
        ArrayList<Gangster> gangsters = new ArrayList<>();
        for(GangMember g: this.gangMembers)
            if(g.getType().equals(Gangster.TYPE))
                gangsters.add((Gangster)g);
        return gangsters;
    }

    /**
     * Renvoie la liste de pots de vin du Gang
     * @return ArrayList avec tous les pots de vin
     * @see PotDeVin
     */
    public ArrayList<PotDeVin> getPotDeVin() {
        ArrayList<PotDeVin> pdv = new ArrayList<>();
        for(GangMember g: this.gangMembers)
            if(g.getType().equals(PotDeVin.TYPE))
                pdv.add((PotDeVin)g);
        return pdv;
    }

    /**
     * Revoit la liste de tous les mesmbres du Gang
     * @return ArrayList des membres du Gang
     * @see GangMember
     */
    public ArrayList<GangMember> getGangMembres(){
        return this.gangMembers;
    }

    /**
     * Renvoie le nom du Gang
     * @return String avec le nom du Gang
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le psuedo du Gang
     * @return String avec le psuedo du Gang
     */
    public String getAbb() {
        return abb;
    }
    
    /**
     * méthode pour réinitialiser le gang après d'un jeu
     * @see Reutilisable
     * @see Boss#setPrisonnier(boolean) 
     */
    @Override
    public void reset() {
        bossInPrision(false);
    }
    
    /**
     * Renvoie le texte decriptif du Gang
     * COntnat le nom du Gang, l'abrebiation et tous les membres
     * @return String avec la decription du Gang
     */    
    @Override
    public String toString(){
        String s =getNom() +"("+getAbb()+") {";
        
        s+="Boss: "+getBoss().show();
        s+="; Gangsters: ";
        for(Gangster g: getGangsters())
            s+=g.show()+", ";
        s+="Pots de vin: ";
        for(PotDeVin v: getPotDeVin())
            s+=v.show()+", ";
        s+='}';
        return s;
    }

    /**
     * Chnge du hashCode tenant en compte le pseudo du Gang
     * @return int avec le hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.abb);
        return hash;
    }

    /**
     * Renvoie true si deux Gangs son les memes,
     * ou ils ont le meme pseudo (abb)
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
        final Gang other = (Gang) obj;
        if (!Objects.equals(this.abb, other.abb)) {
            return false;
        }
        return true;
    }
}
