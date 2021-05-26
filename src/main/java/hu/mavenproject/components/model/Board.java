package hu.mavenproject.components.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.mavenproject.components.model.boardelements.*;
import org.tinylog.Logger;

/**
 * A {@link Class} that represents the game's gamearea, the {@link Board}.
 */
public final class Board {

    // #region attributes
    @JsonIgnore
    private int N = 10;
    @JsonIgnore
    private int M = 10;
    @JsonIgnore
    private Tile[][] map;
    private Player player;
    private char[][] charMap;
    /**
     * Default {@link java.lang.reflect.Constructor} for the {@link Board}.
     */
    public Board() {
        this.player = new Player(1, 1);
        this.map = new Tile[this.N][this.M];
    }

    // #endregion

    // #region constructors

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Board}.
     *
     * @param p A {@link Player} object.
     */
    public Board(final Player p) {
        this.player = p;
        this.map = new Tile[this.N][this.M];
    }

    /**
     * {@link java.lang.reflect.Constructor} for th {@link Board}.
     *
     * @param n An {@link Integer} used to set both dimensions of the {@link Board}.
     *          Must be positive!
     */
    public Board(final int n) {
        this.N = n;
        this.M = n;
        this.player = new Player(1, 1);
        this.map = new Tile[n][n];
    }

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Board}.
     *
     * @param n An {@link Integer} used to set both dimensions of the {@link Board}.
     *          Must be positive!
     * @param p A {@link Player} object, the starting player
     */
    public Board(final int n, final Player p) {
        this.N = n;
        this.M = n;
        this.player = p;
        this.map = new Tile[n][n];

    }

    /**
     * A {@link java.lang.reflect.Constructor} for the {@link Board}.
     *
     * @param n An {@link Integer}, the N parameter of the {@link Board} (the number
     *          of rows). Must be positive!
     * @param m An {@link Integer}, the M parameter of the {@link Board} (the number
     *          of columns). Must be positive!
     */
    public Board(final int n, final int m) {
        this.N = n;
        this.M = m;
        this.player = new Player(1, 1);
        this.map = new Tile[n][m];
    }

    /**
     * A {@link java.lang.reflect.Constructor} for the {@link Board}.
     *
     * @param n An {@link Integer}, the N parameter of the {@link Board} (number of
     *          rows). Must be positive!
     * @param m An {@link Integer}, the M parameter of the {@link Board} (number of
     *          columns). Must be positive!
     * @param p A {@link Player} object, the starting player.
     */
    public Board(final int n, final int m, final Player p) {
        this.N = n;
        this.M = m;
        this.map = new Tile[n][m];
        this.player = p;
    }

    /**
     * A {@link java.lang.reflect.Method} that generates a default {@link Tile} map
     * for the {@link Board}.
     */
    public void generateDefault() {
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                if (i == this.player.getY() && j == this.player.getX()) {
                    map[i][j] = new Start();
                    this.charMap[i][j] = 'O';
                } else if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    map[i][j] = new Wall();
                    this.charMap[i][j] = '#';
                } else if (i == this.N - 2 && j == this.M - 2) {
                    map[i][j] = new Target();
                    this.charMap[i][j] = '$';
                } else {
                    map[i][j] = new Ground();
                    this.charMap[i][j] = '.';
                }

            }

        }
    }

    // #endregion

    // #region methods

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Tile} of the map,
     * on the given coordinates.
     *
     * @param i An {@link Integer}, the row index.
     * @param j An {@link Integer}, the column index.
     * @return A {@link Tile}, that is
     */
    public Tile getTileAt(final int i, final int j) {
        return this.map[i][j];
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the 2D {@link Tile} map as a
     * {@link String}.
     *
     * @return A {@link String} representing the map.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                str += this.map[i][j];
            }
        }
        return str;
    }

    /**
     * A {@link java.lang.reflect.Method} that generates the 2D {@link Tile} array
     * map from a "D" {@link Character} array.
     *
     * @param map_ The 2D {@link Character} array that represents the map.
     * @throws IllegalArgumentException If the input contains an Illegal character
     *                                  (Not "#",".","O","$").
     */
    public void fromString(final char[][] map_) {
        this.N = map_.length;
        this.M = map_[0].length;
        this.map = new Tile[this.N][this.M];
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                switch (map_[i][j]) {
                    case '#':
                        this.map[i][j] = new Wall();
                        break;
                    case '.':
                        this.map[i][j] = new Ground();
                        break;
                    case 'O':
                        this.map[i][j] = new Start();
                        this.player.setStartX(j);
                        this.player.setStartY(i);
                        break;
                    case '$':
                        this.map[i][j] = new Target();
                        break;
                    default:
                        Logger.error("Invalid character: " + map_[i][j]);
                        throw new IllegalArgumentException("Invalid character: " + map_[i][j]);
                }
            }
        }
        this.charMap = map_;
    }

    /**
     * A {@link java.lang.reflect.Method} to regenerate the {@link Board} based on
     * the charmap.
     */
    public void resetMap() {
        this.map = new Tile[this.N][this.M];
        fromString(this.charMap);
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Board}'s
     * {@link Player} object.
     *
     * @return The current game's {@link Player} object.
     */
    public Player getPlayer() {
        return this.player;
    }

    // #endregion

    // #region getters/setters

    /**
     * A {@link java.lang.reflect.Method} to set the {@link Board}'s {@link Player}
     * object.
     *
     * @param player_ A {@link Player} object, the new player.
     */
    public void setPlayer(final Player player_) {
        this.player = player_;
    }

    /**
     * A {@link java.lang.reflect.Method} to set the {@link Board}'s charmap.
     *
     * @param charMap_ The 2D {@link Character} array format of the new map.
     */
    public void setCharMap(final char[][] charMap_) {
        this.charMap = charMap_;
        // "################.....#....###....#.#.#...#O..#.....#...##...#.....#..###....##.....##.....#..#.#.##..#.........##.#..#..#...###......#.....##.##......#..$#...#.#.##...###..........################"
    }

    /**
     * A {@link java.lang.reflect.Method} used to get the 2D {@link Tile} array,
     * that represents the game's map.
     *
     * @return The game's map in a 2D {@link Tile} array.
     */
    public Tile[][] getMap() {
        return this.map;
    }

    /**
     * Returns the number of the {@link Board}'s columns.
     *
     * @return An {@link Integer} that represents the number of the board's columns.
     */
    public int getM() {
        return this.M;
    }

    /**
     * Returns the number of the {@link Board}'s rows.
     *
     * @return An {@link Integer} that represents the number of the board's rows.
     */
    public int getN() {
        return this.N;
    }

    /**
     * A {@link Class} that represents the directions for the game.
     */
    public final static class DIRECTION {
        /**
         * An {@link Integer} that represents a Direction.
         */
        public static final int NONE = 0;
        /**
         * An {@link Integer} that represents a Direction.
         */
        public static final int UP = 1;
        /**
         * An {@link Integer} that represents a Direction.
         */
        public static final int LEFT = 2;
        /**
         * An {@link Integer} that represents a Direction.
         */
        public static final int DOWN = 3;
        /**
         * An {@link Integer} that represents a Direction.
         */
        public static final int RIGHT = 4;
    }
    // #endregion
}
