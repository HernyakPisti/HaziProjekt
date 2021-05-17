package hu.mavenprojekt.Components.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.mavenprojekt.Components.Model.Boardelements.*;
import org.tinylog.Logger;

import java.util.Random;

/**
 * A {@link Class} that represents the game's gamearea, the {@link Board}.
 */
public final class Board {

    private int N = 10;
    private int M = 10;
    @JsonIgnore
    private Tile[][] map;
    private Player player;
    private String mapString;

    /**
     * Default {@link java.lang.reflect.Constructor} for the {@link Board}.
     */
    public Board() {
        this.player = new Player(1, 1);
        this.map = new Tile[this.N][this.M];
    }

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
        if (n > 0) {
            this.N = n;
            this.M = n;
            this.player = new Player(1, 1);
            this.map = new Tile[n][n];
        } else {
            Logger.error("\"n\" must be greater than 0. Got: " + n);
        }
    }

    /**
     * {@link java.lang.reflect.Constructor} for the {@link Board}.
     *
     * @param n An {@link Integer} used to set both dimensions of the {@link Board}.
     *          Must be positive!
     * @param p A {@link Player} object, the starting player
     */
    public Board(final int n, final Player p) {
        if (n > 0) {
            this.N = n;
            this.M = n;
            this.player = p;
            this.map = new Tile[n][n];
        } else {
            Logger.error("\"n\" must be greater than 0. Got: " + n);
        }
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
        if (n > 0 && m > 0) {
            this.N = n;
            this.M = m;
            this.player = new Player(1, 1);
            this.map = new Tile[n][m];
        } else {
            Logger.error("\"n\" and \"m\" must be greater than 0. Got n: " + n + ", m: " + m);
        }
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
        if (n > 0 && m > 0) {
            this.N = n;
            this.M = m;
            this.map = new Tile[n][m];
            this.player = p;
        } else {
            Logger.error("\"n\" and \"m\" must be greater than 0. Got n: " + n + ", m: " + m);
        }
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
     * A {@link java.lang.reflect.Method} to set the {@link Board}'s N parameter
     * (number of rows).
     *
     * @param n An {@link Integer}, the number of rows. Must be positive!
     */
    public void setN(final int n) {
        if (N > 0) {
            this.N = n;
        } else {
            Logger.error("\"n\" must be greater than 0. Got: " + n);
        }
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
     * A {@link java.lang.reflect.Method} to set the {@link Board}'s M parameter
     * (number of columns).
     *
     * @param m An {@link Integer}, the number of columns. Must be positive!
     */
    public void setM(final int m) {
        if (M > 0) {
            this.M = m;
        } else {
            Logger.error("\"m\" must be greater than 0. Got: " + m);
        }
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
     * A {@link java.lang.reflect.Method} used to set the 2D {@link Tile} array,
     * that represents the game's map.
     *
     * @param map_ A 2D {@link Tile} array, the new map.
     */
    public void setMap(final Tile[][] map_) {
        this.map = map_;
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
     * A {@link java.lang.reflect.Method} that returns the {@link Board}'s map in a
     * {@link String} format.
     *
     * @return A {@link String} that represents the 2D {@link Tile} array of the
     * {@link Board}'s map as a {@link String}.
     */
    public String getMapString() {
        this.mapString = toString();
        return this.mapString;
    }

    /**
     * A {@link java.lang.reflect.Method} to set the {@link Board}'s mapString and
     * with that the 2D {@link Tile} array of the map.
     *
     * @param mapString_ The {@link String} format of the new map.
     */
    public void setMapString(final String mapString_) {
        this.mapString = mapString_;
        fromString(this.mapString);
    }

    /**
     * A {@link java.lang.reflect.Method} that generates a random {@link Tile} map
     * for the {@link Board}, depending on the dimensions.
     */
    public void generateDefault() {
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                if (i == this.player.getStartY() && j == this.player.getStartX()) {
                    map[i][j] = new Start();
                } else if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    map[i][j] = new Wall();
                } else {
                    map[i][j] = new Ground();
                }

            }

        }
        int i;
        int j;
        Random r = new Random();


        do {
            i = r.nextInt(this.N);
            j = r.nextInt(this.M);
        } while (this.map[i][j] instanceof Start || (i == 0 && j == 0) || (i == N - 1 && j == 0)
                || (i == N - 1 && j == M - 1) || (i == 0 && j == M - 1));
        this.map[i][j] = new Target();
    }

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
     * map from a {@link String}.
     *
     * @param map_ The {@link String} that represents the map. Must be the same
     *             length as the {@link Board}'s dimensions (N*M).
     */
    public void fromString(final String map_) {
        if (map_.length() == this.N * this.M) {
            int startcount = 0;
            int endcount = 0;
            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    switch (map_.charAt(i * this.M + j)) {
                        case '#':
                            this.map[i][j] = new Wall();
                            break;
                        case '.':
                            this.map[i][j] = new Ground();
                            break;
                        case 'O':
                            if (startcount > 1) {
                                return;
                            }
                            this.map[i][j] = new Start();
                            this.player.setStartX(j);
                            this.player.setStartY(i);
                            startcount++;
                            break;
                        case '$':
                            if (endcount > 1) {
                                return;
                            }
                            this.map[i][j] = new Target();
                            endcount++;
                            break;
                        default:
                            break;
                    }
                }
            }

        } else {
            Logger.error("The map lenght must be equals to " + (this.N * this.M) + ". Got: " + map_.length());
        }
    }

    /**
     * A {@link java.lang.reflect.Method} to regenerate the {@link Board} based on
     * the mapstring.
     */
    public void resetMap() {
        this.map = new Tile[this.N][this.M];
        fromString(this.mapString);
    }

}
