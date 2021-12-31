package cell;

import maze.Direction;

import java.util.HashMap;
import java.util.Map;

public class Cell {

    private Map<Direction, Cell> neighbors;
    private int x, y;

    public Cell(int x, int y) {
        neighbors = new HashMap<>(4);
        this.x = x;
        this.y = y;
    }

    public void addNeighbor(Direction dir, Cell neighbor) {
        neighbors.put(dir, neighbor);
    }

    public void removeWall(Direction dir, Cell neighbor) {
        addNeighbor(dir, neighbor);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Map<Direction, Cell> getNeighbors() {
        return neighbors;
    }

}
