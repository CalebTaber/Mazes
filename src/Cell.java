import java.util.*;

public class Cell {

    private boolean visited;
    private boolean[] wall; // 0 = above, 1 = right, 2 = below, 3 = left
    private int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        visited = false;
        wall = new boolean[4];
        Arrays.fill(wall, true);
    }

    public void removeWall(int direction) {
        if (direction < 0 || direction > 3) {
            System.out.println("ERROR: Invalid wall in Cell.removeWall()");
            return;
        }

        wall[direction] = false;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void visit() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean wallAbove() {
        return wall[0];
    }

    public boolean wallRight() {
        return wall[1];
    }

    public boolean wallBelow() {
        return wall[2];
    }

    public boolean wallLeft() {
        return wall[3];
    }

    public List<Integer> walls() {
        List<Integer> walls = new ArrayList<>(4);
        for (int i = 0; i < wall.length; i++) {
            if (wall[i]) walls.add(i);
        }

        return walls;
    }

}
