package app.com.example.android.codefungame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.R.attr.animation;

/**
 * Created by LENOVO on 1/9/2017.
 */
public class JoyStickSelected extends GameObject{

    private Bitmap image;
    public JoyStickSelected(Bitmap res, int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        image = res;
    }

    public void update(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(image,x,y,null);
        }catch(Exception e){}
    }
}
