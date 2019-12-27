package com.example.MemoryMatchingGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ImageLoadTask.ICallback,ImageDownload.ICallback {

    Button mFetchBtn;
    TextView barText;
    TextView instructionText;
    EditText mUrl;
    ProgressBar bar = null;
    ImageView mView1, mView2,mView3,mView4,mView5,mView6,mView7,mView8, mView9,mView10,
            mView11,mView12,mView13,mView14,mView15, mView16,mView17,mView18,mView19,mView20;

    static ImageDownload asyncTask;
    static int count=0;
    static int error= 0;
    static ArrayList<String> picsToLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if error in url, change the hint in edittext
        if(error>0){
            mUrl = findViewById(R.id.inputTxt);
            mUrl.setHint("Invalid URL!");
        }

        count = 0;
        picsToLoad = new ArrayList<>();
        mFetchBtn = findViewById(R.id.fetchbtn);
        mFetchBtn.setTag(0);
        bar = findViewById(R.id.progressBar);
        bar.setProgress(0);
        bar.setMax(20);
        bar.setVisibility(View.INVISIBLE);
        barText = findViewById(R.id.progressText);
        instructionText = findViewById(R.id.instructions);
        instructionText.setText("Please enter the url to search for images");

        //call method to display all imageview to default
        ImagePlaceholder();

        mFetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard upon button press
                InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mFetchBtn.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                final Animation banim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.button_bounce);
                //Use bounce interpolator with amplitude 0.2 and frequency 20
                ButtonBounce interpolator = new ButtonBounce(0.2,20);
                banim.setInterpolator(interpolator);
                mFetchBtn.startAnimation(banim);

                //make the previous instruction disappear(enter url)
                instructionText.setVisibility(View.INVISIBLE);

                //checks the tag(0 means first time button is pressed
                if((Integer)mFetchBtn.getTag() == 0){
                    mFetchBtn.setTag(1);
                    if (bar != null){
                        //Make the progress bar and text appear
                        bar.setVisibility(View.VISIBLE);
                        barText.setVisibility(View.VISIBLE);
                    }
                    mUrl = findViewById(R.id.inputTxt);
                    String url = "https://"+ mUrl.getText().toString();
                    //Run async method to scrape the internet
                    new ImageLoadTask(MainActivity.this).execute(url);

                    mView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView1.startAnimation(anim1);
                            if(count < 6){
                                picsToLoad.add("pic1");
                                mView1.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic1");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView2.startAnimation(anim2);
                            if(count < 6){
                                picsToLoad.add("pic2");
                                mView2.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic2");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView3.startAnimation(anim3);
                            if(count < 6){
                                picsToLoad.add("pic3");
                                mView3.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic3");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView4.startAnimation(anim4);
                            if(count < 6){
                                picsToLoad.add("pic4");
                                mView4.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic4");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView5.startAnimation(anim5);
                            if(count < 6){
                                picsToLoad.add("pic5");
                                mView5.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic5");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim6 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView6.startAnimation(anim6);
                            if(count < 6){
                                picsToLoad.add("pic6");
                                mView6.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic6");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim7 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView7.startAnimation(anim7);
                            if(count < 6){
                                picsToLoad.add("pic7");
                                mView7.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic7");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim8 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView8.startAnimation(anim8);
                            if(count < 6){
                                picsToLoad.add("pic8");
                                mView8.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic8");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim9 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView9.startAnimation(anim9);
                            if(count < 6){
                                picsToLoad.add("pic9");
                                mView9.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic9");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim10 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView10.startAnimation(anim10);
                            if(count < 6){
                                picsToLoad.add("pic10");
                                mView10.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic10");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim11 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView11.startAnimation(anim11);
                            if(count < 6){
                                picsToLoad.add("pic11");
                                mView11.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic11");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim12 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView12.startAnimation(anim12);
                            if(count < 6){
                                picsToLoad.add("pic12");
                                mView12.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic12");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim13 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView13.startAnimation(anim13);
                            if(count < 6){
                                picsToLoad.add("pic13");
                                mView13.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic13");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim14 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView14.startAnimation(anim14);
                            if(count < 6){
                                picsToLoad.add("pic14");
                                mView14.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic14");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim15 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView15.startAnimation(anim15);
                            if(count < 6){
                                picsToLoad.add("pic15");
                                mView15.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic15");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim16 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView16.startAnimation(anim16);
                            if(count < 6){
                                picsToLoad.add("pic16");
                                mView16.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic16");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView17.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim17 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView17.startAnimation(anim17);
                            if(count < 6){
                                picsToLoad.add("pic17");
                                mView17.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic17");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView18.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim18 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView18.startAnimation(anim18);
                            if(count < 6){
                                picsToLoad.add("pic18");
                                mView18.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic18");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView19.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim19 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView19.startAnimation(anim19);
                            if(count < 6){
                                picsToLoad.add("pic19");
                                mView19.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic19");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });

                    mView20.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim20 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView20.startAnimation(anim20);
                            if(count < 6){
                                picsToLoad.add("pic20");
                                mView20.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic20");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);

                                overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition
                            }
                        }
                    });
                }

                //if fetch button is pressed during the downloading of images
                else{
                    //cancel the async task
                    asyncTask.cancel(true);
                    //reset the pictures
                    ImagePlaceholder();
                    if (bar != null){
                        //Make the progress bar and text disappear
                        bar.setVisibility(View.VISIBLE);
                        barText.setVisibility(View.VISIBLE);
                    }
                    mUrl = findViewById(R.id.inputTxt);
                    String url = "https://"+ mUrl.getText().toString();
                    //Run async method to scrape the internet
                    new ImageLoadTask(MainActivity.this).execute(url);

                    mView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView1.startAnimation(anim1);
                            if(count < 6){
                                picsToLoad.add("pic1");
                                mView1.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic1");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView2.startAnimation(anim2);
                            if(count < 6){
                                picsToLoad.add("pic2");
                                mView2.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic2");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView3.startAnimation(anim3);
                            if(count < 6){
                                picsToLoad.add("pic3");
                                mView3.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic3");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView4.startAnimation(anim4);
                            if(count < 6){
                                picsToLoad.add("pic4");
                                mView4.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic4");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView5.startAnimation(anim5);
                            if(count < 6){
                                picsToLoad.add("pic5");
                                mView5.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic5");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim6 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView6.startAnimation(anim6);
                            if(count < 6){
                                picsToLoad.add("pic6");
                                mView6.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic6");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim7 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView7.startAnimation(anim7);
                            if(count < 6){
                                picsToLoad.add("pic7");
                                mView7.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic7");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim8 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView8.startAnimation(anim8);
                            if(count < 6){
                                picsToLoad.add("pic8");
                                mView8.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic8");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim9 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView9.startAnimation(anim9);
                            if(count < 6){
                                picsToLoad.add("pic9");
                                mView9.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic9");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim10 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView10.startAnimation(anim10);
                            if(count < 6){
                                picsToLoad.add("pic10");
                                mView10.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic10");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim11 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView11.startAnimation(anim11);
                            if(count < 6){
                                picsToLoad.add("pic11");
                                mView11.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic11");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim12 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView12.startAnimation(anim12);
                            if(count < 6){
                                picsToLoad.add("pic12");
                                mView12.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic12");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim13 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView13.startAnimation(anim13);
                            if(count < 6){
                                picsToLoad.add("pic13");
                                mView13.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic13");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim14 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView14.startAnimation(anim14);
                            if(count < 6){
                                picsToLoad.add("pic14");
                                mView14.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic14");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim15 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView15.startAnimation(anim15);
                            if(count < 6){
                                picsToLoad.add("pic15");
                                mView15.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic15");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim16 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView16.startAnimation(anim16);
                            if(count < 6){
                                picsToLoad.add("pic16");
                                mView16.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic16");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView17.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim17 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView17.startAnimation(anim17);
                            if(count < 6){
                                picsToLoad.add("pic17");
                                mView17.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic17");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView18.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim18 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView18.startAnimation(anim18);
                            if(count < 6){
                                picsToLoad.add("pic18");
                                mView18.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic18");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView19.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim19 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView19.startAnimation(anim19);
                            if(count < 6){
                                picsToLoad.add("pic19");
                                mView19.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic19");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });

                    mView20.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;

                            Animation anim20 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.img_fade_out);                    //animation
                            mView20.startAnimation(anim20);
                            if(count < 6){
                                picsToLoad.add("pic20");
                                mView20.setClickable(false);
                            }
                            else{
                                error = 0;
                                picsToLoad.add("pic20");
                                Intent intent = new Intent(MainActivity.this, CardGameActivity.class);
                                intent.putStringArrayListExtra("pics",picsToLoad);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

    }

    //After scraping is done
    @Override
    public void onProcessFinish(String output){

        if(output == null){
            error++;
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }else{
            String saveToPath = getFilesDir() + "/pic";
            ArrayList<String> paths = new ArrayList<>();
            paths.add(saveToPath);

            //to shuffle the order of the images so the images will not appear in the same position
            Integer[] randomArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
            Collections.shuffle(Arrays.asList(randomArray));

            ArrayList<String> imagesurls = new ArrayList<String>();
            ArrayList<String> shuffled = new ArrayList<String>();

            int starthead = output.indexOf("<head>");
            int endhead = output.indexOf("</head>");
            String nohead = output.substring(starthead, endhead);
            output = output.substring(starthead + nohead.length());

            for (int i = 0; i < 30; i++) {

                int start = output.indexOf("img src=\"") + 9;
                int end = output.indexOf("\"", start);
                String src = output.substring(start, end);

                output = output.substring(output.indexOf("img src=\"") + src.length());
                imagesurls.add(src);
            }

            Collections.shuffle(imagesurls);

            for(int j = 0; j<20; j++){
                shuffled.add(imagesurls.get(j));
            }
            //Call async method to download the images
            asyncTask = new ImageDownload(this);
            asyncTask.execute(shuffled,paths);
        }
    }

    //update the progress bar and display images while downloading
    public void onAsyncTaskProgress(int progress) {
        if (bar != null){
            bar.setProgress(Math.round(progress));
            barText.setText("Downloading: "+progress+"/20");

            switch(progress){
                case 1:
                    String path = getFilesDir()+"/pic"+progress+".jpg";
                    File file = new File(path);
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView1.setImageBitmap(bitmap);
                    break;
                case 2:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView2.setImageBitmap(bitmap);
                    break;
                case 3:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView3.setImageBitmap(bitmap);
                    break;
                case 4:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView4.setImageBitmap(bitmap);
                    break;
                case 5:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView5.setImageBitmap(bitmap);
                    break;
                case 6:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView6.setImageBitmap(bitmap);
                    break;
                case 7:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView7.setImageBitmap(bitmap);
                    break;
                case 8:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView8.setImageBitmap(bitmap);
                    break;
                case 9:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView9.setImageBitmap(bitmap);
                    break;
                case 10:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView10.setImageBitmap(bitmap);
                    break;
                case 11:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView11.setImageBitmap(bitmap);
                    break;
                case 12:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView12.setImageBitmap(bitmap);
                    break;
                case 13:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView13.setImageBitmap(bitmap);
                    break;
                case 14:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView14.setImageBitmap(bitmap);
                    break;
                case 15:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView15.setImageBitmap(bitmap);
                    break;
                case 16:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView16.setImageBitmap(bitmap);
                    break;
                case 17:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView17.setImageBitmap(bitmap);
                    break;
                case 18:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView18.setImageBitmap(bitmap);
                    break;
                case 19:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView19.setImageBitmap(bitmap);
                    break;
                case 20:
                    path = getFilesDir()+"/pic"+progress+".jpg";
                    file = new File(path);
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    mView20.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    //After downloading images has completed
    @Override
    public void onDownloadFinish(){
        if (bar != null){
            //Make the progress bar and text disappear
            bar.setVisibility(View.INVISIBLE);
            barText.setVisibility(View.INVISIBLE);
            instructionText.setText("Select 6 pictures to play the game");
            instructionText.setVisibility(View.VISIBLE);
        }
    }

    //method to display all imageviews to default
    public void ImagePlaceholder(){
        mView1=findViewById(R.id.view1);
        mView1.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView2=findViewById(R.id.view2);
        mView2.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView3=findViewById(R.id.view3);
        mView3.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView4=findViewById(R.id.view4);
        mView4.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView5=findViewById(R.id.view5);
        mView5.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView6=findViewById(R.id.view6);
        mView6.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView7=findViewById(R.id.view7);
        mView7.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView8=findViewById(R.id.view8);
        mView8.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView9=findViewById(R.id.view9);
        mView9.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView10=findViewById(R.id.view10);
        mView10.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView11=findViewById(R.id.view11);
        mView11.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView12=findViewById(R.id.view12);
        mView12.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView13=findViewById(R.id.view13);
        mView13.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView14=findViewById(R.id.view14);
        mView14.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView15=findViewById(R.id.view15);
        mView15.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView16=findViewById(R.id.view16);
        mView16.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView17=findViewById(R.id.view17);
        mView17.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView18=findViewById(R.id.view18);
        mView18.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView19=findViewById(R.id.view19);
        mView19.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        mView20=findViewById(R.id.view20);
        mView20.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
    }

    //if async donwloading is cancelled, load all imageviews to default
    @Override
    public void cancelTask(){
        ImagePlaceholder();
    }
}
