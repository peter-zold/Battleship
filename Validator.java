package battleship;

public class Validator {
    // Try to validate shots
    public static boolean validateShots(int[] coords) throws IllegalAccessException{
        int row = coords[0];
        int col = coords[1];
        if (row < 0 || col < 0 || row > 9 || col > 9) {
            throw new IllegalAccessException("Error! Wrong coordinates! Try again:");
        }
        return true;
    }

    // Try to validate coords
    public static boolean validateCoords(Ship ship, int[] coords, String[][] board) throws IllegalAccessException{

        int startRow = coords[0];
        int endRow = coords[1];
        int startCol = coords[2];
        int endCol = coords[3];
        boolean isVertical;

        // Check if it is out of board
        if (startRow < 0 || startCol < 0 || endRow > 9 || endCol > 9) {
            throw new IllegalAccessException("Error! Wrong ship location! Try again:");
        }

        // Check if it is vertical, horizontal or invalid
        if (startRow == endRow) {
            isVertical = false;
        } else if (startCol == endCol) {
            isVertical = true;
        } else {
            throw new IllegalAccessException("Error! Wrong ship location! Try again:");
        }

        // Check if calculates length is valid
        if (!isVertical && endCol - startCol + 1 != ship.getLength()) {
            throw new IllegalAccessException("Error! Wrong length of the " + ship.getName() + "! Try again:");
        }
        if (isVertical && endRow - startRow + 1 != ship.getLength()) {
            throw new IllegalAccessException("Error! Wrong length of the " + ship.getName() + "! Try again:");
        }

        // Check if is it is adjacent to other ships or overlapping them
        if (!isVertical) {
            if (startCol - 1 >= 0 && board[startRow][startCol - 1].equals("O") || endCol + 1 < 10 && board[endRow][endCol + 1].equals("O")) {
                throw new IllegalAccessException("Error! You placed it too close to another one. Try again:");
            }
            for (int i = startCol; i <= endCol; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (board[startRow + j >= 0 && startRow + j < 10 ? startRow + j : startRow][i].equals("O")) {
                        throw new IllegalAccessException("Error! You placed it too close to another one. Try again:");
                    }
                }
            }
        }

        if (isVertical) {
            if (startRow - 1 >= 0 && board[startRow - 1][startCol].equals("O") || endRow + 1 < 10 && board[endRow + 1][endCol].equals("O")) {
                throw new IllegalAccessException("Error! You placed it too close to another one. Try again:");
            }
            for (int i = startRow; i <= endRow; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (board[i][startCol + j >= 0 && startCol + j < 10 ? startCol + j : startCol].equals("O")) {
                        throw new IllegalAccessException("Error! You placed it too close to another one. Try again:");
                    }
                }
            }
        }
        // Every condition checked
        ship.setVertical(isVertical);
        return true;
    }

}
