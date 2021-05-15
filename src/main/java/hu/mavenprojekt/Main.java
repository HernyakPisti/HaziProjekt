package hu.mavenprojekt;

/**
 * A {@link Class} that is the entry point of the program. Used to avoid the
 * javafx promblem when the program is launched as a jar.
 */
public final class Main {

    /**
     * The programs entry point.
     *
     * @param args The program's arguments.
     */
    public static void main(final String[] args) {
        App.main(args);
    }
}
