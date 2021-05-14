package hu.mavenprojekt.Components.Model;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hu.mavenprojekt.Components.Model.Boardelements.Ground;
import hu.mavenprojekt.Components.Model.Boardelements.Start;
import hu.mavenprojekt.Components.Model.Boardelements.Target;
import hu.mavenprojekt.Components.Model.Boardelements.Tile;
import hu.mavenprojekt.Components.Model.Boardelements.Wall;

public final class Board {

    // #region attributes

    private int N = 10;
    private int M = 10;
    // @JsonIgnore
    private Tile[][] map;
    private Player player;
    private String mapString;

    // #endregion

    // #region getters/setters

    public int getN() {
        return this.N;
    }

    public void setN(int N_) {
        this.N = N_;
    }

    public int getM() {
        return this.M;
    }

    public void setM(int M_) {
        this.M = M_;
    }

    public Tile[][] getMap() {
        return this.map;
    }

    public void setMap(Tile[][] map_) {
        this.map = map_;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player_) {
        this.player = player_;
    }

    public String getMapString() {
        this.mapString = toString();
        return this.mapString;
    }

    public void setMapString(String mapString_) {
        this.mapString = mapString_;
    }

    // #endregion

    // #region constructors

    public Board() {
        this.player = new Player(1, 1);
        this.map = new Tile[this.N][this.M];
    }

    public Board(Player p) {
        this.player = p;
        this.map = new Tile[this.N][this.M];
    }

    public Board(int n) {
        this.N = n;
        this.M = n;
        this.player = new Player(1, 1);
        this.map = new Tile[n][n];
    }

    public Board(int n, Player p) {
        this.N = n;
        this.M = n;
        this.player = p;
        this.map = new Tile[n][n];
    }

    public Board(int n, int m) {
        this.N = n;
        this.M = m;
        this.player = new Player(1, 1);
        this.map = new Tile[n][m];
    }

    public Board(int n, int m, Player p) {
        this.N = n;
        this.M = m;
        this.map = new Tile[n][m];
        this.player = p;
    }

    // #endregion

    // #region methods

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

    public Tile getTileAt(int i, int j) {
        return this.map[i][j];
    }

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

    public void fromString(String map) {
        if (map.length() == this.N * this.M) {
            int startcount = 0;
            int endcount = 0;
            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    switch (map.charAt(i * this.M + j)) {
                        case '#':
                            this.map[i][j] = new Wall();
                            break;
                        case '.':
                            this.map[i][j] = new Ground();
                            break;
                        case 'O':
                            if (startcount > 1)
                                return;
                            this.map[i][j] = new Start();
                            this.player.setStartX(j);
                            this.player.setStartY(i);
                            startcount++;
                            break;
                        case '$':
                            if (endcount > 1)
                                return;
                            this.map[i][j] = new Target();
                            endcount++;
                            break;
                        default:
                            break;
                    }
                }
            }

        }
    }

    public void resetMap() {
        this.map = new Tile[this.N][this.M];
        fromString(this.mapString);
    }

    // #endregion

}
