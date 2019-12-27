package com.example.MemoryMatchingGame;

public class ButtonBounce implements android.view.animation.Interpolator{
    private double bamplitude = 1;
    private double bfrequency = 10;
    ButtonBounce (double amplitude, double frequency){
        bamplitude = amplitude;
        bfrequency = frequency;
    }

    public float getInterpolation(float time){
        return (float) (-1* Math.pow(Math.E, -time/bamplitude)* Math.cos(bfrequency*time)+1);
    }
}
