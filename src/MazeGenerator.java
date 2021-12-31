import cell.Cell;
import maze.Direction;
import maze.Maze;
import maze.RectMaze;

import java.util.*;

public class MazeGenerator {

    private static void shuffle(Object[] array) {
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            Object tmp = array[i];
            int swapIndex = (i + r.nextInt(array.length)) % array.length;

            array[i] = array[swapIndex];
            array[swapIndex] = tmp;
        }
    }

    /*
    Want the graph of the maze to be connected
    How to ensure that?
    Remove walls rather than add in iterDFS
     */

    private static Direction dir(Cell src, Cell dest) {
        int srcX = src.getX();
        int srcY = src.getY();

        int destX = dest.getX();
        int destY = dest.getY();

        if (destX == srcX + 1) return Direction.EAST;
        if (destX == srcX - 1) return Direction.WEST;
        if (destY == srcY - 1) return Direction.NORTH;
        if (destY == srcY + 1) return Direction.SOUTH;
        return Direction.NONE;
    }

    public static Maze iterDFS(int width, int height) {
        RectMaze maze = new RectMaze(width, height);

        boolean[][] visited = new boolean[width][height];
        Stack<Cell> stack = new Stack<>();
        Random rand = new Random();

        int firstX = rand.nextInt(width);
        int firstY = rand.nextInt(height);
        visited[firstX][firstY] = true;
        stack.push(maze.getCell(firstX, firstY));

        while (!stack.empty()) {
            Cell current = stack.pop();
            int x = current.getX();
            int y = current.getY();

            Set<Cell> neighbors = new HashSet<>(4);
            if (x > 0) neighbors.add(maze.getCell(x-1, y));
            if (x < width - 1) neighbors.add(maze.getCell(x+1, y));
            if (y > 0) neighbors.add(maze.getCell(x, y-1));
            if (y < height - 1) neighbors.add(maze.getCell(x, y+1));

            Object[] shuffledNeighbors = neighbors.toArray();
            shuffle(shuffledNeighbors);

            for (Object n : shuffledNeighbors) {
                Cell neighbor = (Cell) n;
                if (visited[neighbor.getX()][neighbor.getY()])
                    continue;

                current.removeWall(dir(current, neighbor), neighbor);
                neighbor.removeWall(dir(neighbor, current), current);

                visited[neighbor.getX()][neighbor.getY()] = true;
                stack.push(neighbor);
            }

        }

        return maze;
    }

    public static RectMaze kruskal(int width, int height) {
        RectMaze maze = new RectMaze(width, height);



        return maze;
    }

}
