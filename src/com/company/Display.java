package com.company;

import java.util.Set;
import java.util.stream.Stream;

public class Display {

    private Display() {
    }

    public static void printGeneration(Set<Cell> cellsAlive) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Cell cell : cellsAlive) {
            int cellX = cell.getX();
            int cellY = cell.getY();

            if (maxX < cellX) {
                maxX = cellX;
            }
            if (maxY < cellY) {
                maxY = cellY;
            }
            if (minX > cellX) {
                minX = cellX;
            }
            if (minY > cellY) {
                minY = cellY;
            }
        }

        for (int i = minX; i < maxX + 1; i++) {
            for (int j = minY; j < maxY + 1; j++) {
                System.out.print(cellsAlive.contains(new Cell(i, j)) ? "o" : " ");
            }
            System.out.println();
        }
        Stream.generate(() -> "-").limit(maxY + 1 - (long) minY).forEach(System.out::print);
        System.out.println();
    }
}
