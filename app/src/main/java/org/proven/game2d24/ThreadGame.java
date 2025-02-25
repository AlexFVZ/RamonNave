package org.proven.game2d24;

import android.widget.TextView;

public class ThreadGame extends Thread {
    GameView gameView;
    TextView puntuacio;

    public ThreadGame(GameView gameView,TextView puntuacio) {
        this.gameView = gameView;
        this.puntuacio = puntuacio;
    }
    boolean seguir=true;

    @Override
    public void run() {
        super.run();
        try {
            while (seguir) {
                sleep(50);
                gameView.move();
                gameView.collisions();
                gameView.postInvalidate();
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }
}
