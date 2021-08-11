import java.util.*;

public class IterativeDFS {

    private Cell[][] cells;
    private int l, w;

    public IterativeDFS(int length, int width) {
        cells = new Cell[length][width];
        for (int i = 0; i < length; i++) {
            for (int o = 0; o < width; o++) {
                cells[i][o] = new Cell(i, o);

                if (i == 0) cells[i][o].removeWall(0);
                if (o == width - 1) cells[i][o].removeWall(2);
                if (i == length - 1) cells[i][o].removeWall(1);
                if (o == 0) cells[i][o].removeWall(3);
            }
        }

        l = length;
        w = width;
        generate();
    }

    private void generate() {
        Stack<Cell> visited = new Stack<>();
        Cell first = randomCell();
        first.visit();
        visited.push(randomCell());

        while (!visited.isEmpty()) {
            Cell current = visited.pop();
            List<Integer> currentWalls = current.walls();

            if (!currentWalls.isEmpty()) {
                visited.push(current);
                Collections.shuffle(current.walls());

                int wall = currentWalls.get(0);
                current.removeWall(wall);

                if (wall == 0) current = cells[current.x()][current.y() - 1];
            }
        }
    }

    private Cell randomCell() {
        Random r = new Random();
        return cells[r.nextInt(l)][r.nextInt(w)];
    }

    public void print() {
        for (Cell[] row : cells) {
            for (int i = 0; i < row.length; i++) {
                Cell c = row[i];

                if (i == 0) {
                    if (c.wallLeft()) System.out.print("|");
                    else System.out.print(" ");
                }

                if (c.wallAbove() && c.wallBelow()) System.out.print("=");
                else if (c.wallAbove()) System.out.print("-");
                else if (c.wallBelow()) System.out.print("_");
                else System.out.print(" ");

                if (c.wallRight()) System.out.print("|");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

}
