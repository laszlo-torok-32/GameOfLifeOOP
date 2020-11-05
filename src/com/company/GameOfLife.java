package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLife {

    private Set<Cell> cellsAlive;
    private static final int[][] directions = {
            {1, 0},
            {1, 1},
            {1, -1},
            {0, 1},
            {0, -1},
            {-1, 0},
            {-1, 1},
            {-1, -1},
    };
    private boolean generationChanged;

    public GameOfLife() {
        this.cellsAlive = new HashSet<>();
        this.generationChanged = true;
    }

    public void startGame() throws InterruptedException {

        placeStartingCells();
        while (generationChanged) {

            Display.printGeneration(cellsAlive);
            Set<Cell> oldGeneration = cellsAlive;
            cellsAlive = getNextGeneration();
            generationChanged = isGenerationChanged(oldGeneration);
            Thread.sleep(500);

        }
    }

    boolean isGenerationChanged(Set<Cell> oldGeneration) {
        return !oldGeneration.equals(cellsAlive);
    }

    Set<Cell> getNextGeneration() {
        Set<Cell> nextRoundCellsAlive = new HashSet<>();

        for (Cell cell : cellsAlive) {

            int aliveNeighbours = getAliveNeighbours(cell).size();
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                nextRoundCellsAlive.add(cell);
            }
            for (Cell deadNeighbour : getDeadNeighbours(cell)) {
                if (getAliveNeighbours(deadNeighbour).size() == 3) {
                    nextRoundCellsAlive.add(deadNeighbour);
                }
            }
        }
        return nextRoundCellsAlive;
    }

    Set<Cell> getAliveNeighbours(Cell cell) {
        return Arrays.stream(directions).map(d -> new Cell(cell.getX() + d[0], cell.getY() + d[1])).filter(c -> cellsAlive.contains(c)).collect(Collectors.toSet());
    }

    Set<Cell> getDeadNeighbours(Cell cell) {
        return Arrays.stream(directions).map(d -> new Cell(cell.getX() + d[0], cell.getY() + d[1])).filter(c -> !cellsAlive.contains(c)).collect(Collectors.toSet());
    }

    private void placeStartingCells() {
        cellsAlive.add(new Cell(0, 1));
        cellsAlive.add(new Cell(1, 2));
        cellsAlive.add(new Cell(2, 0));
        cellsAlive.add(new Cell(2, 1));
        cellsAlive.add(new Cell(2, 2));
        cellsAlive.add(new Cell(7, 1));
        cellsAlive.add(new Cell(7, 2));
        cellsAlive.add(new Cell(7, 3));
    }

    public Set<Cell> getCellsAlive() {
        return cellsAlive;
    }

    public void setCellsAlive(Set<Cell> cellsAlive) {
        this.cellsAlive = cellsAlive;
    }
}
