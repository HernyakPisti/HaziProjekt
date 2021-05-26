package hu.mavenproject.components.model;

import hu.mavenproject.components.model.Board.DIRECTION;

/**
 * A {@link Class} that represents the game's {@link Player} object.
 */
public final class Player {

    // #region attributes

    private int x;
    private int y;
    private int startX;
    private int startY;
    private int currentDirection;
    private final int startDirection;
    private int score;
    private String name;

    // #endregion

    // #region constructors

    /**
     * Default {@link java.lang.reflect.Constructor} for the {@link Player}.
     */
    public Player() {
        this.x = 1;
        this.y = 1;
        this.startX = 1;
        this.startY = 1;
        this.currentDirection = DIRECTION.NONE;
        this.startDirection = DIRECTION.NONE;
        this.score = 0;
    }

    /**
     * {@link java.lang.reflect.Constructor} for {@link Player}.
     *
     * @param x_    An {@link Integer}, the X coordinate.
     * @param y_    An {@link Integer}, the Y coordinate.
     * @param name_ A {@link String}, the player's name.
     */
    public Player(final int x_, final int y_, final String name_) {
        this.x = x_;
        this.y = y_;
        this.startX = x_;
        this.startY = y_;
        this.currentDirection = DIRECTION.NONE;
        this.startDirection = DIRECTION.NONE;
        this.name = name_;
        this.score = 0;
    }

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Player}.
     *
     * @param x_ An {@link Integer}, the {@link Player}'s X coordinate. Must be
     *           positive or 0!
     * @param y_ An {@link Integer}, the {@link Player}'s Y coordinate. Must be
     *           positive or 0!
     */
    public Player(final int x_, final int y_) {
        this.x = x_;
        this.y = y_;
        this.startX = x_;
        this.startY = y_;
        this.currentDirection = DIRECTION.NONE;
        this.startDirection = DIRECTION.NONE;
        this.name = "";
        this.score = 0;
    }

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Player}.
     *
     * @param x_                An {@link Integer}, the {@link Player}'s X
     *                          coordinate. Must be positive or 0!
     * @param y_                An {@link Integer}, the {@link Player}'s Y
     *                          coordinate. Must be positive or 0!
     * @param name_             A {@link String}, the {@link Player}'s name.
     * @param currentDirection_ An {@link Integer} that represents the
     *                          {@link Player} starting direction.
     */
    public Player(final int x_, final int y_, final String name_, final int currentDirection_) {

        this.x = x_;
        this.y = y_;
        this.startX = x_;
        this.startY = y_;
        this.currentDirection = currentDirection_;
        this.startDirection = currentDirection_;
        this.score = 0;
        this.name = name_;
    }

    // #endregion

    // #region methods

    /**
     * A {@link java.lang.reflect.Method} that retruns a {@link String} that
     * represents the {@link Player}.
     *
     * @return A {@link String} that represents the {@link Player}.
     */
    @Override
    public String toString() {
        return "X";
    }

    /**
     * A {@link java.lang.reflect.Method} that is used to reset the {@link Player}'s
     * current parameters to its starting ones.
     */
    public void reset() {
        this.x = this.startX;
        this.y = this.startY;
        this.currentDirection = this.startDirection;
        this.score = 0;
    }

    /**
     * The {@link java.lang.reflect.Method} that is responsible for the
     * {@link Player}'s moving.
     *
     * @param x_      An {@link Integer}, the amount of units the player wants to
     *                move on the X-axis. Can be negative!
     * @param y_      An {@link Integer}, the amount of units the player wants to
     *                move on the Y-axis. Can be negative!
     * @param heading A 1 character long or empty {@link String} that represents the
     *                move's direction ("a","s","d","w" or "").
     */
    public void move(final int x_, final int y_, final int heading) {
        this.x += x_;
        this.y += y_;
        this.currentDirection = heading;
        this.score++;
    }
    // #endregion

    // #region getters/setters

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player}'s X
     * current coordinate.
     *
     * @return An {@link Integer} that represents the {@link Player}'s current X
     * coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s current X
     * coordinate.
     *
     * @param x_ An {@link Integer}, the desired X coordinate. Must be positive or
     *           0!
     */
    public void setX(final int x_) {
        this.x = x_;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player}'s current
     * Y coordinate.
     *
     * @return An {@link Integer}, the {@link Player}'s current Y coordinate.
     */
    public int getY() {
        return this.y;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s current Y
     * coordinate.
     *
     * @param y_ An {@link Integer}, the desired Y coordinate. Must be positive or
     *           0!
     */
    public void setY(final int y_) {
        this.y = y_;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player}'s current
     * direction.
     *
     * @return A {@link Integer} that represents the {@link Player}'s current
     * direction.
     */
    public int getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s starting X
     * coordinate.
     *
     * @param startX_ An {@link Integer}, the desired X coordinate. Must be positive
     *                or 0!
     */
    public void setStartX(final int startX_) {
        this.startX = startX_;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s starting Y
     * coordinate.
     *
     * @param startY_ An {@link Integer}, the desired Y coordinate. Must be positive
     *                or 0!
     */
    public void setStartY(final int startY_) {
        this.startY = startY_;

    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player} object's
     * score.
     *
     * @return An {@link Integer}, the {@link Player}'s score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * A {@link java.lang.reflect.Method} to get the {@link Player}'s name.
     *
     * @return  An {@link String}, the {@link Player}'s name
     */
    public String getName() {
        return this.name;
    }

    /**
     * A {@link java.lang.reflect.Method} to set the {@link Player}'s name.
     *
     * @param name_ The {@link Player}'s name as a {@link String}.
     */
    public void setName(String name_) {
        this.name = name_;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns an {@link Integer}
     * representing the {@link Player}'s staring direction.
     *
     * @return An {@link Integer}, the {@link Player}'s starting direction.
     */
    public int getStartDirection() {
        return this.startDirection;
    }
    // #endregion
}
