public class Maze {

    private int length, width;

    private Boolean[][] squares; // True = open path; False = wall; null = uninitialized
    private MazePath solution;

    public Maze(int l, int w) {
        length = l;
        width = w;
        squares = new Boolean[length][width];

        solution = new MazePath();
    }

    public void print() {
        // TODO
    }

}
