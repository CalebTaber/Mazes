package maze;

import cell.Cell;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RectMaze extends Maze {

    private final int width, height;
    private final Cell[][] cells;

    private final int CELL_RENDER_SIZE = 10;
    private final int[] WALL_PIXELS;

    public RectMaze(int width, int height) {
        this.width = width;
        this.height = height;

        cells = new Cell[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = new Cell(x, y);
            }
        }

        // TODO choose start and end cells from outer edge of maze

//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (x > 0) cells[x][y].addNeighbor(Direction.WEST, cells[x - 1][y]);
//                if (x < width - 1) cells[x][y].addNeighbor(Direction.EAST, cells[x + 1][y]);
//                if (y > 0) cells[x][y].addNeighbor(Direction.NORTH, cells[x][y - 1]);
//                if (y < height - 1) cells[x][y].addNeighbor(Direction.SOUTH, cells[x][y + 1]);
//            }
//        }

        WALL_PIXELS = new int[CELL_RENDER_SIZE];
        Arrays.fill(WALL_PIXELS, 0);
    }

    @Override
    public void render(File output) {
        int renderWidth = width * CELL_RENDER_SIZE;
        int renderHeight = height * CELL_RENDER_SIZE;
        BufferedImage bi = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_RGB);

        int[] blue = new int[renderWidth * renderHeight];
        Arrays.fill(blue, 255);
        bi.setRGB(0, 0, width * CELL_RENDER_SIZE, height * CELL_RENDER_SIZE, blue, 0, width * CELL_RENDER_SIZE);

        // Draw walls for each cell
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int xStart = x * CELL_RENDER_SIZE;
                int yStart = y * CELL_RENDER_SIZE;

                // Walls exist in directions where the given cell has no neighbor
                for (Direction d : Direction.values()) {
                    if (cells[x][y].getNeighbors().containsKey(d)) continue;

                    switch (d) {
                        case EAST -> bi.setRGB(xStart + (CELL_RENDER_SIZE - 1), yStart, 1, CELL_RENDER_SIZE, WALL_PIXELS, 0, 1);
                        case NORTH -> bi.setRGB(xStart, yStart, CELL_RENDER_SIZE, 1, WALL_PIXELS, 0, CELL_RENDER_SIZE);
                        case WEST -> bi.setRGB(xStart, yStart, 1, CELL_RENDER_SIZE, WALL_PIXELS, 0, 1);
                        case SOUTH -> bi.setRGB(xStart, yStart + (CELL_RENDER_SIZE - 1), CELL_RENDER_SIZE, 1, WALL_PIXELS, 0, CELL_RENDER_SIZE);
                    }
                }
            }
        }

        try {
            ImageIO.write(bi, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

}
