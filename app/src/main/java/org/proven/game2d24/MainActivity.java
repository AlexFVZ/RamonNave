package org.proven.game2d24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultat;
    GameView gameView;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareObject();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                gameView.postInvalidate(); // onDraw
//                gameView.move();

            }
        });

        ThreadGame thread = new ThreadGame(gameView);
        thread.start();

        resultat.setText("Has perdut");
    }

    private void prepareObject() {
        resultat=findViewById(R.id.resultat);
        gameView =findViewById(R.id.gameview);
        b =findViewById(R.id.button1);
    }
}