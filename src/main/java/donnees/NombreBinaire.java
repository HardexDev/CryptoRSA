package donnees;

import exceptions.ExceptionConversionImpossible;
import java.util.BitSet;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
        
    private BitSet listeBits;
    
    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }
    
    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for(int i=0;i<nombre.listeBits.length();i++) {
            this.listeBits.set(i,nombre.listeBits.get(i));
        } 
    }
    
    //Constructeur clone
    public NombreBinaire(BitSet bitset) {
        this.listeBits = new BitSet();
        for(int i=0;i<bitset.length();i++) {
            this.listeBits.set(i,bitset.get(i));
        } 
    }
    
    //Constructeur à partir d'un long
    public NombreBinaire(Long valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    //Constructeur à partir d'un byte 
    public NombreBinaire(byte b) {
        byte[] bt = new byte[1];
        bt[0] = b;
        this.listeBits = BitSet.valueOf(bt);
    }
    
    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(s.length()-i-1) == '1') {
                this.listeBits.set(i,true);
            }
        }
    }
    
    //Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
       //TODO
       return null;
    }
    
    
    //renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {
       //TODO
       return null;
    }
   
    
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i,valeur);
    }
    
    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }
    
    public BitSet asBitSet() {
        return this.listeBits;
    }
    
    public int getTaille() {
        return this.listeBits.length();
    }
    
    //Convertion en entier non signé 
    public int asInteger() throws ExceptionConversionImpossible{
        if(this.listeBits.length() > 31) throw new ExceptionConversionImpossible("Nombre binaire en entier (trop grand)");
        int res = 0;
        for(int i=0;i<this.listeBits.length();i++) {
            if(this.listeBits.get(i)) {
                res += Math.pow(2, i);
            }
        }
        return res;
    }
    
    //Affichage (dans le bon sens cette foi)
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.getTaille();i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        if(res == "") {
            res = "0";
        }
        return res;
    }
     
     //Renvoie le résultat de l'addition de this avec mot2
     public NombreBinaire addition(NombreBinaire mot2) {
        int R = 0;
        boolean B1, B2;
        int taille = this.getTaille() > mot2.getTaille() 
                ? this.getTaille() 
                : mot2.getTaille();
        NombreBinaire somme = new NombreBinaire(new BitSet(taille + 1));
        
        for (int i = 0; i < taille; i++){
            B1 = (i < this.getTaille()) ? this.get(i) : false;
            B2 = (i < mot2.getTaille()) ? mot2.get(i) : false;

            int b1Int = B1 ? 1 : 0;
            int b2Int = B2 ? 1 : 0;
            
            int add = b1Int + b2Int + R;
            
            R = add >= 2 ? 1 : 0; 
                
            somme.set(i, (add % 2) == 0 ? false : true);
        }
        
        if (R == 1)
            somme.set(taille, true);
        
        return somme;
     }
     
     //renvoie le resultat de l'addition de this avec mot3
     public NombreBinaire soustraction(NombreBinaire mot2) {
         NombreBinaire difference = new NombreBinaire(new BitSet(this.getTaille() + 1));
        int R = 0;
        boolean B1, B2;
        
        for (int i = 0; i < this.getTaille(); i++){
            B1 = this.get(i);
            B2 = (i < mot2.getTaille()) ? mot2.get(i) : false;

            int b1Int = B1 ? 1 : 0;
            int b2Int = B2 ? 1 : 0;
            
            int substract = b1Int - b2Int - R;
            
            R = substract < 0 ? 1 : 0; 
                
            if (substract < 0) {
                substract += 2;
            }
            
            difference.set(i, substract == 0 ? false : true);
        }
        
        if (R == 1)
            difference.set(this.getTaille(), true);
        
        return difference;
     }
     
     //Caclule le décalage de n bits (multiplie par 2^n)
     public NombreBinaire decalage(int n) {
       String nb = this.toString();
       
       for (int i = 0; i < n; i++) {
           nb += 0;
       }
       
       return new NombreBinaire(nb);
     }
     
     //Calcul la multiplication de this avec mot2
     public NombreBinaire multiplication(NombreBinaire mot2) {
       NombreBinaire produit = new NombreBinaire(0);
       
       for (int i = 0; i < mot2.getTaille(); i++) {
           
           if (mot2.get(i) == true) 
               produit = produit.addition(this.decalage(i));
       }
       
       return produit;
     }
     
     //Renvoie si this est plus petit ou égal à mot2
     public boolean estInferieurA(NombreBinaire mot2) {
        boolean estInferieur = false;
       
        if (this.getTaille() == mot2.getTaille()) {
            boolean sortie = false;
            int compteur = 1;
            
            do {
                if (this.get(this.getTaille() - compteur) != mot2.get(this.getTaille() - compteur)) {
                    if (this.get(this.getTaille() - compteur) == false)
                        estInferieur = true;
                    sortie = true;
                }
                else {
                    if (compteur + 1 <= this.getTaille())
                        compteur++;
                    else
                        sortie = true;
                }
            } while (!sortie);
        }
        else {
            if (this.getTaille() < mot2.getTaille())
                estInferieur = true;
        }

        return estInferieur;
     }
     
     //Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire b) {
        NombreBinaire q = new NombreBinaire(0);
        NombreBinaire r = new NombreBinaire(this.toString());
        NombreBinaire qPrime;
        NombreBinaire bPrime;
        int n;

        if (this.getTaille() >= b.getTaille()) {
            do {
                n = b.getTaille() < r.getTaille() ? r.getTaille() - b.getTaille() : 0;
                bPrime = b.decalage(n);

                if (!r.estInferieurA(b)) {
                    if (r.estInferieurA(bPrime)) {
                        bPrime = b.decalage(n - 1);
                        n--;
                    }

                    r = r.soustraction(bPrime);

                    q = q.addition(new NombreBinaire((int) (Math.pow(2, n))));
                }
            } while (!r.estInferieurA(b));
        }

        return r;
     }  

     //Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire b) {
        NombreBinaire q = new NombreBinaire(0);
        NombreBinaire r = new NombreBinaire(this.toString());
        NombreBinaire qPrime;
        NombreBinaire bPrime;
        int n;

        if (this.getTaille() >= b.getTaille()) {
            do {
                n = b.getTaille() < r.getTaille() ? r.getTaille() - b.getTaille() : 0;
                bPrime = b.decalage(n);

                if (!r.estInferieurA(b)) {
                    if (r.estInferieurA(bPrime)) {
                        bPrime = b.decalage(n - 1);
                        n--;
                    }

                    r = r.soustraction(bPrime);

                    q = q.addition(new NombreBinaire((int) (Math.pow(2, n))));
                }
            } while (!r.estInferieurA(b));
        }
        else 
            q = new NombreBinaire(0);

        return q;
     }
     
     //Calcul de this^exposant modulo m par exponentiation modulaire rapide
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
       //TODO
       return null;
     }
     
     public boolean estEgal(NombreBinaire mot2) {
        boolean estEgal = false;
       
        if (this.getTaille() == mot2.getTaille()) {
            if (this.toString().equals(mot2.toString()))
                estEgal = true;
        }
        
        return estEgal;
     }
     
     //Renvoie si un nombre est pair
     public boolean estPair() {
        boolean estPair = false;
       
        if (this.get(0) == false)
            estPair = true;
        
        return estPair;
     }
     
     
     public NombreBinaire PGCD(NombreBinaire mot2) {
       //TODO
       return null;
     }
     
     //Calcul de l'inverse modulo nombre
     //Basé sur l'algo d'euclide étendu (adapté).
     public NombreBinaire inverseModulaire(NombreBinaire nombre) {
         NombreBinaire ZERO = new NombreBinaire(0);
            
         NombreBinaire n0 = new NombreBinaire(nombre);
         NombreBinaire b0 = new NombreBinaire(this);
         NombreBinaire t0 = new NombreBinaire(0);
         NombreBinaire t = new NombreBinaire(1);
         
         NombreBinaire q = n0.quotient(b0);
         NombreBinaire r = n0.modulo(b0);
         while(!r.estEgal(ZERO)) {
             NombreBinaire produit = q.multiplication(t);
             NombreBinaire memoire;
             //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
             if(t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
             }
             else {
                memoire = t0.soustraction(produit).modulo(nombre);  
             }
             
             t0 = t;
             t = memoire;
             n0 = b0;
             b0 = r;
             q = n0.quotient(b0);
             r = n0.modulo(b0);
         }
         return t;
     }
}
