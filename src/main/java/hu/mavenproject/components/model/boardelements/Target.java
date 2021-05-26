package hu.mavenproject.components.model.boardelements;

/**
 * A {@link Class} that represents a {@link Target} block on the
 * {@link hu.mavenproject.components.model.Board}.
 */
public final class Target implements Tile {

    /**
     * A method that represents the {@link Target} {@link Tile} as a {@link String}.
     *
     * @return A {@link String} that represents the {@link Target} {@link Tile}.
     */
    @Override
    public String toString() {
        return "$";
    }
}
