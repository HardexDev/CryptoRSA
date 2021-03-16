/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import challenges.Challenge;
import coucheReseau.client.Client;
import donnees.NombreBinaire;
import java.io.IOException;

/**
 *
 * @author jules
 */
public class RandomTailleFixe implements Challenge {
    
    /**
     * Renvoie un nombre binaire aléatoire de taille égale ou inférieure à la 
     * taille passée en paramètre
     * @param taille La taille maximale du nombre binaire
     * @return le nombre bianire aléatoire
     */
    static NombreBinaire getRandomTailleFixe(int taille) {
        String nombre = "";
        
        for (int i = 0; i < taille; i++) {
            nombre += Math.random() > 0.5 ? "1" : "0";
        }
        
        return new NombreBinaire(nombre);
    }

    @Override
    public void executeChallenge() throws IOException {
        Client client = new Client();
        String message = client.receiveMessage();
        String message1 = client.receiveMessage();
        
        do {
            int taille = Integer.parseInt(message1);
            
            client.sendMessage(RandomTailleFixe.getRandomTailleFixe(taille).toString());
            
            message = client.receiveMessage();
            message1 = client.receiveMessage();
        } while(!message1.equals("Defi valide"));
    }
}
