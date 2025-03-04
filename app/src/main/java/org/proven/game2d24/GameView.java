package org.proven.game2d24;

import android.app.Activity;
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
    ArrayList<Shot> listShots;
    TextView puntuacio;
    int contador = 0;
    ThreadGame threadGame;

    public void setPuntuacio(TextView contador) {
        this.puntuacio=contador;
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBalls();
        System.out.println("GameView Constructor");
    }

    public void setThreadGame(ThreadGame threadGame) {
        this.threadGame = threadGame;
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
        ball.setVelocity(20);
        Paint p = new Paint();
        p.setColor(randomColor());
        p.setStrokeWidth(10);
        ball.setPaint(p);

        Ball ball1 = new Ball(100, 400);
        ball1.setRadius(80);
        ball1.setVelocity(20);
        p = new Paint();
        p.setColor(randomColor());
        p.setStrokeWidth(10);
        ball1.setPaint(p);

        car = new Car(500, 1900 - 20, this.getContext());
        car.setRadius(30);
        car.setVelocity(30);
        p.setColor(Color.GREEN);
        car.setPaint(p);
        Bitmap ballImage = BitmapFactory.decodeResource(getResources(), R.drawable.nausinfondo);
        car.setImage(ballImage);
        listBalls.add(ball);
        listBalls.add(ball1);
    }

    public void initShot() {
        Shot shot = new Shot(car.getX(), car.getY());
        shot.setRadius(20);
        shot.setVelocity(35);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(10);
        shot.setPaint(p);
        listShots.add(shot);
    }

    public void initClickBall(int x,int y){
            Ball nb = new Ball(x, y);
            nb.setRadius((int) (Math.random() * 100) + 20);
            nb.setVelocity(20);
            Paint p = new Paint();
            p.setColor(randomColor());
            p.setStrokeWidth(10);
            nb.setPaint(p);
            listBalls.add(nb);
    }

    public void move() {
        car.move();
        for (Ball b : listBalls) {
            b.move();
        }
        for (Shot s : listShots) {
            s.move();
        }
    }

    public void collisions() {
        Ball b1, b2;
        for (int i = 0; i < listBalls.size() - 1; i++) {
             b1 = listBalls.get(i);
            for (int j = i + 1; j < listBalls.size(); j++) {
                b2 = listBalls.get(j);
                if (b1.collision(b2)) {
                    b1.setDirectionX(!b1.isDirectionX());
                    b1.setDirectionY(!b1.isDirectionY());
                    b2.setDirectionX(!b2.isDirectionX());
                    b2.setDirectionY(!b2.isDirectionY());
                }
            }
        }
        for (int i = listBalls.size() - 1; i >= 0; i--){
            b1 = listBalls.get(i);
            if (car.collision(b1)){
                threadGame.finalizar();
                Log.d("LOSE","La bola "+i+"ha dado en el coche");
            }
        }
        for (int i = listShots.size() - 1; i >= 0; i--) {
            Shot shot = listShots.get(i);
            for (int j = listBalls.size() - 1; j >= 0; j--) {
                Ball ball = listBalls.get(j);
                if (shot.collision(ball)) {
                    Log.d("COLLISION", "Disparo " + i + " eliminó bola " + j + ". Puntuación: " + contador);
                    listShots.remove(i);
                    listBalls.remove(j);
                    contador++;
                    ((Activity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            puntuacio.setText("Puntuació: " + contador);
                        }
                    });
                    break;
                }
            }
        }
        if (listBalls.size()==0){
            threadGame.finalizar();
            Log.d("WIN","Has ganado");
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        car.onDraw(canvas);
        for (Ball b : listBalls) {
            b.onDraw(canvas);
        }
        for (Shot s : listShots) {
            s.onDraw(canvas);
        }
    }

    public int randomColor(){
        int c=0;
        int random = (int)(Math.random()*4)+1;
        if(random==1){
            c= Color.BLUE;
        }
        else if (random==2) {
            c= Color.RED;
        }
        else if (random==3) {
            c= Color.YELLOW;
        }else{
            c= Color.GREEN;
        }
        return c;
    }
}
