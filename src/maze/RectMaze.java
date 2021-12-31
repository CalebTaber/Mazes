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

    private final int cellRenderSize = 10;
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

//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (x > 0) cells[x][y].addNeighbor(Direction.WEST, cells[x - 1][y]);
//                if (x < width - 1) cells[x][y].addNeighbor(Direction.EAST, cells[x + 1][y]);
//                if (y > 0) cells[x][y].addNeighbor(Direction.NORTH, cells[x][y - 1]);
//                if (y < height - 1) cells[x][y].addNeighbor(Direction.SOUTH, cells[x][y + 1]);
//            }
//        }

        WALL_PIXELS = new int[cellRenderSize];
        Arrays.fill(WALL_PIXELS, 0);
    }

    @Override
    public void render(File output) {
        int renderWidth = width * cellRenderSize;
        int renderHeight = height * cellRenderSize;
        BufferedImage bi = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_RGB);

        int[] blue = new int[renderWidth * renderHeight];
        Arrays.fill(blue, 255);
        bi.setRGB(0, 0, width * cellRenderSize, height * cellRenderSize, blue, 0, width * cellRenderSize);

        // Draw walls for each cell
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int xStart = x * cellRenderSize;
                int yStart = y * cellRenderSize;

                // Walls exist in directions where the given cell has no neighbor
                for (Direction d : Direction.values()) {
                    if (cells[x][y].getNeighbors().containsKey(d)) continue;

                    switch (d) {
                        case EAST -> bi.setRGB(xStart + (cellRenderSize - 1), yStart, 1, cellRenderSize, WALL_PIXELS, 0, 1);
                        case NORTH -> bi.setRGB(xStart, yStart, cellRenderSize, 1, WALL_PIXELS, 0, cellRenderSize);
                        case WEST -> bi.setRGB(xStart, yStart, 1, cellRenderSize, WALL_PIXELS, 0, 1);
                        case SOUTH -> bi.setRGB(xStart, yStart + (cellRenderSize - 1), cellRenderSize, 1, WALL_PIXELS, 0, cellRenderSize);
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
