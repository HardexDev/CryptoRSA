package challenges;

import coucheReseau.client.Client;
import donnees.NombreBinaire;

import java.io.IOException;

public class Addition implements Challenge {
    
    @Override
    public void executeChallenge() throws IOException {
        Client client = new Client();
        String message = client.receiveMessage();
        String message1 = client.receiveMessage();
        String message2 = client.receiveMessage();
        
        do {
            NombreBinaire nb1 = new NombreBinaire(message1);
            NombreBinaire nb2 = new NombreBinaire(message2);
            
            client.sendMessage(nb1.addition(nb2).toString());
            
            message = client.receiveMessage();
            message1 = client.receiveMessage();
            message2 = client.receiveMessage();
        } while (!message1.equals("Defi valide"));
    }
}
