package com.example.themarblegame.main;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DrawText extends PlayerBank {

    public void drawCenter(Canvas canvas, Paint paint, Rect rect, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(rect);
        int cHeight = rect.height();
        int cWidth = rect.width();
        paint.getTextBounds(text, 0, text.length(), rect);
        float x = cWidth / 2f - rect.width() / 2f - rect.left;
        float y = cHeight / 2f + rect.height() / 2f - rect.bottom;
        canvas.drawText(text, x, y, paint);
    }

    public void drawInCenterOfRect(Canvas canvas, Paint paint, Rect rect, Rect src, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        int left = src.left + (src.right - src.left)/2;
        int top = (src.top + (src.bottom - src.top)/2);
        paint.getTextBounds(text, 0, text.length(), rect);
        float x = left - rect.width() / 2f - rect.left;
        float y = top + rect.height() / 2f - rect.bottom;
        canvas.drawText(text, x, y, paint);
    }

    public static void drawInCenterOfStaticRect(Canvas canvas, Paint paint, Rect rect, Rect src, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        int left = src.left + (src.right - src.left)/2;
        int top = (src.top + (src.bottom - src.top)/2);
        paint.getTextBounds(text, 0, text.length(), rect);
        float x = left - rect.width() / 2f - rect.left;
        float y = top + rect.height() / 2f - rect.bottom;
        canvas.drawText(text, x, y, paint);
    }

    public void drawAlignLeftInRect(Canvas canvas, Paint paint, Rect rect, Rect src, String text, int padding) {
        paint.setTextAlign(Paint.Align.LEFT);
        int top = (src.top + (src.bottom - src.top)/2);
        paint.getTextBounds(text, 0, text.length(), rect);
        int x = (src.left+padding);
        float y = top + rect.height() / 2f - rect.bottom;
        canvas.drawText(text, x, y, paint);
    }

    public void drawAlignRightInRect(Canvas canvas, Paint paint, Rect rect, Rect src, String text, int padding) {
        paint.setTextAlign(Paint.Align.LEFT);
        int top = (src.top + (src.bottom - src.top)/2);
        paint.getTextBounds(text, 0, text.length(), rect);
        int x = (src.right-padding-rect.width());
        float y = top + rect.height() / 2f - rect.bottom;
        canvas.drawText(text, x, y, paint);
    }

    //locks on center x-axis
    public void drawCenterY(Canvas canvas, Paint paint, String text, int asset, Rect rect, float x, float bounds) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(rect);
        int cHeight = rect.height();
        paint.getTextBounds(text, 0, text.length(), rect);
        float y = cHeight / bounds - rect.height() / bounds - rect.top;
        canvas.drawText(text + asset, x, y, paint);
    }

    //locks on center y-axis
    public void drawCenterX (Canvas canvas, Paint paint, String text, Rect rect, float y, float bounds) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(rect);
        int cWidth = rect.width();
        paint.getTextBounds(text, 0, text.length(), rect);
        float x = cWidth / bounds - rect.width() / bounds - rect.left;
        canvas.drawText(text, x, y, paint);
    }

    //with assets
    public void drawCenterX(Canvas canvas, Paint paint, String text, int asset, Rect rect, float y, float bounds) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(rect);
        int cWidth = rect.width();
        paint.getTextBounds(text, 0, text.length(), rect);
        float x = cWidth / bounds - rect.width() / bounds - rect.left;
        canvas.drawText(text + asset, x, y, paint);
    }

    //CREATE ITERATIVE FUNCTION HERE - it iterates up from 0 - (build new timer in here for this function. implement counting acceleration)
//    public java.util.Timer timer1;
//    public TimerTask timerTask1;
    private int num0 = 0;
    private int num1 = 0;
    private int num = 0;
    private int increaseState = 1;
    private boolean counterIncreaseNum = false;

    public int increaseNum(int state, int targetNum) {
        if (increaseState == 1 && targetNum != 0) {

            switch (state){
                case 0:
                    //from 0
                    if (num0 < targetNum) {
                        num0++;
                    } else {
                        num0 = targetNum;
                        increaseState = 0;
                    }
                    num = num0;
                    break;


                case 1:
                    //from > 0
                    if (!counterIncreaseNum) {
                        num1 = getCoin();
                        counterIncreaseNum = true;
                    }
                    if (num1 < targetNum) {
                        num1++;
                    } else {
                        num1 = targetNum;
                        counterIncreaseNum = false;
                        increaseState = 0;
                    }
                    num = num1;
                    break;
            }
        }

        return num;
    }

    public void resetIncreaseNum() {
        num = 0;
        num0 = 0;
        num1 = 0;
        increaseState = 1;
    }







    //write text box logic here. maximum of 8 lines
    //ENGLISH
    public void text(Canvas canvas, int lines, Rect src, Paint paint, String line1, String line2, String line3, String line4, String line5, String line6, String line7) {
        paint.setTextAlign(Paint.Align.LEFT);
        int left = src.left + Scale.UNIT/2;
        int top = (src.top + Scale.UNIT);
        switch (lines) {
            case 1:
                canvas.drawText(line1, left, top, paint);
                break;
            case 2:
                canvas.drawText(line1, left, top, paint);
                canvas.drawText(line2, left, (float) (top+Scale.UNIT*1.5), paint);
                break;
            case 3:
                canvas.drawText(line1, left, top, paint);
                canvas.drawText(line2, left, (float) (top+Scale.UNIT*1.5), paint);
                canvas.drawText(line3, left, (float) (top+Scale.UNIT*2.5), paint);
                break;
            case 4:
                canvas.drawText(line1, left, top, paint);
                canvas.drawText(line2, left, (float) (top+Scale.UNIT*1.5), paint);
                canvas.drawText(line3, left, (float) (top+Scale.UNIT*2.5), paint);
                canvas.drawText(line4, left, (float) (top+Scale.UNIT*3.5), paint);
                break;
            case 5:
                canvas.drawText(line1, left, top, paint);
                canvas.drawText(line2, left, (float) (top+Scale.UNIT*1.5), paint);
                canvas.drawText(line3, left, (float) (top+Scale.UNIT*2.5), paint);
                canvas.drawText(line4, left, (float) (top+Scale.UNIT*3.5), paint);
                canvas.drawText(line5, left, (float) (top+Scale.UNIT*4.5), paint);
                break;
            case 6:
                canvas.drawText(line1, left, top, paint);
                canvas.drawText(line2, left, (float) (top+Scale.UNIT*1.5), paint);
                canvas.drawText(line3, left, (float) (top+Scale.UNIT*2.5), paint);
                canvas.drawText(line4, left, (float) (top+Scale.UNIT*3.5), paint);
                canvas.drawText(line5, left, (float) (top+Scale.UNIT*4.5), paint);
                canvas.drawText(line6, left, (float) (top+Scale.UNIT*5.5), paint);
                break;
            case 7:
                canvas.drawText(line1, left, top, paint);
                canvas.drawText(line2, left, (float) (top+Scale.UNIT*1.5), paint);
                canvas.drawText(line3, left, (float) (top+Scale.UNIT*2.5), paint);
                canvas.drawText(line4, left, (float) (top+Scale.UNIT*3.5), paint);
                canvas.drawText(line5, left, (float) (top+Scale.UNIT*4.5), paint);
                canvas.drawText(line6, left, (float) (top+Scale.UNIT*5.5), paint);
                canvas.drawText(line7, left, (float) (top+Scale.UNIT*6.5), paint);
                break;
        }
    }
}