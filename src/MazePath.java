import java.util.ArrayList;

public class MazePath {

    private ArrayList<Pair<Integer, Integer>> steps;

    public MazePath() {
        steps = new ArrayList<>();
    }

    public void addStep(int x, int y) {
        steps.add(new Pair<>(x, y));
    }

    public void print() {
        System.out.println("Begin MazePath");

        for (Pair<Integer, Integer> p : steps) {
            p.print();
        }

        System.out.println("End MazePath");
    }

}
