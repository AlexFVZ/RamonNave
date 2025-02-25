package org.proven.game2d24;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {

    ArrayList<Ball> listBalls;
    Car car;
    ArrayList<Ball> listShots;
    TextView puntuacio=findViewById(R.id.contador);
    int contador=0;

    public GameView(Context context) {
        super(context);
        initBalls();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBalls();
        System.out.println("GameView Constructor");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("onSizeChanged: " + w + "-" + h);
        for (Ball b : listBalls) {
            b.setMaxX(w);
            b.setMaxY(h);
        }
    }


    private void initBalls() {
        listBalls = new ArrayList<>();
        listShots = new ArrayList<>();
        Ball ball = new Ball(200, 200);
        ball.setRadius(100);
        ball.setVelocity(30);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        ball.setPaint(p);

        Ball ball1 = new Ball(100, 400);
        ball1.setRadius(80);
        ball1.setVelocity(30);
        p = new Paint();
        p.setColor(Color.BLUE);
        ball1.setPaint(p);

        car = new Car(500, 1900 - 20, this.getContext());
        car.setRadius(20);
        car.setVelocity(20);
        p.setColor(Color.GREEN);
        car.setPaint(p);
        Bitmap ballImage = BitmapFactory.decodeResource(getResources(), R.drawable.nausinfondo);
        car.setImage(ballImage);
        listBalls.add(ball);
        listBalls.add(ball1);
    }

    public void move() {
        car.move();
        for (Ball b : listBalls) {
            b.move();
        }
    }

    public void collisions() {
        Ball b1, b2,shot;
        for (int i = 0; i < listBalls.size() - 1; i++) {
            b1 = listBalls.get(i);
            for (int j = i + 1; j < listBalls.size(); j++) {
                b2 = listBalls.get(j);
                if (b1.collision(b2)) {
                    // HI HA COLISIO Invert Directions
                    b1.setDirectionX(!b1.isDirectionX());
                    b1.setDirectionY(!b1.isDirectionY());
                    b2.setDirectionX(!b2.isDirectionX());
                    b2.setDirectionY(!b2.isDirectionY());
                    //comprovar shots
                    if (car.collision(b1)||car.collision(b2)) {
                        contador++;
                        Log.d("TAG", String.valueOf(contador));
                    }
                }


            }
//            shot=listShots.get(i);
//            if (shot.collision(b1)) {
//                // HI HA COLISIO Invert Directions
//                listBalls.remove(b1);
//                contador++;
//                puntuacio.setText("Puntuació: "+contador);
//            }

            //comprovar car



        }


    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        car.onDraw(canvas);
        for (Ball b : listBalls) {
            b.onDraw(canvas);

        }
    }
}
