package org.proven.game2d24;

import android.widget.TextView;

public class ThreadGame extends Thread {
    GameView gameView;
    TextView puntuacio;
    MainActivity main;
    boolean seguir=true;

    public ThreadGame(GameView gameView,TextView puntuacio,MainActivity main) {
        this.gameView = gameView;
        this.puntuacio = puntuacio;
        this.main = main;
    }


    @Override
    public void run() {
        super.run();
        try {
            while (seguir) {
                sleep(30);
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
