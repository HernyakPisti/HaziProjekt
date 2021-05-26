package hu.mavenproject.components.model;

/**
 * The class that represents the result entries for the Leaderborad.
 */
public class Result {

    private String name;
    private int score;

    /**
     * Default {@link java.lang.reflect.Constructor} for the {@link Result}.
     */
    public Result() {
    }

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Result}.
     *
     * @param n A {@link String}, the player's name.
     * @param s An {@link Integer}, the player's score.
     */
    public Result(String n, int s) {
        this.name = n;
        this.score = s;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Result} in a
     * readable format.
     *
     * @return A {@link String}, the readable result.
     */
    public String toString() {
        return this.name + " - " + this.score;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Result}'s score
     * attribute. Used for a {@link java.util.Comparator} for the Leaderboard.
     *
     * @return An {@link Integer}, the score attribute.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Result}'s score
     * attribute.
     *
     * @param score_ An {@link Integer}, the player's score.
     */
    public void setScore(int score_) {
        this.score = score_;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Result}'s name
     * attribute.
     *
     * @return A {@link String}, the player's score.
     */
    public String getName() {
        return this.name;
    }

    /**
     * A {@link java.lang.reflect.Method} that sets the {@link Result}'s name
     * attribute.
     *
     * @param name_ A {@link String}, the player's name.
     */
    public void setName(String name_) {
        this.name = name_;
    }
}
