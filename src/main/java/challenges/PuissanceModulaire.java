/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import coucheReseau.client.Client;
import donnees.NombreBinaire;
import java.io.IOException;

/**
 *
 * @author jules
 */
public class PuissanceModulaire implements Challenge {

    @Override
    public void executeChallenge() throws IOException {
        Client client = new Client();
        String message = client.receiveMessage();
        String message1 = client.receiveMessage();
        String message2 = client.receiveMessage();;
        String message3;
        
        do {
            message3 = client.receiveMessage();
            
            NombreBinaire nb1 = new NombreBinaire(message1);
            NombreBinaire nb2 = new NombreBinaire(message2);
            NombreBinaire nb3 = new NombreBinaire(message3);
            
            client.sendMessage(nb1.puissanceModulo(nb2, nb3).toString());
            
            message = client.receiveMessage();
            message1 = client.receiveMessage();
            message2 = client.receiveMessage();
        } while (!message2.equals("FIN"));
    }
    
}
