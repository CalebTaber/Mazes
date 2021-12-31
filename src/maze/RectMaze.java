package maze;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RectMaze extends Maze {

    private int width, height;
    private final int cellRenderSize = 10;

    public RectMaze(int width, int height) {
        this.width = width;
        this.height = height;

        int numVerts = width * height;
        vertices = new HashSet<>(numVerts);
        neighbors = new ArrayList<>(numVerts);
        walls = new ArrayList<>(numVerts);

        // Add all vertices to vertex set
        // Set all vertices to be adjacent to their neighbors by default
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int vertNum = (y * width) + x;
                vertices.add(vertNum);
                walls.add(new HashSet<>());

                HashSet<Integer> v_neighbors = new HashSet<>(4);
                if (x > 0) v_neighbors.add(vertNum - 1);                // Left adjacent
                if (x < width - 1) v_neighbors.add(vertNum + 1);        // Right adjacent
                if (y > 0) v_neighbors.add(vertNum - width);            // Up adjacent
                if (y < height - 1) v_neighbors.add(vertNum + width);   // Down adjacent
                neighbors.add(v_neighbors);
            }
        }
    }

    @Override
    public void addWall(int vertA, int vertB) {
        walls.get(vertA).add(vertB);
        walls.get(vertB).add(vertA);
    }

    private Direction direction(int srcCell, int destCell) {
        if (destCell == srcCell + 1) return Direction.EAST;
        else if (destCell == srcCell - 1) return Direction.WEST;
        else if (destCell == srcCell + width) return Direction.SOUTH;
        else if (destCell == srcCell - width) return Direction.NORTH;
        else return Direction.NONE;
    }

    private void renderCell(BufferedImage bi, int cell) throws Exception {
        Set<Integer> cellWalls = walls.get(cell);

        int xOrigin = (cell % width) * cellRenderSize;
        int yOrigin = Math.floorDiv(cell, height) * cellRenderSize;

        // Initialize all pixels to be blue
        int[] blue = new int[cellRenderSize * cellRenderSize];
        Arrays.fill(blue, 255);
        bi.setRGB(xOrigin, yOrigin ,cellRenderSize, cellRenderSize, blue, 0, cellRenderSize);

        // Add black pixels for walls
        int[] black = new int[cellRenderSize];
        Arrays.fill(black, 0);
        for (int wall : cellWalls) {
            switch (direction(cell, wall)) {
                case EAST ->
                        bi.setRGB(xOrigin + (cellRenderSize - 1), yOrigin, 1, cellRenderSize, black, 0, 1);
                case NORTH ->
                        bi.setRGB(xOrigin, yOrigin, cellRenderSize, 1, black, 0, cellRenderSize);
                case WEST ->
                        bi.setRGB(xOrigin, yOrigin, 1, cellRenderSize, black, 0, 1);
                case SOUTH ->
                        bi.setRGB(xOrigin, yOrigin + (cellRenderSize - 1), cellRenderSize, 1, black, 0, cellRenderSize);
                default -> throw new Exception("A cell cannot have a wall between it and itself (cell " + cell + " is in its own walls list");
            }
        }


    }

    @Override
    public void render(File output) {
        BufferedImage bi = new BufferedImage(width * cellRenderSize, height * cellRenderSize, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < walls.size(); i++) {
            try {
                renderCell(bi, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            ImageIO.write(bi, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
