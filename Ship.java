package battleship;

public class Ship {

    private String name;
    private int length;

    private int life;
    private boolean isVertical;
    private int startRow;
    private int endRow;
    private int startCol;
    private int endCol;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.life = length;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int startEnd) {
        this.endCol = startEnd;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int[][] listCoordinates() {
        int[][] coords = new int[length][2];
        for (int i = 0; i < length; i++) {
            coords[i][0] = isVertical ? startRow + i : startRow;
            coords[i][1] = isVertical ? startCol : startCol + i;
        }
        return coords;
    }
}
