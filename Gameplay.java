package battleship;

import java.util.Scanner;

import static battleship.Moves.placeShips;
import static battleship.Moves.shootAShip;

public class Gameplay {

    // Initializing players
    private static Player player1 = new Player("Player 1");
    private static Player player2 = new Player("Player 2");

    // Referring active, passive statuses to players
    private static Player active = player1;
    private static Player passive = player2;

    // Method to execute game
    public static void executeGame() {

        // Place down ships
        placeShips(active);
        changePlayer();
        placeShips(active);
        changePlayer();


        // Make shots one after and other till passive player lose
        while(!passive.isLost()) {
            shootAShip(active, passive);
            if(passive.isLost()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            changePlayer();
        }
    }

    // Function to change between players
    private static void changePlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter and pass the move to another player");
        scanner.nextLine();
        System.out.println("...");
        System.out.println();
        active = player1.equals(active) ? player2 : player1;
        passive = player2.equals(passive) ? player1 : player2;
    }
}
