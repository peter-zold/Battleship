package battleship;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


import static battleship.Validator.validateCoords;
import static battleship.Validator.validateShots;

public class Moves {

    // Get coords of ship's position
    private static int[] getShipCoords() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        List<Integer> bothRows = Arrays.asList(rowCharToInt(input[0].charAt(0)) - 1, rowCharToInt(input[1].charAt(0)) - 1);
        List<Integer> bothCols = Arrays.asList(Integer.parseInt(input[0].substring(1)) - 1, Integer.parseInt(input[1].substring(1)) - 1);
        int startRow = Collections.min(bothRows);
        int endRow = Collections.max(bothRows);
        int startCol = Collections.min(bothCols);
        int endCol = Collections.max(bothCols);
        return new int[]{startRow, endRow, startCol, endCol};
    }

    // Get coords of shot
    private static int[] getShotCoords() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        return new int[]{rowCharToInt(input[0].charAt(0)) - 1, Integer.parseInt(input[0].substring(1)) - 1};
    }

    // Place ships on player's board
    public static void placeShips(Player player) {
        // Initialize board of player
        player.initializeBoards();
        System.out.println(player.getName() + ", place your ships on the game field");
        player.printBoard(FogOfWar.NO_FOG);
        // Place all ships in validated places and register their coords
        for (Ship ship : player.listAllShips()) {
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getLength());
            // Validating placement
            boolean areCordsValid = false;
            while (!areCordsValid) {
                try {
                    int[] coords = getShipCoords();
                    areCordsValid = validateCoords(ship, coords, player.getBoard());
                    ship.setStartRow(coords[0]);
                    ship.setEndRow(coords[1]);
                    ship.setStartCol(coords[2]);
                    ship.setEndCol(coords[3]);
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
            // Finally put the ship on board
            player.putDownShip(ship);
            player.printBoard(FogOfWar.NO_FOG);
        }
    }


    // Active player shoot on passive's board
    public static void shootAShip(Player active, Player passive) {
        passive.printBoard(FogOfWar.WITH_FOG);
        System.out.println("---------------------");
        active.printBoard(FogOfWar.NO_FOG);
        System.out.println(active.getName() + ", it's your turn:");
        boolean areShotCoordsValid = false;
        int[] shotCoords = {1, 1};
        while (!areShotCoordsValid) {
            try {
                shotCoords = getShotCoords();
                areShotCoordsValid = validateShots(shotCoords);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        passive.getShot(shotCoords);
    }

    // Utility to convert A,B,C... to a row number
    private static int rowCharToInt(char x) {
        return x - 64;
    }
}


