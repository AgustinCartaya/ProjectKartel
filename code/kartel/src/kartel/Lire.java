package kartel;
        
import java.io.*;
public class Lire
{
public static String S() // Lire un String
{
String tmp = "";
char C='\0'; 
try {
         while ((C=(char) System.in.read()) !='\n')
         {
          if (C != '\r')  tmp = tmp+C;
          
         }
   }     
 catch (IOException e)
        {
          System.out.println("Erreur de frappe");
          System.exit(0);
        } 
 return tmp;
} // fin de S()


 public static String S(String message, String errorMessage){
        System.out.println(message);
        String tmp = "";
        boolean fait= false;
        do{
            if(fait)
                System.out.println(errorMessage);
            tmp = S();
            fait =  true;
        }while(tmp.trim().equals(""));
        return tmp;

 }
 
 
 public static byte b()  // Lire un entier byte
 {
         byte x=0;
                try {
                         x=Byte.parseByte(S());
                        }
                catch (NumberFormatException e) {
          System.out.println("Format num�rique incorrect");
          System.exit(0);
    }     
          return x ;
 }        
          
 public static short s()  // Lire un entier short
 {
         short x=0;
                try {
                         x=Short.parseShort(S());
                        }
                catch (NumberFormatException e) {
          System.out.println("Format num�rique incorrect");
          System.exit(0);
    }     
          return x ;
 }        
          
 //un peut de modification...        
 public static int i ()  throws NumberFormatException// Lire un entier
{
         int x=0;
                try {
                         x=Integer.parseInt(S());
                }        
                catch (NumberFormatException e) {
          throw new NumberFormatException();//("Format num�rique incorrect");
          
    }     
          return x ;
 }
 
 public static int i(String message, String errorMessage){
       System.out.println(message);
       int valeur = 0;
       boolean erreur = false;
       do{
            try {
                valeur = Lire.i();
                erreur =false;
            }
            catch(NumberFormatException e){
                erreur =  true;
                System.out.println(errorMessage);
            }
       }
       while(erreur);
       return valeur;

 }
 
 public static int i(String message, String errorMessage, int min, int max){
              System.out.println(message);
       int valeur = 0;
       boolean erreur = false;
       do{
            try {
                valeur = Lire.i();
                erreur =false;
            }
            catch(NumberFormatException e){
                erreur =  true;
            }
            if(erreur || valeur<min || valeur>max)
                System.out.println(errorMessage);
            
       }
       while(erreur || valeur<min || valeur>max);
       return valeur;
 }
 
          
 public static long l()  // Lire un entier long
 {
         long x=0;
                try {
                         x=Integer.parseInt(S());
                }        
                catch (NumberFormatException e) {
          System.out.println("Format num�rique incorrect");
          System.exit(0);
    }     
          return x ;
 }        
          
          
 public  static double d()  // Lire un double
 {
        double x=0.0;
                try {
                        x=Double.valueOf(S()).doubleValue();
                }    
                catch (NumberFormatException e) {
          System.out.println("Format num�rique incorrect");
          System.exit(0);
    }     
          return x ;
 }        
          
 public  static float f()  // Lire un float
 {
   float x=0.0f;
        try {
                x=Double.valueOf(S()).floatValue();
        }    
        catch (NumberFormatException e)
        {
          System.out.println("Format num�rique incorrect");
          System.exit(0);
    }     
          return x ;
 }        
          
          
 public  static char c()  // Lire un caractere
 {
  String tmp=S();
  if (tmp.length()==0)
          return '\n';
  else    
                {
                return tmp.charAt(0);
                }
 }        
}         
