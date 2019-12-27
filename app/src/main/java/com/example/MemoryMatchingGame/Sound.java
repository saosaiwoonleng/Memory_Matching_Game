package com.example.MemoryMatchingGame;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;


public class Sound {

    private AudioAttributes audioAttributes;
    private int soundStreamMax=3;
    private static SoundPool voices;
    private static int success;//when two matches
    private static int lose;//when two mismatches
    private static int congratulation;//when win
    private static int id;

    public Sound(Context context)
    {
        voices=new SoundPool(soundStreamMax, AudioManager.STREAM_MUSIC,0);
        success=voices.load(context,R.raw.success,1);
        lose=voices.load(context,R.raw.wrong,1);
        congratulation=voices.load(context,R.raw.win,1);
    }
    public void playSucessSound(){
        voices.play(success,1.0f,1.0f,1,0,1.0f);
    }
    public void playWrongSound(){
        voices.play(lose,1.0f,1.0f,1,0,1.0f);
    }
    public void playWinSound(){id = voices.play(congratulation,1.0f,1.0f,1,-1,1.0f);}
    public void stopWinSound(){
        voices.stop(id);
    }
}
