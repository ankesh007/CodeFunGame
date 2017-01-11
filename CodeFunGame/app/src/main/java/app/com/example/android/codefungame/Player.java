package app.com.example.android.codefungame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.animation.Animation;

import static android.R.attr.animation;
import static android.R.attr.width;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by LENOVO on 1/8/2017.
 */

public class Player extends GameObject{

        private Bitmap image;
        private int score;
        private float xAccel;
        private float yAccel;
        private float xVel;
        private float yVel;
        private float xPos;
        private float yPos;
        private float xMax;
        private float yMax;

        private boolean up;
        private boolean playing;
     //   private Animation animation = new Animation();
        private long startTime;

        public Player(Bitmap image, int w, int h, float xMax , float yMax) {

            this.xMax = xMax;
            this.yMax = yMax;
            this.image = image;
            xAccel = 0.0f;
            yAccel = 0.0f;
            xVel = 0.0f;
            yVel = 0.0f;
            xPos = 0.0f;
            yPos = 0.0f;

            score = 0;
            height = h;
            width = w;

            startTime = System.nanoTime();

        }

        public void setUp(boolean b){up = b ;}

        public void update()
        {
            float frameTime = 0.666f;
            xVel += (xAccel * frameTime);
            yVel += (yAccel * frameTime);

            float xS = (xVel / 2) * frameTime;
            float yS = (yVel / 2) * frameTime;

            xPos -= xS;
            yPos -= yS;

            if (xPos > xMax) {
                xPos = xMax;
                xVel = -xVel;
            } else if (xPos < 0) {
                xPos = 0;
                xVel = -xVel;
            }
            if (yPos > yMax) {
                yPos = yMax;
                yVel = -yVel;
            } else if (yPos < 0) {
                yPos = 0;
                yVel = -yVel;
            }
        }

        public void draw(Canvas canvas)
        {
        //    Log.v("s","xPos : "+xPos+"yPos : "+yPos);
            canvas.drawBitmap(image,xPos,yPos,null);
        }
        public int getScore(){return score;}
        public boolean getPlaying(){return playing;}
        public void setPlaying(boolean b){playing = b;}
        public void resetDY(){dy = 0;}
        public void resetScore(){score = 0;}

    public void setXAccel(float value) {
    xAccel  = value;
    }
    public void setYAccel(float value) {
    yAccel = value;
    }
}
