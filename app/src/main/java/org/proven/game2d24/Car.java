package org.proven.game2d24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class Car {
    int x, y;  // Position
    int maxX, maxY; // Límit X Y
    int radius;
    int velocity;
    boolean directionX; // Horizontal
    boolean directionY;
    Paint paint;
    boolean live;
    Bitmap image;

    public Car() {
        x = 0;
        y = 0;
        maxX = 1000;
        maxY = 800;
        radius = 20;
        paint = new Paint();
        paint.setColor(Color.GREEN);
        velocity = 10;
        directionX = true;
        directionY = true;
        live = true;

    }

    public Car(int x, int y, Context context) {
        this();
        this.x = x;
        this.y = y;

    }

    public boolean collision(Ball  b) {
        boolean ret = false;
        double D =
                Math.sqrt( Math.pow(getX() - b.getX(), 2)
                        + Math.pow(getY() - b.getY(), 2));
        if(D <= (getRadius() + b.getRadius())) {
            live=false;
            ret = true;
        }
        return ret;
    }

    public void move() {
        // Directions  X
        if (getX() > getMaxX() - getRadius()) {
            setDirectionX(false);
            setX(getMaxX() - getRadius());
        }
        if (getX() < getRadius()) {
            setDirectionX(true);
            setX(getRadius());
        }
        if (isDirectionX()) {  // Right
            setX(getX() + getVelocity());
        } else {                // left
            setX(getX() - getVelocity());
        }
    }

    //onDraw

    public void onDraw(Canvas canvas) {

        if(image!=null){
            canvas.drawBitmap(image,getX(), getY(),null);
        }else{
            canvas.drawCircle(getX(), getY()
                    , getRadius(), getPaint());
        }

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isDirectionX() {
        return directionX;
    }

    public void setDirectionX(boolean directionX) {
        this.directionX = directionX;
    }

    public boolean isDirectionY() {
        return directionY;
    }

    public void setDirectionY(boolean directionY) {
        this.directionY = directionY;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
