package algorithmes.chiffrement;

import algorithmes.chiffrement.RSA.ParametresRSA;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.Cles;
import donnees.messages.Message;
import donnees.messages.MessageBinaire;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.util.ArrayList;

public class AlgorithmeRSA implements Algorithme{

    
    @Override
    public String getNom() {
        return "RSA";
    }
    
    //Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public MotBinaire chiffrerMorceau(MotBinaire morceau, Cles clesPubliques) throws ExceptionConversionImpossible {
       NombreBinaire morceauChiffre = new NombreBinaire(morceau.getBitSet());
       NombreBinaire cleRSA_e = new NombreBinaire(clesPubliques.getCle("cleRSA_e").asMotBinaire().getBitSet());
       NombreBinaire cleRSA_N = new NombreBinaire(clesPubliques.getCle("cleRSA_N").asMotBinaire().getBitSet());
       
       morceauChiffre = morceauChiffre.puissanceModulo(cleRSA_e, cleRSA_N);
       
       MotBinaire retour = new MotBinaire(morceauChiffre.asBitSet(), ParametresRSA.getTailleCle());
       
       return retour;
    }
    
    //Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public MotBinaire dechiffrerMorceau(MotBinaire morceau, Cles clesPubliques, Cles clesPrivees) throws ExceptionConversionImpossible {
       NombreBinaire morceauDechiffre = new NombreBinaire(morceau.getBitSet());
       NombreBinaire cleRSA_d = new NombreBinaire(clesPrivees.getCle("cleRSA_d").asMotBinaire().getBitSet());
       NombreBinaire cleRSA_N = new NombreBinaire(clesPubliques.getCle("cleRSA_N").asMotBinaire().getBitSet());
       
       morceauDechiffre = morceauDechiffre.puissanceModulo(cleRSA_d, cleRSA_N);
       
       MotBinaire retour = new MotBinaire(morceauDechiffre.asBitSet(), ParametresRSA.getTailleMorceau());
       
       return retour;
    }

    @Override
    public Message chiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       int nbMorceaux = ParametresRSA.getTailleMorceau();
       ArrayList<MotBinaire> morceaux = message.asMotBinaire().scinder(nbMorceaux);   
       MotBinaire messageChiffre = new MotBinaire();
       
       Cles cles = new Cles();
       cles.addCle("cleRSA_N", clesPubliques.getCle("cleRSA_N"));
       cles.addCle("cleRSA_e", clesPrivees.getCle("cleRSA_e"));
       
       for (int i = 0; i < morceaux.size(); i++) {
           messageChiffre = this.chiffrerMorceau(morceaux.get(i), cles).concatenation(messageChiffre);
       }
       
       return new MessageBinaire(messageChiffre);
    }

    @Override
    public Message dechiffrer(Message message, Cles clesPubliques, Cles clesPrivees) throws ExceptionCryptographie {
       int nbMorceaux = ParametresRSA.getTailleCle();
       ArrayList<MotBinaire> morceaux = message.asMotBinaire().scinder(nbMorceaux);   
       MotBinaire messageDechiffre = new MotBinaire();
       
       for (int i = 0; i < morceaux.size(); i++) {
           messageDechiffre = this.dechiffrerMorceau(morceaux.get(i), clesPubliques, clesPrivees).concatenation(messageDechiffre);
       }
       
       return new MessageBinaire(messageDechiffre);
    }

}
