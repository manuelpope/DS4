package edu.uoc.mecm.eda.pac4.exercise3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class that implements the AmongVous game
 *
 * @author Carles Pairot Gavaldà
 */
public class AmongVous {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    /**
     * Constant for representing a FREE block in the board
     */
    private static final int FREE = 0;
    /**
     * Constant for representing an OBSTACLE block in the board
     */
    private static final int OBSTACLE = 1;
    /**
     * Constant for representing the START position in the board
     */
    private static final int START = 2;
    /**
     * Constant for representing any HUMAN player's position in the board
     */
    private static final int HUMAN = 3;
    private BoardBuilder boardBuilder;

    /**
     * Constructs the game map from an input file
     *
     * @param gameMap File containing the game map
     * @throws Exception the exception
     */
    public AmongVous(File gameMap) throws Exception {
        StringBuilder fileText = new StringBuilder();
        try (Scanner input = new Scanner(gameMap)) {
            if (!input.hasNextLine()) {
                throw new IllegalArgumentException("not valid text file");
            }
            while (input.hasNextLine()) {
                String nextLine = input.nextLine();
                if (nextLine.isEmpty() || !nextLine.matches("[PSX_]*")) {
                    throw new IllegalArgumentException("not valid char in text");
                }
                fileText.append(nextLine).append("\n");
            }
        }
        this.boardBuilder = new BoardBuilder(fileText.toString());
    }

    /**
     * Get Maze height
     *
     * @return Maze height
     */
    public int getHeight() {
        return boardBuilder.getHeight();
    }

    /**
     * Get Maze width
     *
     * @return Maze width
     */
    public int getWidth() {
        return this.boardBuilder.getWidth();
    }

    /**
     * Method to find the distance to the nearest human player
     *
     * @return A list of coordinates for solving the Maze
     */
    public List<Position> distanceToHumanPlayer() {
        return solve(this.boardBuilder);
    }

    private List<Position> solve(BoardBuilder maze) {
        LinkedList<Position> nextToVisit = new LinkedList<>();
        Position start = maze.getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Position cur = nextToVisit.remove();

            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if (maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            if (maze.isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Position coordinate = new Position(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                // if(maze.isValidLocation(coordinate.getX(),coordinate.getY())&&!maze.isWall(coordinate.getX(),coordinate.getY()) &&!maze.isExplored(coordinate.getX(),coordinate.getY())){

                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
                //   }

            }
        }
        return Collections.emptyList();
    }

    private List<Position> backtrackPath(Position cur) {
        List<Position> path = new ArrayList<>();
        Position iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.getParent();
        }

        return path;
    }

    /**
     * Method for getting this game's found path
     *
     * @param path Found path
     * @return This game's found path as a String
     */
    public String getPath(List<Position> path) {

        int[][] solvedBoard = this.boardBuilder.getMaze();
        for (Position pos : path) {
            if (!this.boardBuilder.isStart(pos.getX(), pos.getY()) && !this.boardBuilder.isExit(pos.getX(), pos.getY())) {
                solvedBoard[pos.getX()][pos.getY()] = 5;
            }

        }

        return toString(solvedBoard);
    }

    /**
     * Method for printing this board
     *
     * @param board board to print
     * @return String representing the board
     */
    public String toString(int[][] board) {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (board[row][col] == FREE) {
                    result.append('_');
                } else if (board[row][col] == OBSTACLE) {
                    result.append('X');
                } else if (board[row][col] == START) {
                    result.append('S');
                } else if (board[row][col] == HUMAN) {
                    result.append('P');
                } else {
                    result.append('·');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
}