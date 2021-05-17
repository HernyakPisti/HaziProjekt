package hu.mavenprojekt.Components.Model.Boardelements;

/**
 * An interface used for the boardelelments, so the game's map can be
 * represented as a 2D array of boardelements.
 */
public interface Tile {
    /**
     * The method that is used to represent the {@link Class}es that implements the
     * {@link Tile} interface as a {@link String}.
     *
     * @return A {@link String} that represents the {@link Class}.
     */
    String toString();
}
