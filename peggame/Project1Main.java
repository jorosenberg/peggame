package peggame;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class for running a Peg Game
 * 
 * @author Michael Ambrose
 * @author Graham Rogozinski
 * @author Jonah Rosenberg
 */
public class Project1Main {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        System.out.print("Enter filename: ");
        String filename = s.nextLine();

        try {
        PegGame game = BoardReader.readBoard(filename);
        PegGameCLI.runGame(game);

        }
        catch (IOException ioe) {
            System.out.println("File not found.");
            main(args);
        }
        
        s.close();
    }
}
