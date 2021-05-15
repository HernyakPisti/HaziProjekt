package hu.mavenprojekt.Components.Model.Boardelements;

/**
 * A {@link Class} that represents a {@link Start} block on the
 * {@link hu.mavenprojekt.Components.Model.Board}.
 */
public final class Start implements Tile {

    /**
     * A method that represents the {@link Start} {@link Tile} as a {@link String}.
     *
     * @return A {@link String} that represents the {@link Start} {@link Tile}.
     */
    @Override
    public String toString() {
        return "O";
    }
}
