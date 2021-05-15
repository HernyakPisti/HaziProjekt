package hu.mavenprojekt.Components.Model.Boardelements;

/**
 * A {@link Class} that represents a {@link Ground} block on the
 * {@link hu.mavenprojekt.Components.Model.Board}.
 */
public final class Ground implements Tile {

    /**
     * A method that represents the {@link Ground} {@link Tile} as a {@link String}.
     *
     * @return A {@link String} that represents the {@link Ground} {@link Tile}.
     */
    @Override
    public String toString() {
        return ".";
    }
}
