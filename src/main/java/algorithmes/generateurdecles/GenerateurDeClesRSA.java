package algorithmes.generateurdecles;

import algorithmes.chiffrement.RSA.ParametresRSA;
import algorithmes.chiffrement.RSA.RabinMiller;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import java.util.logging.Logger;

/**
 * Description de la classe
 * @author Matthieu
 */
public class GenerateurDeClesRSA implements GenerateurDeCles{

    
    private NombreBinaire P;
    private NombreBinaire Q;
    private NombreBinaire N;;
    private NombreBinaire phi;
    private NombreBinaire e;
        
    @Override
    public Cles genererClePublique() {
       //TODO
       return null;
    }

    @Override
    public Cles genererClePrivee() {
       //TODO
       return null;
    }
    
    public void setP (NombreBinaire P){
        this.P = P;
    }
    public void setQ (NombreBinaire Q){
        this.Q = Q;
    }
    public void setPhi (NombreBinaire phi){
        this.phi = phi;
    }
    public void setE (NombreBinaire e){
        this.e = e;
    }

    
    
}
