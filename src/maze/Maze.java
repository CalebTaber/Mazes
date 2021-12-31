package maze;

import java.io.File;
import java.util.List;
import java.util.Set;

public abstract class Maze {

    protected Set<Integer> vertices;
    protected List<Set<Integer>> neighbors; // Neighbors of each vertex
    protected List<Set<Integer>> walls; // List of walls around each vertex

    abstract void addWall(int vertA, int vertB);

    public abstract void render(File output);

    public Set<Integer> getNeighbors(int vertex) {
        return neighbors.get(vertex);
    }

}
