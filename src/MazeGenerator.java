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

    public static RectMaze iterDFS(int width, int height) {
        RectMaze maze = new RectMaze(width, height);

        boolean[] visited = new boolean[width * height];
        Stack<Integer> stack = new Stack<>();
        Random rand = new Random();

        int first = rand.nextInt(width * height);
        visited[first] = true;
        stack.push(first);

        while (!stack.empty()) {
            int current = stack.pop();
            Set<Integer> neighbors = new HashSet<>(maze.getNeighbors(current));
            Object[] arr = neighbors.toArray();
            shuffle(arr);

            for (Object n : arr) {
                int neighbor = (int) n;
                if (!visited[neighbor]) {
                    maze.addWall(current, neighbor);
                    visited[neighbor] = true;
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
