
package kartel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Classe principal du jeu Kartel cette classe contient le menu et la 
 * méthode jouer qui permet de initialiser le jeu,
 * 
 * cette classe permet l'interaction entre l'utilisateur et le jeu par la console
 * il est possible de crrer des joueurs, Gangs, modifier le De, regler l'intelligence des personages...
 * 
 * il est possible aussi jouer en mode test pour verifier les intlelligences avec la methode
 * modeStatistiques()
 * 
 * 
 * @author Manel Meftah
 * @author Agustin Cartaya
 * 
 * @see Gang
 * @see Plateau
 * @see De
 * @see LesJoueurs
 * @see KartelIntelligence
 */
public class Partie implements Reutilisable{
    
    /**Plateau du jeu*/
    private Plateau plateau;
    
    /**Liste avec les joueurs*/
    private LesJoueurs joueurs;
    
    /**De du jeu*/
    private De de;
    
    /**Intelligence du jeu*/
    private KartelIntelligence intelligence;
    
    /**true lors que le jeu fini la première fois pour activer l'option rejouer du menu*/
    private boolean rejouer = false;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new Partie();
    }
    
    
    /**
     * Creation d'une nouvelle partie avec:
     * une liste de joueurs vide
     * un plateau sans Gangs
     * une intelligence par defaut de 0
     * un de par dafaut
     * et l'initialisation du menu
     * @see LesJoueurs#LesJoueurs() 
     * @see Plateau#Plateau(kartel.LesGangs) 
     * @see KartelIntelligence#KartelIntelligence(int) 
     * @see De#De() 
     * 
     */
    public Partie(){
        
        this.joueurs = new LesJoueurs();
        this.plateau = new Plateau(new LesGangs(), 0);
        this.intelligence = new KartelIntelligence(0);
        this.de = new De();
        menu();
        //modeStatistiques(100);
    }
    
    /**
     * Menu du jeu contien les option qui l'utilisateur peut selectioner 
     * pour parametriser le jeu et commencer à jouer
     */
    public void menu(){
        int opc = 0;

        while(opc !=12){
            String menu =  "\n--------------------- Menu ---------------------\n"
                    +  ((this.rejouer)?"0: Rejouer avec le meme parametres\n":"")
                    +  "1: Creer Joueurs\n"
                    +  "2: Creer Gangs\n"
                    +  "3: Jouer\n"
                    +  "4: Modifier le De\n"
                    +  "5: afficher les paramètres actuels\n"
                    +  "6: Mode test\n"
                    +  "7: Modifier la prison\n"
                    +  "8: Suprimer joueur\n"
                    +  "9: Creer Gangs par defaut\n"
                    +  "10: Mode Vs\n"
                    +  "11: Statistiques\n"
                    +  "12: Sortir du jeu\n"
                    +  "---------------- Tapez une option ----------------\n";
            opc = Lire.i(menu,"SVP tapez une option valide",0,15);
            switch(opc){
                case 0:
                    reset();
                    jouer();
                    break;
                case 1:
                    createJoueurs();
                    break;
                case 2:
                    createGangs();
                    break;
                case 3:
                    jouer();
                    break;
                case 4:
                    deModification();
                    break;
                case 5:
                    afficerLesParametres();
                    break;
                case 6:
                    chargerDefaultsJoueurs();
                    chargerDefaultsGangs();
                    jouer();
                    break;
                case 7:
                    modifierPrison();
                    break;
                case 8:
                    suprimerJoueur();
                    break;
                case 9:
                    chargerDefaultsGangs();
                    break;
                case 10:
                    modeVs();
                    chargerDefaultsGangs();
                    jouer();
                    break;
                case 11:
                    modeStatistiques(100);
                    break;
                case 12:
                    System.out.println("Au-revoir");
                    break;
                default:
                    System.out.println("SVP tapez une option valide");
                    break;
            }
            
        }
    }
     
    
   
    /**
     * ajoute un nouveau joueur a la liste
     * avec le nom passé en paramètre et le reglage pour l'inteligance
     * qui va lui aider
     * @param nom String nom du joueur
     * @param automatique true si l'inteligence a tout le control du joueur
     * @param iNiveau int niveau de l'intellegencedu joueur
     * @param iVolimen int volumen de l'intelligence du joueur
     * 
     * @see Joueur#setAutomatique(boolean) 
     * @see Joueur#setNi(int) 
     * @see Joueur#setVolumen(int)
     */
    private boolean ajouterJoueur(String nom, boolean automatique, int iNiveau, int iVolimen){
        Joueur j = new Joueur(nom);
        j.setAutomatique(automatique);
        j.setiNiveau(iNiveau);
        j.setiVolumen(iVolimen);
       return this.joueurs.addJoueur( j);  
    }
    
     /**
     * Etabli des nouveaux gans dans le plateau
     * et regle les parametres de la prision aussi
     * @param gangs LesGangs a ajouter au plateau
     * @param maxP int avec la quantite max de prisonniers
     */
    private void setGangs(LesGangs gangs, int maxP){
       setGangs(gangs);
       this.plateau.getPrison().setMaxPrisonniers(maxP);
       this.plateau.getPrison().reset();
    }
    
    /**
     * crée le tableau avec la liste des gangs passée en paramètre
     * et un dé avec les faces passés par paramètre
     * @param gansters 
     */
    private void setGangs(LesGangs gangs){
       this.plateau.setGangs(gangs);
    }
    
 
  
        
    /**
     * Charge de joueurs automatiques pour faire de tests
     * joueuers avec tous le tipes d'intelligence sont chargé
     */
    private void chargerIntelligencesTest(){
        if(this.joueurs.getNbJoueurs()!= 0)
            this.joueurs.removeAll();
        
        System.out.println("Init test...");
        ajouterJoueur("I(0)", true, 0,0);
        ajouterJoueur("I(1)", true, 1,0);       
        ajouterJoueur("I(2)", true, 2,0);
        ajouterJoueur("I(3)", true, 3,0);
 
    }
    
    /**
     * Permet de charger jeueurs par defaut pour faire de tests
     */
    private void chargerDefaultsJoueurs(){
        
        System.out.println("Init...");
        if(this.joueurs.getNbJoueurs()!= 0)
            this.joueurs.removeAll();
        
        for (int i = 0; i < 3; i++) 
            ajouterJoueur("Joueur("+i+")", false,i,3);

       
    }
     
    /**
     * Charge les Gangs par defaut 
     * 7 Gangs sont chargé avec leur dafaults gangs membres
     * apres sont passe au plateau pour creer les jetons
     * si la cantité max de prisonniers n'est pas
     * déjà etabli elle est reglé à 5
     * 
     */
    private void chargerDefaultsGangs(){
                
        String []gangNoms = new String[]{"Verts","Blue","Rouge","Cyan","Noire","Gris","Magenta"};
        LesGangs gangs = new LesGangs();
        for (int i = 0; i < gangNoms.length; i++) {
            Gang g = new Gang(gangNoms[i],gangNoms[i].charAt(0)+"");
            g.initDefaultGangMembres();
            gangs.addGang( g);
        }
        int maxP =this.plateau.getPrison().getMaxPrisoniers();
        setGangs(gangs, (maxP==0)?5:maxP);   
    }
    
    
    
    /**
     * Initialise le mode statistiques por tester les intelligences
     * cette mode va charger les intelligences par defaut et va faire des jeux
     * pour voir quel est l'intelligence qui gagne le plus
     * @param ctt cantité de jeux à faire
     */
    public void modeStatistiques(int ctt){
        chargerIntelligencesTest();
        chargerDefaultsGangs();
        for (int i = 0; i < ctt; i++) {
            if(i!=0)
                reset();
            jouer();
        }
        
        ArrayList <Joueur>jou = new ArrayList <>();
        for(Joueur j:this.joueurs.getJoueurs())
            jou.add(j);
        
        Collections.sort(jou,new Comparator<Joueur>(){
                     @Override
                     public int compare(Joueur j1,Joueur j2){
                           
                           return j2.getJeuxGagnes() - j1.getJeuxGagnes();
                     }});
        for(Joueur j: jou)
            System.out.println(j.getNom()+": "+ j.getJeuxGagnes() + " = "
                                +(j.getJeuxGagnes() * 100 / ctt) + "%");
    }   

    /**
     * Mode du jeu utilisateur vs intelligence
     */
    private void modeVs(){
        if(this.joueurs.getNbJoueurs()!= 0)
            this.joueurs.removeAll();
        
        ajouterJoueur("Vous", false, 0,0);
        ajouterJoueur("Intelligence", true, 3,0);
    }
    
    
    
    /**
     * Methode pour permetre aux joueurs de 
     * regler les parametres de la prison
     */
    private void modifierPrison(){

        if(this.plateau.getgangs().getNbGangs() == 0){
            System.out.println("La prision ne peut pas etre modifie avant de creer les Gangs");
            return;
        }
        int maxActuel =  this.plateau.getgangs().getNbGangs();
        int maxP = Lire.i("Veuillez écrire le nombre de prisonniers acceptés doit etre entre [1 et " +maxActuel +"]",
                        "nombre non accepté écrivez un autre SVP", 1,
                        maxActuel);
        this.plateau.getPrison().setMaxPrisonniers(maxP);
    }
    
    /**
     * Permet de suprimer un joueur par l'utilisateur
     */
    private void suprimerJoueur(){
        boolean remov;
        String nom;
        System.out.println("\n--------------------- Liste de Joueurs ---------------------");
        System.out.println(this.joueurs.toString());
        System.out.println("--------------------- ****************** ---------------------");

        
        nom = Lire.S("SVP tepez le nom du joueur a suprimer, attention aux MAYUSCULES!:",
                "Nom non valide veuillez entrer un autre");
        remov = this.joueurs.remove(nom);
        
        if(remov)
            System.out.println(nom +" a bien été supprimé de la liste");
        else
            System.out.println( nom +" n'a pas été trouvé dans la liste");

    }
    
    /**
     * Permet à l'utilisateur de creer des nouveaux joueurs
     * avec un nom et lui propose de creer une intelligence pour
     * aider ou controler le joueur
     */
    public void createJoueurs(){
        char opc = 'o';
        char opci = 'n';
        
        System.out.println("Creation de joueurs");
        while (opc == 'o' || opc =='O'){
            
            String nom = Lire.S("SVP tapez le nom du joueur "+ (this.joueurs.getNbJoueurs()+1),
                                "Nom du joueur non valide veuillez entrer un autre");
            System.out.println("Voulez vous une intelligence pour cette joueur? "
                                + "\nOui:o, non:n");
            opci = Lire.c();
            if(opci == 'o' || opci =='O'){
                
                int iNiveau, iVolumen;
                
                iNiveau = Lire.i("SVP tapez le niveau d'intelligence entre 0 et 2",
                                       "Ce niveau d'intelligence n'est pas autorisé, veuillez entrer un autre entre 0 et 2",
                                       0,2);
                                    
                System.out.println("Voulez que l'intelligence controle cette joueur? "
                                + "\nOui:o, non:n");
                
                char opciC = Lire.c();
                if(opciC == 'o' || opciC =='O'){
                     ajouterJoueur( nom, true,iNiveau, 0);
                }
                else{

                    iVolumen = Lire.i("SVP tapez le volumen de l'intelligence valeur entre 0 et 2",
                                       "Ce niveau volumen n'est pas autorisé, veuillez entrer un autre entre 0 et 2",
                                       0,2);

                    ajouterJoueur( nom, false,iNiveau, iVolumen);
                }
            }
            else{
                ajouterJoueur( nom, false,0,0);
            }
            System.out.println("Il y a " + this.joueurs.getNbJoueurs() + " Joueurs");
            System.out.println("souhaitez-vous en ajouter un autre?"
                                + "\nOui:o, non:n");
            opc = Lire.c();
        }
    }
    
    /**
     * Creation de gangs par l'utilisateur
     */
    public void createGangs(){
        LesGangs gangs = new LesGangs();
        char opc = 'o';
        char opc2 = 'n';
        System.out.println("Creation de Gangs");
        while (opc == 'o' || opc =='O'){

            String nom = Lire.S( "SVP tapez le nom du Gang #" + (gangs.getNbGangs()+1),
                                "Nom du Gang non valide veuillez entrer un autre");

            String pseudo = Lire.S("SVP tapez le psuedo de " + nom,
                                    "psuedo non valide veuillez entrer un autre");
            
            Gang tempGang = new Gang(nom, pseudo);
            
            if(gangs.addGang(tempGang)){
                System.out.println("Voulez-vous ajouter au gang les mebres par défaut"
                                + "\nOui:o, non:n");
                opc2 = Lire.c();
                if(opc2 == 'o' || opc2 =='O')
                    tempGang.initDefaultGangMembres();
                
                System.out.println("Voulez-vous ajouter d'autres membres?"
                                + "\nOui:o, non:n");
                opc2 = Lire.c();
                while(opc2 == 'o' || opc2 =='O'){
                    GangMember tempGangMember = null;
                    System.out.println("\nsélectionnez le type de membre:"
                                   + "\nGangster:g, Pot De Vin:v");      
                    opc2 = Lire.c();

                    switch(opc2){
                        case 'g':
                        case 'G':
                                                         
                            int niveauGangster = Lire.i("SVP tapez le niveau du gangster (e.g: 1,2,3...)",
                                   "Ce niveau de gangster n'est pas autorisé, veuillez entrer un autre (e.g: 1,2,3...)",
                                   0,9999);
  
                           tempGangMember = new Gangster(tempGang, niveauGangster);
                            
                            break;
                            
                        case 'v':
                        case 'V':
                            
                            int valeurPotDeVin = Lire.i("SVP tapez la valeur du Pot de vin (e.g: 1,2,3...)",
                                   "Cette valeur pour le Pot de vin n'est pas autorisé, veuillez entrer un autre (e.g: 1,2,3...)",
                                   0,9999);

                            tempGangMember = new PotDeVin(tempGang, valeurPotDeVin);
                            
                            break;
                        default:
                            System.out.println("SVP tapez une option valable");
                            break;
                    }
                    if(tempGangMember != null){
                        tempGang.addGangMember(tempGangMember);
                        System.out.println("Membre ajoute au Gang "+ nom +" avec succès");
                    }
                    else{
                        System.out.println("Membre ne pas ajouté");
                    }
                    
                    System.out.println("souhaitez-vous en ajouter un autre?"
                            + "\nOui:o, non:n");
                    opc2 = Lire.c();
                }
                System.out.println("Gang " + nom +" créé avec succès");
                
            }else{
                System.out.println("il n'est pas possible de créer le gang avec\n"
                        + "ce nom et ce pseudo puisqu'il existe déjà");
            }
            System.out.println("\nsouhaitez-vous ajouter un autre Gang?"
                            + "\nOui:o, non:n");
            opc = Lire.c();
            
        }

        setGangs(gangs, gangs.getNbGangs());
    }

    /**
     * Permet que le joueur modifie le De
     * le nombre de faces et ses valuers
     */
    private void deModification(){

        int nbFaces = Lire.i("SVP tapez le nombre de faces qu'aura le dé",
                                   "La valeur n'est pas acceptee, veuillez entrer un autre (e.g: 1,2,3...)",
                                   0,9999);
        
        int [] faces = new int [nbFaces];
        
        for (int i = 0; i < nbFaces; i++)
            faces[i] = Lire.i("SVP tapez la valeur de la face "+(i+1),
                           "La valeur n'est pas acceptee",
                           0,9999);
        
        this.de = new De(faces);
    }
    
    /**
     * affiche tout les parametres du jeu actuel
     * comme les joueurs, les Gangs, la prision,
     * le de...
     */
    public void afficerLesParametres(){
        System.out.println("\n---------------------- Parametres Actueld du jeu ----------------------\n"
                + this.joueurs.toString() + '\n'
                + this.plateau.getgangs().toString()+ '\n'
                + this.plateau.getPrison().toString()+ '\n'
                + this.de.toString() + '\n'
                
        );
        System.out.println("---------------------- ************************** ----------------------\n");
    }
 
     /**
     * méthode en charge de démarrer le jeu
     * explication détaillée plus tard ... (..tu m'aide tellement)
     */
    private void jouer(){
        /**Verification de joueurs existents*/
        if( this.joueurs.getNbJoueurs() == 0 ){
            System.out.println("Impossible de commencer a jouer il n'y a aucun joueur");
            return;//on peut mettre une exception 
        }

        /**
         * verification qui aient des jetons sur le plateau
         * s'in n'est pas les cas, les jetons par defaut sont crée
         */
        if(this.plateau.getNbJetons()==0)
            chargerDefaultsGangs();
        
            
        /**
         * variables pour garder le resulta du de et la valeur soit
         * par l'utilisateur ou pour l'intelligence
         */
        int resDe = 0, 
            valeurChoisie =0;
        
        /**Joueur actuel*/
        Joueur jAct;
        
        /**Selection aleatoire de joueur a commencer*/
        this.joueurs.setIndexJoueurActuel( ((int)(Math.random() * this.joueurs.getNbJoueurs())) );
        
        /**
         * Boucle qui se repetira jusqu'à la fin du jeu
         */
        while( !this.plateau.getPrison().isPrisonComplete() ){
            
            /**Obtention du jueur actuel*/
            jAct = this.joueurs.getJoueurActuel(); 
            
            /**impression de la partie sur console*/
            System.out.println(this.plateau.show());
            System.out.println(this.joueurs.showJoueurs());
            System.out.println("C'est à "  + jAct.getNom() +" de jouer" );
            
            /**Lancement du De*/
            resDe = this.de.lancer();
            
            /**Etablissement de paramettres de l'intelligence par rapport au joueur actuel*/
            this.intelligence.setVolumen(jAct.getiVolumen());
            this.intelligence.setNi(jAct.getiNiveau());
            
            /**verification si le joueur est automatique ou pas*/
            int valeurIntelligence = 0;
            if (jAct.isAutomatique() ||  this.intelligence.getVolumen() !=0 )
                valeurIntelligence = this.intelligence.getCoup(de, plateau, joueurs);
            
            /**Cas du joueur pas automatique*/
            if(!jAct.isAutomatique())
                valeurChoisie = Lire.i("Le resulta du De est: " + resDe + " que jouez-vous ? : ",
                                    "La valeur doit etre entre 0 et " + resDe + " veuillez saisir une valeur correcte svp !",
                                    0,resDe);
            else{
                /**Cas du joueur automatique*/
                valeurChoisie = valeurIntelligence;
                System.out.println("Le resulta du De est: " + resDe +"\n"
                                    +valeurChoisie +" pas avancée Par: " + jAct.getNom()+"\n");
            }
            /**Deplacer le detective sur le plateau et cepturer le Jeton obtenue*/
            if(valeurChoisie != 0){
                GangMember gmAct = this.plateau.next( valeurChoisie );
            
                if(!gmAct.getType().equals(Boss.TYPE))
                    
                    jAct.addRecompense((Recompense)gmAct);
            }
            
            /**Passer au joueur suivant*/
            this.joueurs.nextJoueur();
            
            
        }
        
        /**Impression des resultats pour la fin du jeu*/
        System.out.println("\n***** Fin du jeu *****");
        System.out.println(this.plateau.toString());
        System.out.println("----------- Resultats -----------");
        System.out.println(this.joueurs.afficherPositions(-1));
        System.out.println("----------- ********* -----------");
        
        /**Activation de l'option rejouer*/
        this.rejouer = true;
        this.joueurs.getJoueurGagnant().addJeuGange();
        
    }
    
    /**
     * Permet de réinitialiser la partie
     * réinitialise les joueurs et le plateau
     */
    @Override
    public void reset(){
        this.joueurs.resetJoueurs();
        this.plateau.reset();
        System.out.println("Encore...");
    }
}
