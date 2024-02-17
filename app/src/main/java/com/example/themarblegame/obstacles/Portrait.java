package com.example.themarblegame.obstacles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;

import com.example.themarblegame.R;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.main.Constants;
import com.example.themarblegame.main.Physics;


public class Portrait implements GameDesigner {
    //this class will be used for making images
    private final Rect rect;
    private Bitmap scaledBitmap;
    private final Physics physics;

    public Portrait(int left, int top, int right, int bottom, int bitmap, boolean isPhysics) {
        this.rect = new Rect(left, top, right, bottom);
        Bitmap ogBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), bitmap);
        scaledBitmap = Bitmap.createScaledBitmap(ogBitmap, (right-left), (bottom-top), false);
        ogBitmap.recycle();
        if (isPhysics)
            physics = new Physics();
        else
            physics = null;
    }
    // no bitmap portrait
    public Portrait(int left, int top, int right, int bottom, boolean isPhysics) {
        this.rect = new Rect(left, top, right, bottom);
        if (isPhysics)
            physics = new Physics();
        else
            physics = null;
    }

    public Rect getRect() {
        return rect;
    }

    public Bitmap getScaledBitmap() {
        return scaledBitmap;
    }

    public void setScaledBitmap(Bitmap scaledBitmap) {
        this.scaledBitmap = scaledBitmap;
    }

    public Physics getPhysics() {return physics;}

    Paint paint = new Paint();


    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.BLACK);
//        canvas.drawBitmap(scaledBitmap, null, rect, null);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {
    }

}
