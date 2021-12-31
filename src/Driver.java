import maze.Maze;

import java.io.File;

public class Driver {

    public static void main(String[] args) {
        Maze dfs = MazeGenerator.iterDFS(50, 50);
        dfs.render(new File(System.getProperty("user.dir") + "/dfs.png"));

//        Maze kruskal = MazeGenerator.kruskal(50, 50);
//        kruskal.render(new File(System.getProperty("user.dir") + "/kruskal.png"));
    }

}
