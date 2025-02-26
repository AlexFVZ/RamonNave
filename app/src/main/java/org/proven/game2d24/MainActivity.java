package org.proven.game2d24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView contador;
    int puntuacion=0;
    GameView gameView;
    Button b;

    View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareObject();
        prepareListener();
        initListener();
        initGame();


//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameView.postInvalidate(); // onDraw
//                gameView.move();
//
//            }
//        });

    }

    private void initListener() {
        b.setOnClickListener(listener);
        gameView.setOnClickListener(listener);
    }

    private void initGame() {
        ThreadGame thread = new ThreadGame(gameView,contador);
        thread.start();
    }

    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.bShot){
                    gameView.initShot();
                }
                else if(v.getId()==R.id.gameview){
                    gameView.initClickBall();
                }
            }
        };
    }

    private void prepareObject() {
        contador=findViewById(R.id.contador);
        gameView =findViewById(R.id.gameview);
        gameView.setPuntuacio(contador);
        b =findViewById(R.id.bShot);
    }
}