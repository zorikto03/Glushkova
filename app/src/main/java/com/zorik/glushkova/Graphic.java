package com.zorik.glushkova;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.jar.Attributes;

public class Graphic extends View {


    protected Context context;
    protected String direction;
    private float position[];
    private float LenX, LenY;
    private int radius_circle = 30;
    protected float X_Y_rect[];
    protected boolean border = true;
    protected boolean border_exit = false;          // активатор потока (пока не врезался в границу)
    public Graphic(Context context,  @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        position = new float[2];
        position[0] = 400;
        position[1] = 400;
        X_Y_rect = new float[4];
        direction="Right";

    }

    public void move(){
        if(!border) {
            //check of borders
            if (position[0] + radius_circle >= LenX) {
                direction = "Left";
                border_exit = true;
            }
            if (position[0] - radius_circle < 0) {
                direction = "Right";
                border_exit = true;
            }
            if (position[1] + radius_circle >= LenY){
                border_exit = true;
                direction = "Up";
            }
            if (position[1] - radius_circle < 0){
                direction = "Bottom";
                border_exit = true;
            }
        }
        else {
            if (position[0] + radius_circle >= X_Y_rect[2]) {
                direction = "Left";
                border_exit = true;
            }
            if (position[0] - radius_circle < X_Y_rect[0]) {
                direction = "Right";
                border_exit = true;
            }
            if (position[1] + radius_circle >= X_Y_rect[3]){
                direction = "Up";
                border_exit = true;}
            if (position[1] - radius_circle < X_Y_rect[1]){
                direction = "Bottom";
                border_exit = true;
            }
        }
        //Moving on some direction
        switch (direction) {
            case "Right":
                position[0] += 5;
                break;
            case "Left":
                position[0] -= 5;
                break;
            case "Up":
                position[1] -= 5;
                break;
            case "Bottom":
                position[1] += 5;
                break;

            default:

                break;
        }
        invalidate();               //call onDraw
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LenX = canvas.getWidth();
        LenY = canvas.getHeight();

        X_Y_rect[0] = (LenX/5);
        X_Y_rect[1] = (LenY/5);
        X_Y_rect[2] = LenX-(LenX/5);
        X_Y_rect[3] = LenY-(LenY/5);;

        Paint pen = new Paint();
        pen.setColor(Color.BLACK);
        Paint pen_white = new Paint();
        pen_white.setColor(Color.rgb(255, 255, 255));
        Paint pen_rect = new Paint();
        pen.setColor(Color.BLUE);


        if(border)
            canvas.drawRect((X_Y_rect[0]<X_Y_rect[2] ? X_Y_rect[0]: X_Y_rect[2]), (X_Y_rect[1]<X_Y_rect[3] ? X_Y_rect[1]: X_Y_rect[3]), (X_Y_rect[0]>X_Y_rect[2] ? X_Y_rect[0]: X_Y_rect[2]), (X_Y_rect[1]<X_Y_rect[3] ? X_Y_rect[3]: X_Y_rect[1]), pen_rect);
        canvas.drawCircle(position[0],position[1], 2*radius_circle, pen);
        canvas.drawCircle(position[0],position[1], radius_circle, pen_white);
    }
}
