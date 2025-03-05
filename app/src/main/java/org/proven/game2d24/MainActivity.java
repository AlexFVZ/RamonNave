package org.proven.game2d24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView contador;
    GameView gameView;
    Button b;
    int x,y;
    ThreadGame thread;

    View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareObject();
        prepareListener();
        initListener();
        initGame();
    }

    private void initListener() {
        b.setOnClickListener(listener);
        gameView.setOnClickListener(listener);
    }

    private void initGame()  {
        thread = new ThreadGame(gameView,contador,this);
        gameView.setThreadGame(thread);
        thread.start();

    }

    private void prepareListener() {
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.bShot){
                    gameView.initShot();
                }
                else if(v.getId()==R.id.gameview){
                    gameView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            final int actionPeformed = event.getAction();
                            switch (actionPeformed){
                                case MotionEvent.ACTION_DOWN:{
                                    x=(int) event.getX();
                                    y=(int) event.getY();
                                    gameView.initClickBall(x,y);
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        };
    }

    private void prepareObject() {

        contador=findViewById(R.id.contador);
        gameView =findViewById(R.id.gameview);
        gameView.setPuntuacio(contador);
        gameView.setMain(this);
        b =findViewById(R.id.bShot);
    }

    public void VoDGame(boolean victoria) {
        runOnUiThread(() -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (victoria) {
            builder.setTitle("¡Victoria!")
                    .setMessage("Has ganado con " + contador.getText() + " . ¿Reiniciar?");
        } else {
            builder.setTitle("Game Over")
                    .setMessage("Has perdido. " + contador.getText() + ". ¿Reiniciar?");
        }
        builder.setPositiveButton("Sí", (dialog, which) -> {
            gameView.reinicio();
            thread = new ThreadGame(gameView, contador, this);
            gameView.setThreadGame(thread);
            thread.start();
        });
        builder.setNegativeButton("No", (dialog, which) -> finish());
        builder.setCancelable(false);
        builder.show();
        });
    }
}