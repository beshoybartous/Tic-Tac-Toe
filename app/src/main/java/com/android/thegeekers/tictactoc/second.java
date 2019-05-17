package com.android.thegeekers.tictactoc;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.android.thegeekers.tictactoc.MainActivity.game;


public class second extends AppCompatActivity {
    private boolean firstTime = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        game.setImg(((ImageButton) findViewById(R.id.img1)), 0);
        game.setImg(((ImageButton) findViewById(R.id.img2)), 1);
        game.setImg(((ImageButton) findViewById(R.id.img3)), 2);
        game.setImg(((ImageButton) findViewById(R.id.img4)), 3);
        game.setImg(((ImageButton) findViewById(R.id.img5)), 4);
        game.setImg(((ImageButton) findViewById(R.id.img6)), 5);
        game.setImg(((ImageButton) findViewById(R.id.img7)), 6);
        game.setImg(((ImageButton) findViewById(R.id.img8)), 7);
        game.setImg(((ImageButton) findViewById(R.id.img9)), 8);
        game.newGame();
        for (int i = 0; i < 9; i++) {
            game.getImg(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer res = (Integer) ((ImageView) view).getTag();
                    if (game.isSound())
                        game.releaseSoundClic();
                    if (game.isxWin()||game.isoWin()||game.isTie())
                    {
                        showToast('q');
                    }
                    else if ((game.getTurn() == 'x' && !game.isxWin() && !game.isoWin()&&!game.isTie())) {
                        if (res == null) {
                            ((ImageView) view).setImageResource(R.drawable.x6);
                            ((ImageView) view).setTag(R.drawable.x6);
                            game.playAt();
                            if (!game.doChecks())
                                game.nextTurn();
                            game.mediaPlayerLoad(view, 'x');
                        }

                    } else if (game.getTurn() == 'o' && game.isTwoPlayers() && !game.isxWin() && !game.isoWin()&&!game.isTie()) {
                        if (res == null) {
                            ((ImageView) view).setImageResource(R.drawable.o5);
                            ((ImageView) view).setTag(R.drawable.o5);
                            game.playAt();
                            if (!game.doChecks())
                                game.nextTurn();
                            game.mediaPlayerLoad(view, 'o');
                        }

                    }
                    if (game.doChecks()&&!firstTime) {
                        if (game.isxWin()) {
                            showToast('x');
                            game.changeWinningColor('x');
                            if (!game.isWinSound()&&game.isSound()) {
                                game.mediaPlayerLoad(view, 'w');
                                game.setWinSound(true);
                            }
                            if (!firstTime) {
                                game.incScoreO();
                                displayScoreO();
                                firstTime=true;
                            }
                        } else if (game.isoWin()) {
                            int v = 0;
                            if (game.isOnePlayer())
                                v = 400;
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    game.changeWinningColor('o');
                                    if (game.isTwoPlayers())
                                        showToast('o');
                                    else
                                        showToast('l');
                                }
                            }, v);
                            if (!game.isWinSound()&&game.isSound()) {
                                if (game.isTwoPlayers())
                                game.mediaPlayerLoad(view, 'w');
                                else
                                    game.mediaPlayerLoad(view, 'l');
                                game.setWinSound(true);
                            }
                            if (!firstTime) {
                                game.incScoreT();
                                displayScoreT();
                                firstTime= true;
                            }
                        } else {
                            showToast('t');
                            if (!game.isTieSound()&&game.isSound()) {
                                game.mediaPlayerLoad(view, 't');
                                game.setTieSound(true);
                            }
                            if (!firstTime) {
                                game.incTie();
                                displayTie();
                                firstTime=true;
                            }
                        }
                    }
                }
            });
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        game.releaseSoundClic();
    }
    public void ng(View view) {
        game.newGame();
        game.setxWin(false);
        game.setoWin(false);
        game.setTie(false);
        game.setTieSound(false);
        game.setWinSound(false);
        firstTime = false;
        game.mediaPlayerLoad(view, 'x');
    }

    private void displayTie() {

        TextView TIE = (TextView) findViewById(R.id.tie);

        TIE.setText(String.valueOf(game.getTie()));

    }

    private void displayScoreO() {

        TextView scoreOneTxtView = (TextView) findViewById(R.id.ScoreO);

        scoreOneTxtView.setText(String.valueOf(game.getScoreO()));

    }

    private void displayScoreT() {

        TextView scoreTwoTxtView = (TextView) findViewById(R.id.ScoreT);

        scoreTwoTxtView.setText(String.valueOf(game.getScoreT()));

    }
    private void showToast(char var)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage= layout.findViewById(R.id.toast_image);
        if (var=='x')
        {
            toastText.setText("X Win You are our new winner");
            toastText.setTextColor(this.getResources().getColor(R.color.red));
            toastImage.setImageResource(R.drawable.win);
        }
       else if(var =='o')
        {
            toastText.setText("O Win You are our new winner");
            toastText.setTextColor(this.getResources().getColor(R.color.red));
            toastImage.setImageResource(R.drawable.win);
        }
        else if(var =='l')
        {
            toastText.setText("O Win You are a loser");
            toastText.setTextColor(this.getResources().getColor(R.color.darkgreen));
            toastImage.setImageResource(R.drawable.lose);
        }
        else if (var=='t')
        {
            toastText.setText("Tie");
            toastText.setTextColor(this.getResources().getColor(R.color.black));
            toastImage.setImageResource(R.drawable.tie);
        }
        else if (var=='q')
        {
            toastText.setText("Please press refresh icon");
            toastText.setTextColor(this.getResources().getColor(R.color.black));
            toastImage.setImageResource(R.drawable.play);
        }
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}

