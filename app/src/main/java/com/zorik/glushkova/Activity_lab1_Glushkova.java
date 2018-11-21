package com.zorik.glushkova;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_lab1_Glushkova extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    TextView textView;
    MyThread myThread;
    private GestureDetectorCompat gestureDetectorCompat;
    private Graphic graphic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1__glushkova);
        textView = findViewById(R.id.textView);
        gestureDetectorCompat = new GestureDetectorCompat(this, this);
        gestureDetectorCompat.setOnDoubleTapListener(this);
        graphic = findViewById(R.id.id_graphics_view);
        textView.setText("Click double time to start");
        graphic.context = this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    private int counter=0;

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
//        counter++;
//        if(!graphic.border) {
//            switch (counter % 2) {
//                case 1:
//                    graphic.X_Y_rect[0] = e.getX();
//                    graphic.X_Y_rect[1] = e.getY();
//                    textView.setText("first [X Y] for rect : " + (int) graphic.X_Y_rect[0] + " , " + (int) graphic.X_Y_rect[1] + " , border false");
//                    break;
//                case 0:
//                    graphic.X_Y_rect[2] = e.getX();
//                    graphic.X_Y_rect[3] = e.getY();
//                    textView.setText("second [X Y] for rect : " + (int) graphic.X_Y_rect[2] + " , " + (int) graphic.X_Y_rect[3] + " , border true");
//                    graphic.border = true;
//                    break;
//            }
//        }
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        textView.setText("Pause");
        graphic.border_exit = true;
    }

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void onSwipeRight() {
        textView.setText("swipe Right");
        graphic.direction="Right";
    }

    public void onSwipeLeft() {
        textView.setText("swipe Left");
        graphic.direction="Left";
    }

    public void onSwipeTop() {
        textView.setText("swipe Top");
        graphic.direction="Up";
    }

    public void onSwipeBottom() {
        textView.setText("swipe Bottom");
        graphic.direction="Bottom";
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        graphic.border_exit = false;
        myThread = new MyThread();
        myThread.start();
        textView.setText("Start!!        Press on screen to stop");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }



    class MyThread extends Thread{


        @Override
        public void run() {
            super.run();
            while(!graphic.border_exit) {
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                graphic.post(new Runnable() {
                    @Override
                    public void run() {
                        graphic.move();

                    }
                });

            }
            Toast.makeText(Activity_lab1_Glushkova.this, "Бублик вышел за пределы", Toast.LENGTH_SHORT).show();

        }
    }






}
