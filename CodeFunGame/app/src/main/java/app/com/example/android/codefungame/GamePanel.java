package app.com.example.android.codefungame;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.R.attr.x;
import static android.R.attr.y;
import static app.com.example.android.codefungame.MainThread.canvas;

/**
 * Created by LENOVO on 1/8/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback,SensorEventListener2 {

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    private MainThread thread;

    public static final int MOVESPEED = -5;
    private Player player;
    private Background bg;
    private JoyStickSelected joyStickSelected;
    private JoyStickClass js;
    public GamePanel(Context context) {

        super(context);

        getHolder().addCallback(this);
        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }
    private SensorManager sensorManager;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        Point size = new Point();
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        float xMax = (float) size.x - 100;
        float yMax = (float) size.y - 300;

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

        player = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.ball), 25, 25 , xMax , yMax);
       bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
       // joyStickSelected = new JoyStickSelected(BitmapFactory.decodeResource(getResources(),R.drawable.ball), 65, 25 , 71 , 71);
        Log.v("MainThread","Working4");
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        int counter = 0;
        while (retry && counter < 1000) {
            counter++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void draw(Canvas canvas){

        final float scaleFactorX = ((float) (getWidth())) / (WIDTH);
        final float scaleFactorY = ((float) (getHeight())) / (HEIGHT);
       // Log.v("sa","daddadaa");
        if (canvas != null) {

            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
//Log.v("sa","dada");
            bg.draw(canvas);
            player.draw(canvas);
////            joyStickSelected.draw(canvas);
//            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image_button_bg),WIDTH - 50,HEIGHT - 50,null);
            canvas.restoreToCount(savedState);
        }
    }

    public void update(){
        player.update();
       // bg.update();
    }

    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            player.setXAccel(sensorEvent.values[0]);
            player.setYAccel(-sensorEvent.values[1]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
