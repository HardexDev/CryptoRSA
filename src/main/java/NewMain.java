
import challenges.Addition;
import challenges.Challenge;
import challenges.Chiffrer;
import challenges.ChiffrerMorceau;
import challenges.Connection;
import challenges.Decalage;
import challenges.Dechiffrer;
import challenges.DechiffrerMorceau;
import challenges.EstEgal;
import challenges.EstInferieur;
import challenges.EstPair;
import challenges.InverseModulaire;
import challenges.Modulo;
import challenges.Multiplication;
import challenges.PGCD;
import challenges.PuissanceModulaire;
import challenges.Quotient;
import challenges.RandomAvecBornes;
import challenges.RandomTailleFixe;
import challenges.Soustraction;
import donnees.NombreBinaire;
import exceptions.ExceptionCryptographie;
import protocoles.Protocole;
import protocoles.*;

import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthieu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExceptionCryptographie, IOException {
        Challenge c = new Dechiffrer();
        c.executeChallenge();
    }
}
