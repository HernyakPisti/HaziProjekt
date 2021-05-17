package hu.mavenprojekt.Components.Model;

import org.tinylog.Logger;

import java.util.Random;

/**
 * A {@link Class} that represents the game's {@link Player} object.
 */
public final class Player {

    // #region attributes

    private int x;
    private int y;
    private int startX;
    private int startY;
    private String currentHeading;
    private String startHeading;

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
        this.currentHeading = "";
        this.startHeading = "";
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
        if (x_ >= 0 && y_ >= 0) {
            this.x = x_;
            this.y = y_;
            this.startX = x_;
            this.startY = y_;
            this.currentHeading = "";
            this.startHeading = "";
        } else {
            Logger.error("\"x\" and \"y\" must be greater or equals to 0. Got x: " + x_ + ", y: " + y_);
        }
    }

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Player}.
     *
     * @param x_              An {@link Integer}, the {@link Player}'s X coordinate.
     *                        Must be positive or 0!
     * @param y_              An {@link Integer}, the {@link Player}'s Y coordinate.
     *                        Must be positive or 0!
     * @param currentHeading_ A 1 character long {@link String} that represents the
     *                        {@link Player} starting direction ("a","s","d","w").
     */
    public Player(final int x_, final int y_, final String currentHeading_) {
        if (y_ >= 0 && y_ >= 0 && currentHeading_.trim().length() == 1) {
            this.x = x_;
            this.y = y_;
            this.startX = x_;
            this.startY = y_;
            this.currentHeading = currentHeading_.trim();
            this.startHeading = currentHeading_.trim();
        } else {
            Logger.error(
                    "\"x\" and \"y\" must be greater or equals to 0 and \"currentHeading\"s lenghts must be 1 character. Got x: "
                            + x_ + ", y: " + y_ + ", lenght: " + currentHeading_.length());
        }
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
        if (x_ >= 0) {
            this.x = x_;
        } else {
            Logger.error("\"x\"  must be greater or equals to 0. Got: " + x_);
        }
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
        if (y_ >= 0) {
            this.y = y_;
        } else {
            Logger.error("\"y\" must be greater or equals to 0. Got: " + y_);
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player}'s starting
     * X coordinate.
     *
     * @return An {@link Integer}, the {@link Player}'s starting Y coordinate.
     */
    public int getStartX() {
        return this.startX;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s starting X
     * coordinate.
     *
     * @param startX_ An {@link Integer}, the desired X coordinate. Must be positive
     *                or 0!
     */
    public void setStartX(final int startX_) {
        if (startX_ >= 0) {
            this.startX = startX_;
        } else {
            Logger.error("\"startX\" must be greater or equals to 0. Got: " + startX_);
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player}'s starting
     * Y coordinate.
     *
     * @return An {@link Integer}, the {@link Player}'s starting Y coordinate.
     */
    public int getStartY() {
        return this.startY;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s starting Y
     * coordinate.
     *
     * @param startY_ An {@link Integer}, the desired Y coordinate. Must be positive
     *                or 0!
     */
    public void setStartY(final int startY_) {
        if (startY_ >= 0) {
            this.startY = startY_;
        } else {
            Logger.error(" \"startY\" must be greater or equals to 0. Got: " + startY_);
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Player}'s current
     * direction.
     *
     * @return A {@link String} that represents the {@link Player}'s current
     * direction.
     */
    public String getCurrentHeading() {
        return this.currentHeading;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Player}'s current
     * direction.
     *
     * @param currentHeading_ The desired direction, a 1 character long or empty
     *                        {@link String} ("a","s","d","w" or "").
     */
    public void setCurrentHeading(final String currentHeading_) {
        if (currentHeading_.trim().length() < 2) {
            this.currentHeading = currentHeading_.trim();
        } else {
            Logger.error("\"currentHeading\"'s lenght must be 0 or 1. Got: " + currentHeading_.trim().length());
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that returns a {@link String} representing
     * the {@link Player}'s staring direction.
     *
     * @return A {@link String}, the {@link Player}'s starting direction.
     */
    public String getStartHeading() {
        return this.startHeading;
    }

    /**
     * A {@link java.lang.reflect.Method} to set the {@link Player}'s starting
     * direction.
     *
     * @param startHeading_ The desired direction, a 1 character long or empty
     *                      {@link String} ("a","s","d","w" or "").
     */
    public void setStartHeading(final String startHeading_) {
        if (startHeading_.trim().length() < 2) {
            this.startHeading = startHeading_;
        } else {
            Logger.error("\"startHeading\"'s lenght must be 0 or 1. Got: " + startHeading_.trim().length());
        }
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
        this.currentHeading = this.startHeading;
    }

    /**
     * A {@link java.lang.reflect.Method} that resets the {@link Player}'s
     * parameters to its starting ones or rerandomize them.
     *
     * @param newgame A {@link Boolean} that decides if the {@link Player} is
     *                rerandomized or not.
     * @param N       An {@link Integer}, the {@link Board}'s N parameter (number of
     *                rows). Must be positive!
     * @param M       An {@link Integer}, the {@link Board}'s M parameter (number of
     *                columns). Must be positive!
     */
    public void reset(final boolean newgame, final int N, final int M) {
        if (N > 0 && M > 0) {
            if (newgame) {
                int i, j;
                Random r = new Random();
                do {
                    i = r.nextInt(N);
                    j = r.nextInt(M);
                } while ((i == 0 && j == 0) || (i == 0 && j == M) || (i == N && j == 0) || (i == N && j == M));
                this.x = j;
                this.y = i;
                this.startX = this.x;
                this.startY = this.y;
                this.currentHeading = "";
            } else {
                this.x = this.startX;
                this.y = this.startY;
                this.currentHeading = this.startHeading;
            }
        } else {
            Logger.error("\"N\" and \"M\" must be greater than 0. Got N: " + N + ", M: " + M);
        }
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
    public void move(final int x_, final int y_, final String heading) {
        this.x += x_;
        this.y += y_;
        this.currentHeading = heading;
    }
    // #endregion

}
