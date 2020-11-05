package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class GameOfLifeTest {

    @Test
    void testGetAliveNeighbours() {
        GameOfLife gameOfLife = new GameOfLife();
        Set<Cell> cellSet = new HashSet<>();
        cellSet.add(new Cell(3, 3));
        cellSet.add(new Cell(3, 4));
        gameOfLife.setCellsAlive(cellSet);
        Assertions.assertEquals(2, gameOfLife.getAliveNeighbours(new Cell(4, 3)).size());
    }

    @Test
    void testGetDeadNeighbours() {
        GameOfLife gameOfLife = new GameOfLife();
        Set<Cell> cellSet = new HashSet<>();
        cellSet.add(new Cell(3, 3));
        cellSet.add(new Cell(3, 4));
        gameOfLife.setCellsAlive(cellSet);
        Assertions.assertEquals(6, gameOfLife.getDeadNeighbours(new Cell(4, 3)).size());
    }

    @Test
    void testGenerationChanged() {
        GameOfLife gameOfLife = new GameOfLife();
        Set<Cell> cellSet = new HashSet<>();
        cellSet.add(new Cell(3, 3));
        cellSet.add(new Cell(3, 4));
        gameOfLife.setCellsAlive(cellSet);

        Set<Cell> nextGeneration = gameOfLife.getNextGeneration();
        gameOfLife.setCellsAlive(nextGeneration);

        Assertions.assertTrue(gameOfLife.isGenerationChanged(cellSet));
    }

    @Test
    void testGenerationNotChanged() {
        GameOfLife gameOfLife = new GameOfLife();
        Set<Cell> cellSet = new HashSet<>();
        cellSet.add(new Cell(3, 3));
        cellSet.add(new Cell(3, 4));
        gameOfLife.setCellsAlive(cellSet);

        Set<Cell> sameCellSet = new HashSet<>();
        sameCellSet.add(new Cell(3, 3));
        sameCellSet.add(new Cell(3, 4));

        Assertions.assertFalse(gameOfLife.isGenerationChanged(sameCellSet));
    }

}
