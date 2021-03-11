package coucheReseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket; 
    private PrintWriter pw;
    private BufferedReader bufr;
    
    public Client() throws IOException {
        this.socket = new Socket("127.0.0.1",1234);
        InputStreamReader inputStreamReader = new InputStreamReader(this.socket.getInputStream());
        this.bufr = new BufferedReader(inputStreamReader);

        this.pw = new PrintWriter(this.socket.getOutputStream(), true);
    }
    
    public void sendMessage(String message) {
        System.out.println(">>" + message);
        this.pw.println(message);
    }
        
    //Renvoie true si l'IA doit continuer
    public String receiveMessage() throws IOException {
        String message = this.bufr.readLine();
        System.out.println("<<"+message);
        return message;
    }
    
    public void end() throws IOException {
        this.socket.close();
    }
    
}
