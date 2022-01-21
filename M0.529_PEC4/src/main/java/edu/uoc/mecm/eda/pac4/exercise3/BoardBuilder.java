package edu.uoc.mecm.eda.pac4.exercise3;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Board builder.
 */
public class BoardBuilder {


    private static final int FREE = 0;
    private static final int OBSTACLE = 1;
    private static final int START = 2;
    private static final int HUMAN = 3;
    private String textBoard;
    private int[][] maze;
    private boolean[][] visited;
    private Position start;
    private List<Position> end = new ArrayList<>();


    /**
     * Instantiates a new Board builder.
     *
     * @param textBoard the text board
     */
    public BoardBuilder(String textBoard) {
        this.textBoard = textBoard;
        initializeMaze(this.textBoard);
    }

    /**
     * Get maze int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getMaze() {
        return maze;
    }

    private void initializeMaze(String text) {
        if (text == null || (text = text.trim()).length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }

        String[] lines = text.split("[\r]?\n");
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() + " but should be " + getWidth() + ")");
            }

            for (int col = 0; col < getWidth(); col++) {
                if (lines[row].charAt(col) == 'X')
                    maze[row][col] = OBSTACLE;
                else if (lines[row].charAt(col) == 'S') {
                    maze[row][col] = START;
                    start = new Position(row, col);
                } else if (lines[row].charAt(col) == 'P') {
                    maze[row][col] = HUMAN;
                    end.add(new Position(row, col));
                } else if (lines[row].charAt(col) == '_') {
                    maze[row][col] = FREE;
                }
            }
        }
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return maze.length;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return maze[0].length;
    }

    /**
     * Gets entry.
     *
     * @return the entry
     */
    public Position getEntry() {
        return start;
    }

    /**
     * Gets exit.
     *
     * @return the exit
     */
    public List<Position> getExit() {
        return end;
    }

    /**
     * Is exit boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isExit(int x, int y) {
        boolean flag = false;
        for (Position pos : end) {
            if (pos.getX() == x && pos.getY() == y) flag = true;
        }
        return flag;
    }

    /**
     * Is start boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    /**
     * Is explored boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    /**
     * Is wall boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isWall(int row, int col) {
        return maze[row][col] == OBSTACLE;
    }

    /**
     * Sets visited.
     *
     * @param row   the row
     * @param col   the col
     * @param value the value
     */
    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    /**
     * Is valid location boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }


}
