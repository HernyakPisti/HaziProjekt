package hu.mavenprojekt.Components.Model;

import java.util.Random;

public final class Player {


    private int x;
    private int y;
    private int startX;
    private int startY;
    private String currentHeading;
    private String startHeading;



    public Player() {
        this.x = 1;
        this.y = 1;
        this.startX = 1;
        this.startY = 1;
        this.currentHeading = "";
        this.startHeading = "";
    }

    public Player(int x_, int y_) {
        this.x = x_;
        this.y = y_;
        this.startX = x_;
        this.startY = y_;
        this.currentHeading = "";
        this.startHeading = "";
    }

    public Player(int x_, int y_, String currentHeading_) {
        this.x = x_;
        this.y = y_;
        this.startX = x_;
        this.startY = y_;
        this.currentHeading = currentHeading_;
        this.startHeading = currentHeading_;
    }



    public int getX() {
        return this.x;
    }

    public void setX(int x_) {
        this.x = x_;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y_) {
        this.y = y_;
    }

    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int startX_) {
        this.startX = startX_;
    }

    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int startY_) {
        this.startY = startY_;
    }

    public String getCurrentHeading() {
        return this.currentHeading;
    }

    public void setCurrentHeading(String currentHeading_) {
        this.currentHeading = currentHeading_;
    }

    public String getStartHeading() {
        return this.startHeading;
    }

    public void setStartHeading(String startHeading_) {
        this.startHeading = startHeading_;
    }



    @Override
    public String toString() {
        return "X";
    }

    public void reset() {
        this.x = this.startX;
        this.y = this.startY;
        this.currentHeading = this.startHeading;
    }

    public void reset(boolean newgame, int N, int M) {
        if (newgame) {
            int i, j;
            Random r = new Random();
            do {
                i = r.nextInt(N);
                j = r.nextInt(M);
            } while ((i == 0 && j == 0) || (i == 0 && j == M) || (i == N && j == 0) || (i == N && j == M));
            this.x = j;
            this.y = i;
            this.startX = this.x;
            this.startY = this.y;
            this.currentHeading = "";
        } else {
            this.x = this.startX;
            this.y = this.startY;
            this.currentHeading = this.startHeading;
        }
    }

    public void move(int x_, int y_, String heading) {
        this.x += x_;
        this.y += y_;
        this.currentHeading = heading;
    }


}
