package Challenges;

import coucheReseau.client.Client;

import java.io.IOException;

public class Connection implements Challenge {
    @Override
    public void executeChallenge() throws IOException {
        Client client = new Client();
        String messageRecu = client.receiveMessage();
        messageRecu = client.receiveMessage();
        while (!messageRecu.equals("FIN")){
            if (!messageRecu.equals("OK") && !messageRecu.equals("NOK") && !messageRecu.equals("Defi valide") && !messageRecu.equals("Defi echoue!")) {
                client.sendMessage(String.valueOf(Integer.parseInt(messageRecu) + 1));
            }
            messageRecu = client.receiveMessage();
        }
        client.end();
    }
}
