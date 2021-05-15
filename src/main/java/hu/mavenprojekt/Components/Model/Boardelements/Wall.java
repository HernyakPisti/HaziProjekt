package hu.mavenprojekt.Components.Model.Boardelements;

/**
 * A {@link Class} that represents a {@link Wall} block on the
 * {@link hu.mavenprojekt.Components.Model.Board}.
 */
public final class Wall implements Tile {

    /**
     * A method that represents the {@link Wall} {@link Tile} as a {@link String}.
     *
     * @return A {@link String} that represents the {@link Wall} {@link Tile}.
     */
    @Override
    public String toString() {
        return "#";
    }
}
