package app.com.example.android.codefungame;

import android.graphics.Rect;

/**
 * Created by LENOVO on 1/8/2017.
 */
public abstract class GameObject {
        protected double x;
        protected double y;
        protected double dy;
        protected double dx;
        protected double width;
        protected double height;

        public void setX(int x)
        {
            this.x = x;
        }
        public void setY(int y)
        {
            this.y = y;
        }
        public int getX()
        {
            return x;
        }
        public int getY()
        {
            return y;
        }
        public int getHeight()
        {
            return height;
        }
        public int getWidth()
        {
            return width;
        }
        public Rect getRectangle()
        {
            return new Rect(x, y, x+width, y+height);
        }

}
