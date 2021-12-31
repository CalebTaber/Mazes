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
            Map<Direction, Cell> neighbors = current.getNeighbors();
            Object[] arr = neighbors.keySet().toArray();
            shuffle(arr);

            for (Object dir : arr) {
                Cell neighbor = current.getNeighbors().get((Direction) dir);
                if (!visited[neighbor.getX()][neighbor.getY()]) {
                    current.getNeighbors().remove(dir);
                    visited[neighbor.getX()][neighbor.getY()] = true;
                    stack.push(neighbor);
                }
            }
        }

        return maze;
    }

    public static RectMaze kruskal(int width, int height) {
        RectMaze maze = new RectMaze(width, height);



        return maze;
    }

}
