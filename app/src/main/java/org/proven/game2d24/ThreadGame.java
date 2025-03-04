package org.proven.game2d24;

import android.widget.TextView;

public class ThreadGame extends Thread {
    GameView gameView;
    TextView puntuacio;
    boolean seguir=true;

    public ThreadGame(GameView gameView,TextView puntuacio) {
        this.gameView = gameView;
        this.puntuacio = puntuacio;
    }


    @Override
    public void run() {
        super.run();
        try {
            while (seguir) {
                sleep(20);
                gameView.move();
                gameView.collisions();
                gameView.postInvalidate();
            }
        } catch (InterruptedException e) {
        }
    }
    public void finalizar(){
        seguir=false;
    }
}
