package com.android.thegeekers.tictactoc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public static Game game = new Game();
    private ImageView img;
    private Integer res;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener=
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                            focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
                            game.getStart().pause();
                            game.getStart().seekTo(0);
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
                    {
                        game.getStart().start();
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
                    {
                        relasesound();
                    }
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.sound);
    }
    @Override
    protected void onStart(){
        super.onStart();
        relasesound();
        game.setManageMain((AudioManager) getSystemService(Context.AUDIO_SERVICE));
        int result=game.getManageMain().requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED&&game.isSound())
        {
            game.setStart(MediaPlayer.create(this,R.raw.zig));
            game.getStart().start();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.getStart().pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        relasesound();

    }



    private void relasesound()
    {
        if(game.getStart()!=null&&game.isSound()){
            game.getStart().release();
            game.setStart(null);
            game.getManageMain().abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        game.getStart().start();
    }

    public void OnePlayer(View view) {

        if (game.isSound()) {
            game.setSoundclic(MediaPlayer.create(this,R.raw.o));
            onPause();
            game.getSoundclic().start();
            onResume();
        }
        game.setxWin(false);
        game.setoWin(false);
        game.setTie(false);
        game.setTie(0);
        game.setScoreO(0);
        game.setScoreT(0);
        game.setTwoPlayers(false);
        game.setOnePlayer(true);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Choose play mode");
        alert.setMessage("please Choose your gaming mode ??!");
        alert.setIcon(R.mipmap.xv);
        final Intent v = new Intent(this, second.class);

        alert.setNeutralButton("Easy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                game.setEasy(true);
                game.setHard(false);
                if (game.isSound()) {
                    onPause();
                    game.getSoundclic().start();
                    onResume();
                }
                startActivity(v);
            }
        });
        alert.setNegativeButton("Medium", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                game.setEasy(false);
                game.setHard(false);
                if (game.isSound()) {
                    onPause();
                    game.getSoundclic().start();
                    onResume();
                }
                startActivity(v);
            }
        });
        alert.setPositiveButton("Hard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               game.setEasy(false);
               game.setHard(true);
                if (game.isSound()) {
                    onPause();
                    game.getSoundclic().start();
                    onResume();
                }
                startActivity(v);
            }
        });
        AlertDialog alert1 = alert.create();
        alert1.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2f);
                negativeButton.setLayoutParams(params);
                negativeButton.invalidate();
            }
        });

        alert1.show();


    }

    public void TwoPlayers(View view) {

        if (game.isSound()) {
            game.setSoundclic(MediaPlayer.create(this,R.raw.o));
            onPause();
            game.getSoundclic().start();
            onResume();
        }
        game.setxWin(false);
        game.setoWin(false);
        game.setTie(false);
        game.setTie(0);
        game.setScoreO(0);
        game.setScoreT(0);
        game.setTwoPlayers(true);
        game.setOnePlayer(false);
        game.setEasy(false);
        game.setHard(false);
        Intent v = new Intent(this, second.class);
        startActivity(v);
    }
    public void teamInfo (View view)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("INFO");
        builder1.setMessage("The Geekers team presents Tic Tac Toe game app.\n" +
                "This game contians 2 options one Player and two Players, One player consist of 3 modes, first mode that's easy to you to win it will be very simple specially for kids to win, second is medium mode that you will need to think a little bit how to win and the last is hard mode that will be challange for you while you play the game and also we don't forget two players mode that's allow to you to play with your friends and have some fun time together.\n" +
                "The game comes with great sound effects and an easy user interface to use, and the default start in the game is to play with X.\n" +
                "For any suggestions or Problems Please contact with any member of our team.\n" +
                "Name: Ayman Adel Moner\n" +
                "E-Mail: aymanadel51@gmail.com\n" +
                "Name: Beshoy Bartous\n" +
                "E-Mail: beshoybartous4@gmail.com\n" +
                "Name: Aya Rabea\n" +
                "E-Mail: ayarabea378@gmail.com\n" +
                "Thank You to support us");
        builder1.setCancelable(true);
        builder1.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void sound (View view)
    {
        if (img.getTag()==null) {
            img.setTag(R.drawable.speaker);
            res =(Integer) img.getTag();
        }
        if ( res ==(R.drawable.speaker))
        {
            img.setImageResource(R.drawable.silent);
            img.setTag(R.drawable.silent);
            game.setSound(false);
            res=(Integer) img.getTag();
            game.getStart().stop();

        }
        else
        {
            img.setImageResource(R.drawable.speaker);
            img.setTag(R.drawable.speaker);
            game.setSound(true);
            res=(Integer)img.getTag();
            game.setStart(MediaPlayer.create(this,R.raw.zig));
            game.getStart().start();
        }
    }
}
