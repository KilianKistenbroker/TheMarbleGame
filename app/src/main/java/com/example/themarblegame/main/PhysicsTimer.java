package com.example.themarblegame.main;

import java.util.TimerTask;

public class PhysicsTimer {

    //this is for applying velocity on scroll for ACTION_MOVE

    /*
    Every half second will record position (SCROLL) in var1 and var2 two will record position on ACTION_UP.
    We will take the difference of those values to determine scroll velocity, which will be executed
    in cancel function.

    ...

    SCROLL_1 - SCROLL_2 = velocity,
    where SCROLL_1 is recorded every half second,
    and SCROLL_2 is recorded on ACTION_UP.

    ...

    create 5 var positions that updates every tenth of a second.
    figure out how to get displacement of .5 seconds before ACTION_UP.

    ...

    lets just do y2 - y1 / time to calculate speed
    timer starts on ACTION_MOVE.
    timer resets every two seconds??

    ...

    how about we create an array list of last ten positions with intervals of .1 seconds,
    then we would take the position 6 units back from ACTION_UP. create reach back function,
    to check how far we can go back.

    ...

    looks like im not doing this. I will do an elevator like scroll, where auto scroll will happen
    as if the screen where an analog stick.

    ...

    ok i think it good now
     */

    private static final String TAG = "msg";
    public boolean timer1Running = false;
    public boolean timer2Running = false;

    public double fractionOfSecondPassed = 0f;
    public float milliOfSecondPassed = 0f;



    public float position1 = 0;
    public float position2 = 0;

    public double velocity = 0f;

    public java.util.Timer timer1;
    public TimerTask timerTask1;

    public java.util.Timer timer2;
    public TimerTask timerTask2;

//    public void pause() {
//        timer1Running = false;
//    }
//
//    public void resume() {
//        timer1Running = true;
//    }
//
//    public void restart() {
//        cancel();
//        startHomeTimer1();
//    }

    public float previousPosition1;

    public void startHomeTimer1() {
        timer1 = new java.util.Timer();
        timerTask1 = new TimerTask() {
            @Override
            public void run() {
                if (timer1Running) {
                    fractionOfSecondPassed += .04f;
                    previousPosition1 = position1;
                    position1 = HomeInterface.SCROLL;
                }
            }
        };
        timer1.scheduleAtFixedRate(timerTask1, 0, 40);
    }


    public void startTimer2() {
        timer2 = new java.util.Timer();
        timerTask2 = new TimerTask() {
            @Override
            public void run() {
                if (timer2Running) {
                    milliOfSecondPassed += .001f;
                }
            }
        };
        timer2.scheduleAtFixedRate(timerTask2, 0, 1);
    }

    public void startLevelTimer1() {
        timer1 = new java.util.Timer();
        timerTask1 = new TimerTask() {
            @Override
            public void run() {
                if (timer1Running) {
                    fractionOfSecondPassed += .05f;
                    position1 = LevelInterface.SCROLL;
                }
            }
        };
        timer1.scheduleAtFixedRate(timerTask1, 0, 50);
    }


    public void calcVelocity(int state) {
        if (state == 1)
            position2 = HomeInterface.SCROLL;
        else
            position2 = LevelInterface.SCROLL;
        double denominator = 1;
        if (milliOfSecondPassed < .04f) {
            position1 = 0;
            position2 = 0;
        }

        else if (Math.abs(milliOfSecondPassed - fractionOfSecondPassed) < .02f)
            denominator = .02f;
        else if (Math.abs(fractionOfSecondPassed - milliOfSecondPassed) > .04f)
            denominator = .04f;
        else if (Math.abs(milliOfSecondPassed - fractionOfSecondPassed) != 0)
            denominator = Math.abs(fractionOfSecondPassed - milliOfSecondPassed);
        else {
            position1 = previousPosition1;
            denominator = .04f;
        }

        velocity = (position2 - position1)/(100*denominator); //position 1 is position @ .5 seconds before ACTION_UP.5
    }

    public void cancel() {
        timer1Running = false;
        timer2Running = false;
        fractionOfSecondPassed = 0f;
        milliOfSecondPassed = 0f;
        previousPosition1 = 0;
        position1 = 0;
        position2 = 0;
//        timer1 = null;
//        timer2 = null;
//        timerTask1 = null;
//        timerTask2 = null;
    }
}
