package hu.mavenprojekt.Components.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Boardelements.Target;
import hu.mavenprojekt.Components.Model.Player;
import hu.mavenprojekt.Components.View.BoardGUI;
import hu.mavenprojekt.Components.View.GUI;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;


public final class BoardController {

    private GUI boardGUI = null;
    private Board board = null;
    private int N = 10;
    private int M = 10;

    public BoardController(GUI boardGUI, int N, int M) {
        this.boardGUI = boardGUI;
        this.N = N;
        this.M = M;
    }

    public BoardController(GUI boardGUI, Board board) {
        this.boardGUI = boardGUI;
        this.N = board.getN();
        this.M = board.getM();
        this.board = board;
    }

    public Board generateDefaultBoard(Board b) {
        b.generateDefault();
        this.board = b;
        return this.board;
    }

    public Board generateDefaultBoard() {
        this.board = new Board(this.N, this.M);
        this.board.generateDefault();
        return this.board;
    }

    public void Save(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, this.board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String move(KeyEvent e) {

        String h = null;
        String c = "";
        if (e.getCode() == KeyCode.A) {
            c = "a";
        } else if (e.getCode() == KeyCode.S) {
            c = "s";
        } else if (e.getCode() == KeyCode.D) {
            c = "d";
        } else if (e.getCode() == KeyCode.W) {
            c = "w";
        }

        if (((BoardGUI) this.boardGUI).getPlayerController().move(this.board, c))
            h = c;
        ((BoardGUI) boardGUI).draw();
        ((BoardGUI) boardGUI).drawConsole();
        if (checkWin()) {
            Logger.info("Game finished");
            reset(true);
            h = "";
        }

        return h;

    }

    public Board getBoard() {
        return this.board;
    }

    public void reset() {
        this.board.getPlayer().reset();
        ((BoardGUI) this.boardGUI).draw();
        ((BoardGUI) this.boardGUI).drawConsole();
    }

    public void reset(boolean newgame) {
        this.board.getPlayer().reset();
        if (newgame) {
            this.board.getPlayer().reset(newgame, this.board.getN(), this.board.getM());
            generateDefaultBoard(this.board);
        }
        ((BoardGUI) this.boardGUI).draw();
        ((BoardGUI) this.boardGUI).drawConsole();
    }

    private boolean checkWin() {
        return this.board.getMap()[this.board.getPlayer().getY()][this.board.getPlayer().getX()] instanceof Target;
    }

    public Player getPlayer() {
        return this.board.getPlayer();
    }
}
