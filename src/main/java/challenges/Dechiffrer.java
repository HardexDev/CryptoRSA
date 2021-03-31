/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import algorithmes.chiffrement.AlgorithmeRSA;
import coucheReseau.client.Client;
import donnees.MotBinaire;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import donnees.messages.MessageBinaire;
import exceptions.ExceptionConversionImpossible;
import exceptions.ExceptionCryptographie;
import java.io.IOException;

/**
 *
 * @author jules
 */
public class Dechiffrer implements Challenge {

    @Override
    public void executeChallenge() throws IOException {
        Client client = new Client();
        AlgorithmeRSA algoRSA = new AlgorithmeRSA();
        String message = client.receiveMessage();
        String message1 = client.receiveMessage();
        String message2 = client.receiveMessage();;
        String message3;
        
        do {
            message3 = client.receiveMessage();
            
            MotBinaire nb1 = new MotBinaire(message1);
            MotBinaire nb2 = new MotBinaire(message2);
            MotBinaire nb3 = new MotBinaire(message3);
            
            MessageBinaire m = new MessageBinaire(nb1);
            CleBinaire cleRSA_N = new CleBinaire(nb2);
            CleBinaire cleRSA_d = new CleBinaire(nb3);
            
            Cles clesPlubiques = new Cles();
            Cles clesPrivees = new Cles();
            clesPlubiques.addCle("cleRSA_N", cleRSA_N);
            clesPrivees.addCle("cleRSA_d", cleRSA_d);
            
            try {
                client.sendMessage(algoRSA.dechiffrer(m, clesPlubiques, clesPrivees).asMotBinaire().toString());
            } catch (ExceptionConversionImpossible ex) {
                ex.printStackTrace();
            } catch (ExceptionCryptographie ex) {
                ex.printStackTrace();
            }
            
            message = client.receiveMessage();
            message1 = client.receiveMessage();
            message2 = client.receiveMessage();
        } while (!message2.equals("FIN"));
    }
    
}
