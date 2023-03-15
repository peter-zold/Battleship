package battleship;


public class Player {

    public String name;
    private boolean lost = false;
    private Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
    private Ship battleship = new Ship("Battleship", 4);
    private Ship submarine = new Ship("Submarine", 3);
    private Ship cruiser = new Ship("Cruiser", 3);
    private Ship destroyer = new Ship("Destroyer", 2);
    private String[][] board = new String[10][10];
    private String[][] foggyBoard = new String[10][10];

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String[][] getBoard() {
        return board;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean isLost() {
        return lost;
    }

    public Ship[] listAllShips() {
        return new Ship[] {aircraftCarrier, battleship, submarine, cruiser, destroyer};
    }

    public void initializeBoards() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = "~";
                foggyBoard[i][j] = "~";
            }
        }
    }

    public void printBoard(FogOfWar status) {
        // Upper border
        StringBuilder boardToPrint = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");
        // Side border character to increment
        char rowMarker = 'A';
        for (int i = 0; i < 10; i++) {
            boardToPrint.append("\n").append(rowMarker);
            rowMarker++;
            for (int j = 0; j < 10; j++) {
                boardToPrint.append(" ").append(status == FogOfWar.WITH_FOG ? foggyBoard[i][j] : status == FogOfWar.NO_FOG ? board[i][j] : "?");
            }
        }
        System.out.println(boardToPrint);
    }

    public void putDownShip(Ship ship) {
        if (!ship.isVertical()) {
            for (int i = ship.getStartCol(); i <= ship.getEndCol(); i++) {
                board[ship.getStartRow()][i] = "O";
            }
        }
        if (ship.isVertical()) {
            for (int i = ship.getStartRow(); i <= ship.getEndRow(); i++) {
                board[i][ship.getStartCol()] = "O";
            }
        }
    }

    public void getShot(int[] coords) {
        int row = coords[0];
        int col = coords[1];
        boolean isSunk = false;

        // if already shot a ship do nothing
        if (board[row][col].equals("X")) {
            System.out.println("You hit a ship!");

        // succesful shot, also check if shot and sank
        } else if (board[row][col].equals("O")) {
            for (Ship ship : listAllShips()) {
                int[][] shipCoords = ship.listCoordinates();
                for (int[] cellCoord : shipCoords) {
                    if(cellCoord[0] == row && cellCoord[1] == col) {
                        ship.setLife(ship.getLife()-1);
                        if(ship.getLife() == 0) {
                            isSunk = true;
                        }
                    }
                }
            }
            board[row][col] = "X";
            foggyBoard[row][col] = "X";
            System.out.println(isSunk? "You sank a ship!" : "You hit a ship!");

        // if hit a 0 or M , print missed
        } else {
            board[row][col] = "M";
            foggyBoard[row][col] = "M";
            System.out.println("You missed!");
        }

        // Check if all ship has sunk
        if (aircraftCarrier.getLife() == 0
                && battleship.getLife() == 0
                && submarine.getLife() == 0
                && cruiser.getLife() == 0
                && destroyer.getLife() == 0
        ) {
            setLost(true);
        }
    }
}
