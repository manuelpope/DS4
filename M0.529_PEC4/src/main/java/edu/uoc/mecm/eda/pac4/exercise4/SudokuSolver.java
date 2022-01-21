package edu.uoc.mecm.eda.pac4.exercise4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class for solving Sudoku puzzles using a Graph Coloring algorithm
 *
 * @author Carles Pairot Gavaldà
 */
public class SudokuSolver {
    private static int N = 9;
    // TODO: Add your code here
    private int[][] board;

    /**
     * Constructor for SudokuSolver
     *
     * @param sudokuMap File containing the Sudoku's initial map
     * @throws Exception the exception
     */
    public SudokuSolver(File sudokuMap) throws Exception {

        StringBuilder fileText = new StringBuilder();
        try (Scanner input = new Scanner(sudokuMap)) {
            if (!input.hasNextLine()) {
                throw new IllegalArgumentException("not valid text file");
            }

            while (input.hasNextLine()) {
                String nextLine = input.nextLine();
                if (nextLine.isEmpty() || !nextLine.matches("[.0123456789]*") || !(nextLine.length() == 9)) {
                    throw new IllegalArgumentException("not valid char in text");
                }
                fileText.append(nextLine).append("\n");
            }
            init(fileText.toString());
        }
    }

    /**
     * To string string.
     *
     * @param grid the grid
     * @return the string
     */
    /* A utility function to print grid */
    static String toString(int[][] grid) {
        StringBuilder fileText = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fileText.append(grid[i][j]);
                if (j < N - 1) {
                    fileText.append(" ");

                } else {
                    fileText.append("\n");
                }
            }
        }
        return fileText.toString();
    }

    /**
     * Is safe boolean.
     *
     * @param grid the grid
     * @param row  the row
     * @param col  the col
     * @param num  the num
     * @return the boolean
     */
    static boolean isSafe(int[][] grid, int row, int col,
                          int num) {


        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == num)
                return false;


        for (int x = 0; x <= 8; x++)
            if (grid[x][col] == num)
                return false;


        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    private void init(String text) {
        if (text == null || (text = text.trim()).length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }

        String[] lines = text.split("[\r]?\n");
        board = new int[lines.length][lines[0].length()];

        for (int row = 0; row < 9; row++) {
            if (lines[row].length() != 9) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() + " but should be " + 9 + ")");
            }

            for (int col = 0; col < 9; col++) {
                if (lines[row].charAt(col) == '.')
                    board[row][col] = 0;
                else {
                    board[row][col] = Character.getNumericValue(lines[row].charAt(col));


                }
            }
        }
    }

    /**
     * Get Sudoku's solution
     *
     * @return A String containing the Sudoku's solution
     */
    public String getSolution() {

        return toString(board);
    }

    /**
     * Method to solve the Sudoku puzzle
     *
     * @return true if Sudoku has been solved correctly
     */
    public boolean solveSudoku() {
        return solveSudoku(board, 0, 0);
    }

    /**
     * Solve sudoku boolean.
     *
     * @param grid the grid
     * @param row  the row
     * @param col  the col
     * @return the boolean
     */
    boolean solveSudoku(int grid[][], int row,
                        int col) {

        /*if we have reached the 8th
           row and 9th column (0
           indexed matrix) ,
           we are returning true to avoid further
           backtracking       */
        if (row == N - 1 && col == N)
            return true;

        // Check if column value  becomes 9 ,
        // we move to next row
        // and column start from 0
        if (col == N) {
            row++;
            col = 0;
        }

        // Check if the current position
        // of the grid already
        // contains value >0, we iterate
        // for next column
        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {

            // Check if it is safe to place
            // the num (1-9)  in the
            // given row ,col ->we move to next column
            if (isSafe(grid, row, col, num)) {

                /*  assigning the num in the current
                (row,col)  position of the grid and
                assuming our assigned num in the position
                is correct */
                grid[row][col] = num;

                // Checking for next
                // possibility with next column
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            /* removing the assigned num , since our
               assumption was wrong , and we go for next
               assumption with diff num value   */
            grid[row][col] = 0;
        }
        return false;
    }
}

