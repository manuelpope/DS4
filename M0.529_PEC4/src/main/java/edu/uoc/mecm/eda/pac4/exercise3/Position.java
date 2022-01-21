package edu.uoc.mecm.eda.pac4.exercise3;

import java.awt.*;

/**
 * This class implements a 2D point in space and also stores the parent's coordinate
 *
 * @author Carles Pairot Gavaldà
 */
public class Position {

    /**
     * Current 2D Point
     */
    private Point current;

    /**
     * Parent 2D Point
     */
    private Position parent;

    /**
     * Constructs a Position object from an (x, y) point
     *
     * @param x X position
     * @param y Y position
     */
    public Position(int x, int y) {
        this.current = new Point(x, y);
        this.parent = null;
    }

    /**
     * Constructs a Position object from an (x, y) point and a parent
     *
     * @param x      X position
     * @param y      Y position
     * @param parent Parent coordinate
     */
    public Position(int x, int y, Position parent) {
        this.current = new Point(x, y);
        this.parent = parent;
    }

    /**
     * Returns this coordinate's X position
     *
     * @return X position
     */
    public int getX() {
        return (int) current.getX();
    }

    /**
     * Returns this coordinate's Y position
     *
     * @return Y position
     */
    public int getY() {
        return (int) current.getY();
    }

    /**
     * Return this coordinate's parent coordinate
     *
     * @return Parent 's coordinate
     */
    public Position getParent() {
        return parent;
    }
}