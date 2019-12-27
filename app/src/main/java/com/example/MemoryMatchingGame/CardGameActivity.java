package com.example.MemoryMatchingGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardGameActivity extends AppCompatActivity {
    private SelfDialog selfDialog;
    private ExitDialog exitDialog;
    static TextView timerTextView;
    long startTime = 0;
    static long millis;
    static long finalTime;
    static int seconds;
    static int minutes;
    static int pauseTime;
    MediaPlayer mediaPlayer;
    private Sound sound;

    private static ArrayList<String> pics;
    private String pic1, pic2, pic3, pic4, pic5, pic6;

    int firstCard, secondCard, firstClick, secondClick;
    int cardNum = 1;
    int playerScore = 0;
    //to shuffle the array later
    Integer[] cardPositionArray = {11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 26};
    ImageView gamepic11, gamepic12, gamepic13, gamepic14, gamepic21, gamepic22, gamepic23, gamepic24, gamepic31,
            gamepic32, gamepic33, gamepic34;
    TextView mTv;

    //to create the timer
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            pauseTime = 0;
            millis = 0;
            finalTime = 0;
            seconds = 0;
            minutes = 0;

            millis = System.currentTimeMillis() - startTime;
            seconds = (int) (millis / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("Time taken %d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };

    //on pause,stop music,pause timer
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        pauseTime += millis;
        timerHandler.removeCallbacks(timerRunnable);
    }

    //on resume,start music,start timer
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        timerTextView = findViewById(R.id.timer);
        startTime = System.currentTimeMillis() - pauseTime;
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        sound = new Sound(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        timerTextView = findViewById(R.id.timer);
        timerTextView.setVisibility(View.INVISIBLE);

        selfDialog = new SelfDialog(CardGameActivity.this);
        selfDialog.setTitle("Get Ready!");
        selfDialog.setMessage("Press Yes to start the game");
        selfDialog.setYesOnclickListener("Yes", new SelfDialog.onYesOnclickListener() {

            @Override

            public void onYesClick() {
                Toast.makeText(CardGameActivity.this, "Good Luck!", Toast.LENGTH_LONG).show();
                selfDialog.dismiss();

                timerTextView = findViewById(R.id.timer);
                timerTextView.setVisibility(View.VISIBLE);
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);

                mTv = findViewById(R.id.scoredisplay);

                Intent intent = getIntent();
                pics = intent.getStringArrayListExtra("pics");
                pic1 = pics.get(0);
                pic2 = pics.get(1);
                pic3 = pics.get(2);
                pic4 = pics.get(3);
                pic5 = pics.get(4);
                pic6 = pics.get(5);

                gamepic11 = findViewById(R.id.gamepic11);
                gamepic12 = findViewById(R.id.gamepic12);
                gamepic13 = findViewById(R.id.gamepic13);
                gamepic14 = findViewById(R.id.gamepic14);
                gamepic21 = findViewById(R.id.gamepic21);
                gamepic22 = findViewById(R.id.gamepic22);
                gamepic23 = findViewById(R.id.gamepic23);
                gamepic24 = findViewById(R.id.gamepic24);
                gamepic31 = findViewById(R.id.gamepic31);
                gamepic32 = findViewById(R.id.gamepic32);
                gamepic33 = findViewById(R.id.gamepic33);
                gamepic34 = findViewById(R.id.gamepic34);

                gamepic11.setTag("0");
                gamepic12.setTag("1");
                gamepic13.setTag("2");
                gamepic14.setTag("3");
                gamepic21.setTag("4");
                gamepic22.setTag("5");
                gamepic23.setTag("6");
                gamepic24.setTag("7");
                gamepic31.setTag("8");
                gamepic32.setTag("9");
                gamepic33.setTag("10");
                gamepic34.setTag("11");

                resetCardBack();
                Collections.shuffle(Arrays.asList(cardPositionArray));

                gamepic11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic11, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic11.startAnimation(animacard);                                                                  //card fadein

                    }
                });

                gamepic12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic12, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic12.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic13, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic13.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic14, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic14.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic21, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic21.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic22, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic22.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic23, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic23.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic24.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic24, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic24.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic31, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic31.startAnimation(animacard);                                                                  //card fadein

                    }
                });
                gamepic32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic32, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic32.startAnimation(animacard);                                                                  //card fadein

                    }
                });

                gamepic33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic33, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic33.startAnimation(animacard);                                                                  //card fadein

                    }
                });

                gamepic34.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int card = Integer.parseInt((String) v.getTag());
                        loading(gamepic34, card);
                        Animation animacard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_in);      //card animation
                        gamepic34.startAnimation(animacard);                                                                  //card fadein

                    }
                });
            }
        });
        selfDialog.show();
    }

    private void loading(ImageView imageView, int card) {
        String path = getFilesDir() + "/" + pic1 + ".jpg";
        File file = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        if (cardPositionArray[card] == 11) {
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 12) {
            path = getFilesDir() + "/" + pic2 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 13) {
            path = getFilesDir() + "/" + pic3 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 14) {
            path = getFilesDir() + "/" + pic4 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 15) {
            path = getFilesDir() + "/" + pic5 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 16) {
            path = getFilesDir() + "/" + pic6 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 21) {
            path = getFilesDir() + "/" + pic1 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 22) {
            path = getFilesDir() + "/" + pic2 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 23) {
            path = getFilesDir() + "/" + pic3 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 24) {
            path = getFilesDir() + "/" + pic4 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 25) {
            path = getFilesDir() + "/" + pic5 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else if (cardPositionArray[card] == 26) {
            path = getFilesDir() + "/" + pic6 + ".jpg";
            file = new File(path);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

        if (cardNum == 1) {
            firstCard = cardPositionArray[card];
            if (firstCard > 20) {
                firstCard = firstCard - 10;
            }
            cardNum = 2;
            firstClick = card;
            imageView.setEnabled(false);
        } else if (cardNum == 2) {
            secondCard = cardPositionArray[card];
            if (secondCard > 20) {
                secondCard = secondCard - 10;
            }
            cardNum = 1;
            secondClick = card;

            gamepic11.setEnabled(false);
            gamepic12.setEnabled(false);
            gamepic13.setEnabled(false);
            gamepic14.setEnabled(false);
            gamepic21.setEnabled(false);
            gamepic22.setEnabled(false);
            gamepic23.setEnabled(false);
            gamepic24.setEnabled(false);
            gamepic31.setEnabled(false);
            gamepic32.setEnabled(false);
            gamepic33.setEnabled(false);
            gamepic34.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compute();
                }
            }, 1000);

        }

    }

    //check if card matches
    //upon matching, card to disappear and disable clicking and play sound
    private void compute() {
        Animation animcard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_fade_out);      //card animation
        if (firstCard == secondCard) {
            if (firstClick == 0) {
                gamepic11.startAnimation(animcard);                                                           //card fadeout
                gamepic11.setVisibility(View.INVISIBLE);
                gamepic11.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 1) {
                gamepic12.startAnimation(animcard);                                                           //card fadeout
                gamepic12.setVisibility(View.INVISIBLE);
                gamepic12.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 2) {
                gamepic13.startAnimation(animcard);                                                           //card fadeout
                gamepic13.setVisibility(View.INVISIBLE);
                gamepic13.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 3) {
                gamepic14.startAnimation(animcard);                                                           //card fadeout
                gamepic14.setVisibility(View.INVISIBLE);
                gamepic14.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 4) {
                gamepic21.startAnimation(animcard);                                                           //card fadeout
                gamepic21.setVisibility(View.INVISIBLE);
                gamepic21.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 5) {
                gamepic22.startAnimation(animcard);                                                           //card fadeout
                gamepic22.setVisibility(View.INVISIBLE);
                gamepic22.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 6) {
                gamepic23.startAnimation(animcard);                                                           //card fadeout
                gamepic23.setVisibility(View.INVISIBLE);
                gamepic23.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 7) {
                gamepic24.startAnimation(animcard);                                                           //card fadeout
                gamepic24.setVisibility(View.INVISIBLE);
                gamepic24.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 8) {
                gamepic31.startAnimation(animcard);                                                           //card fadeout
                gamepic31.setVisibility(View.INVISIBLE);
                gamepic31.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 9) {
                gamepic32.startAnimation(animcard);                                                           //card fadeout
                gamepic32.setVisibility(View.INVISIBLE);
                gamepic32.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 10) {
                gamepic33.startAnimation(animcard);                                                           //card fadeout
                gamepic33.setVisibility(View.INVISIBLE);
                gamepic33.setClickable(false);
                sound.playSucessSound();
            } else if (firstClick == 11) {
                gamepic34.startAnimation(animcard);                                                           //card fadeout
                gamepic34.setVisibility(View.INVISIBLE);
                gamepic34.setClickable(false);
                sound.playSucessSound();
            }

            if (secondClick == 0) {
                gamepic11.startAnimation(animcard);                                                           //card fadeout
                gamepic11.setVisibility(View.INVISIBLE);
                gamepic11.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 1) {
                gamepic12.startAnimation(animcard);                                                           //card fadeout
                gamepic12.setVisibility(View.INVISIBLE);
                gamepic12.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 2) {
                gamepic13.startAnimation(animcard);                                                           //card fadeout
                gamepic13.setVisibility(View.INVISIBLE);
                gamepic13.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 3) {
                gamepic14.startAnimation(animcard);                                                           //card fadeout
                gamepic14.setVisibility(View.INVISIBLE);
                gamepic14.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 4) {
                gamepic21.startAnimation(animcard);                                                           //card fadeout
                gamepic21.setVisibility(View.INVISIBLE);
                gamepic21.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 5) {
                gamepic22.startAnimation(animcard);                                                           //card fadeout
                gamepic22.setVisibility(View.INVISIBLE);
                gamepic22.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 6) {
                gamepic23.startAnimation(animcard);                                                           //card fadeout
                gamepic23.setVisibility(View.INVISIBLE);
                gamepic23.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 7) {
                gamepic24.startAnimation(animcard);                                                           //card fadeout
                gamepic24.setVisibility(View.INVISIBLE);
                gamepic24.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 8) {
                gamepic31.startAnimation(animcard);                                                           //card fadeout
                gamepic31.setVisibility(View.INVISIBLE);
                gamepic31.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 9) {
                gamepic32.startAnimation(animcard);                                                           //card fadeout
                gamepic32.setVisibility(View.INVISIBLE);
                gamepic32.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 10) {
                gamepic33.startAnimation(animcard);                                                           //card fadeout
                gamepic33.setVisibility(View.INVISIBLE);
                gamepic33.setClickable(false);
                sound.playSucessSound();
            } else if (secondClick == 11) {
                gamepic34.startAnimation(animcard);
                gamepic34.setVisibility(View.INVISIBLE);
                gamepic34.setClickable(false);                                                                  //card fadeout
                sound.playSucessSound();
            }
            playerScore++;
            mTv.setText("Completed sets: " + playerScore);

        }
        //if wrong match,set the cards to default card back and play sound
        else {
            resetCardBack();
            sound.playWrongSound();
        }
        gamepic11.setEnabled(true);
        gamepic12.setEnabled(true);
        gamepic13.setEnabled(true);
        gamepic14.setEnabled(true);
        gamepic21.setEnabled(true);
        gamepic22.setEnabled(true);
        gamepic23.setEnabled(true);
        gamepic24.setEnabled(true);
        gamepic31.setEnabled(true);
        gamepic32.setEnabled(true);
        gamepic33.setEnabled(true);
        gamepic34.setEnabled(true);

        validateEnd();
    }

    //check if the game has ended based on score
    private void validateEnd() {
        if (playerScore == 6) {
            timerHandler.removeCallbacks(timerRunnable);
            finalTime += millis;
            int time = (int) (finalTime / 1000);
            mediaPlayer.stop();
            String filePath = "SampleFolder";
            String fileName = "HighScore.txt";
            File mTargetFile = new File(getFilesDir(), filePath + "/" + fileName);

            String readHighScore = "";
            try {
                FileInputStream fis = new FileInputStream(mTargetFile);
                DataInputStream dataInputStream = new DataInputStream(fis);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

                String strLine;
                while ((strLine = bufferedReader.readLine()) != null) {
                    readHighScore = readHighScore + strLine;
                }
                dataInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //try to read highscore,if no highscore previously,create new file to store
            if (readHighScore.equals("")) {
                File parent = mTargetFile.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Cannot create dir: " + parent);
                }
                String highScoreTxt = String.valueOf(time);
                try {
                    FileOutputStream fos = new FileOutputStream(mTargetFile);
                    fos.write(highScoreTxt.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sound.playWinSound();
                exitDialog = new ExitDialog(CardGameActivity.this);

                exitDialog.setTitle("Game Over");

                exitDialog.setMessage("Your timing is: " + time + "s");

                exitDialog.setYesOnclickListener("New Game", new ExitDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        flushTime();
                        sound.stopWinSound();
                        Intent intent = new Intent(CardGameActivity.this, MainActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition

                    }
                });
                exitDialog.show();


            }
            //if highscore is found
            else {
                int previousHighScore = Integer.parseInt(readHighScore);
                //if current time is faster than highscore,overwrite the highscore
                if (time < previousHighScore) {
                    filePath = "SampleFolder";
                    fileName = "HighScore.txt";
                    mTargetFile = new File(getFilesDir(), filePath + "/" + fileName);
                    File parent = mTargetFile.getParentFile();
                    if (!parent.exists() && !parent.mkdirs()) {
                        throw new IllegalStateException("Cannot create dir: " + parent);
                    }
                    String highScoreTxt = String.valueOf(time);
                    try {
                        FileOutputStream fos = new FileOutputStream(mTargetFile);
                        fos.write(highScoreTxt.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sound.playWinSound();
                    exitDialog = new ExitDialog(CardGameActivity.this);

                    exitDialog.setTitle("Game Over");

                    exitDialog.setMessage("Your timing is: " + time + "s" + "\nCongratulations!\nNew Highscore: " + time + "s");

                    exitDialog.setYesOnclickListener("New Game", new ExitDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            flushTime();
                            sound.stopWinSound();
                            Intent intent = new Intent(CardGameActivity.this, MainActivity.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition

                        }
                    });
                    exitDialog.show();

                }
                //if current time is slower than high score,do not overwrite high score
                else {
                    sound.playWinSound();
                    exitDialog = new ExitDialog(CardGameActivity.this);

                    exitDialog.setTitle("Game Over");

                    exitDialog.setMessage("Your timing is: " + time + "s" + "\nHighscore is: " + previousHighScore + "s\nTry Harder!");

                    exitDialog.setYesOnclickListener("New Game", new ExitDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            flushTime();
                            sound.stopWinSound();
                            Intent intent = new Intent(CardGameActivity.this, MainActivity.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            overridePendingTransition(R.anim.screen_transition_fadein,R.anim.screen_transition_fadeout);                //screen transition

                        }
                    });
                    exitDialog.show();
                }
            }

        }

    }

    //to clear timer if user starts new game
    public void flushTime() {
        pauseTime = 0;
        millis = 0;
        finalTime = 0;
        seconds = 0;
        minutes = 0;
    }

    //method to reset all imageviews to card back design
    public void resetCardBack() {
        gamepic11.setImageResource(R.drawable.cardbg);
        gamepic12.setImageResource(R.drawable.cardbg);
        gamepic13.setImageResource(R.drawable.cardbg);
        gamepic14.setImageResource(R.drawable.cardbg);
        gamepic21.setImageResource(R.drawable.cardbg);
        gamepic22.setImageResource(R.drawable.cardbg);
        gamepic23.setImageResource(R.drawable.cardbg);
        gamepic24.setImageResource(R.drawable.cardbg);
        gamepic31.setImageResource(R.drawable.cardbg);
        gamepic32.setImageResource(R.drawable.cardbg);
        gamepic33.setImageResource(R.drawable.cardbg);
        gamepic34.setImageResource(R.drawable.cardbg);
    }

}
