package hu.mavenprojekt.Components.Controller;

import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Boardelements.Wall;
import hu.mavenprojekt.Components.View.GUI;
import org.tinylog.Logger;

public final class PlayerController {

    private GUI boardGUI = null;

    public PlayerController(GUI gui) {
        this.boardGUI = gui;
    }

    public boolean move(Board board, String c) {
        boolean flag = false;
        int x = 0, y = 0;
        switch (c) {
            case "a":
                x = -1;
                break;
            case "s":
                y = 1;
                break;
            case "d":
                x = 1;
                break;
            case "w":
                y = -1;
                break;
            default:
                break;
        }
        if (checkInput(board, x, y, c)) {
            board.getPlayer().move(x, y, c);
            flag = true;
            Logger.info("Moved to: "+(board.getPlayer().getX()+1)+" "+(board.getPlayer().getY()+1));
        }
        if(flag==false) Logger.info("Can't move");
        return flag;

    }

    private boolean checkInput(Board board, int x, int y, String c) {
        boolean flag = false;

        if (!(board.getMap()[board.getPlayer().getY() + y][board.getPlayer().getX() + x] instanceof Wall)) {

            if (board.getPlayer().getCurrentHeading().equals(c.trim())) {
                flag = true;
            } else {
                switch (board.getPlayer().getCurrentHeading()) {
                    case "":
                        flag = true;
                        break;
                    case "w":
                        if (c.equals("d"))
                            flag = true;
                        break;
                    case "a":
                        if (c.equals("w"))
                            flag = true;
                        break;
                    case "s":
                        if (c.equals("a"))
                            flag = true;
                        break;
                    case "d":
                        if (c.equals("s"))
                            flag = true;
                        break;
                    default:
                        break;
                }
            }
        }

        return flag;

    }

}
