package peggame;

import java.util.List;
import java.util.Scanner;


/**
 * Command line interface implementation for PegGame
 */
public class PegGameCLI {
    
    /**
     * Method for PegGame inputs.
     * 
     * Commands:
     *  help - displays help message
     *  move r1 c1 r2 c2 - attempts to move a peg form r1 c1 to r2 c2 on the board
     *  hint - dispplays an available move
     *  quit - quits the game
     * 
     * prints goodbye message at end of game.
     * 
     * @param game Peg Game to run off of.  
     */
    public static void runGame(PegGame game) {
        System.out.println(game);
        
        Scanner s = new Scanner(System.in);

        while(game.getGameState() != GameState.WON && game.getGameState() != GameState.STALEMATE) {
            System.out.print(">> ");

            String command = s.nextLine();
            command.strip();

            String[] sCommand = command.split(" ");

            if (command.equals("help")) {
                System.out.println("Available commands: \n" + 
                                    "  help - displays this message \n" +
                                    "  move r1 c1 r2 c2 - attempts to move a peg from r1 c1 to r2 c2 on the board. \n" +
                                    "  hint - displays an available move. \n" +
                                    "  solve - solves the game. \n" + 
                                    "  quit - quits the game\n");
            }
            if (command.equals("quit")) {
                System.out.print("Are you sure (y/n): ");
                String answer = s.nextLine();
                if (answer.equals("y")) break;
            };
            if (sCommand[0].equals("move")) {
                try {
                int r1 = Integer.parseInt(sCommand[1]);
                int c1 = Integer.parseInt(sCommand[2]);

                Location from = new Location(r1, c1);

                int r2 = Integer.parseInt(sCommand[3]);
                int c2 = Integer.parseInt(sCommand[4]);

                Location to = new Location(r2, c2);

                Move move = new Move(from, to);

                game.makeMove(move);
                }
                catch (PegGameException pge) {
                    System.out.println("Invalid move. \n");
                }
                catch (IndexOutOfBoundsException obe) {
                    System.out.println("Invalid move. \n");
                }

            };
            if (command.equals("hint")) {
                GameConfig newConfig = GameConfig.solveGame(game);

                if(newConfig != null)
                    System.out.println("hint: " + newConfig.previousMoves.get(0) + "\n");
                else
                    System.out.println("No Solution");
            };

            if(command.equals("solve")){
                GameConfig newSolve = GameConfig.solveGame(game);

                if(newSolve == null)
                    System.out.println("No Solution");
                else {
                    List<Move> solvedMoves = newSolve.previousMoves;

                    for(Move move : solvedMoves) {
                        try{
                        //Make the move
                        System.out.println("Making move: " + move);
                        game.makeMove(move);
                        //Print board
                        System.out.println(game);
                        }
                        catch(PegGameException pge) {
                            System.out.println("Invalid");
                        }
                    }
                }
            }

            if (game.getGameState() == GameState.WON) 
                System.out.println("You win! \n");
                
            if (game.getGameState() == GameState.STALEMATE) 
                System.out.println("No more moves. \n");
                
            System.out.println(game);
            
        }
        System.out.println("Goodbye!");
        s.close();
    }

    public static void main(String[] args) {
        PegGame game = new PegBoard(3, 4);

        runGame(game);
    }
}
