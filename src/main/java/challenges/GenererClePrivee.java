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
 * @author Alex
 */
public class GenererClePrivee implements Challenge {
    
    @Override
    public void executeChallenge() throws IOException {
       Client client = new Client();
        String message = client.receiveMessage();
        String message1 = client.receiveMessage();
        String message2 = client.receiveMessage();
        String message3 = client.receiveMessage();
        
        do {
            NombreBinaire p = new NombreBinaire(message1);
            NombreBinaire q = new NombreBinaire(message2);
            NombreBinaire e = new NombreBinaire(message3);
            
            client.sendMessage(e.genererClePrivee(p,q).toString());
            
            message = client.receiveMessage();
            message1 = client.receiveMessage();
            message2 = client.receiveMessage();
            message3 = client.receiveMessage();
        } while (!message1.equals("Defi valide"));
    }
    
}
