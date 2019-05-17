package com.android.thegeekers.tictactoc;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class Game {
    private boolean twoPlayers;
    private boolean onePlayer;
    private char[][] grid;
    private int freeSpots;
    private int scoreO;
    private boolean easy;
    private int scoreT;
    private int tie;
    private char turn;
    private int oplacee;
    private boolean xWin;
    private boolean oWin;
    private boolean Tie;
    private ImageButton[] img;
    private Integer[] res;
    private boolean hard;
    private AudioManager manageMain;
    private MediaPlayer start;
    private  MediaPlayer soundclic;
    private boolean winSound;
    private boolean tieSound;
    private boolean Sound;
    public boolean isSound() {
        return Sound;
    }

    public void setSound(boolean sound) {
        Sound = sound;
    }



    public boolean isWinSound() {
        return winSound;
    }

    public void setWinSound(boolean winSound) {
        this.winSound = winSound;
    }

    public boolean isTieSound() {
        return tieSound;
    }

    public void setTieSound(boolean tieSound) {
        this.tieSound = tieSound;
    }



    public AudioManager getManageMain() {
        return manageMain;
    }

    public void setManageMain(AudioManager manage) {
        this.manageMain = manage;
    }


    public MediaPlayer getStart() {
        return start;
    }

    public void setStart(MediaPlayer start) {
        this.start = start;
    }

    public MediaPlayer getSoundclic() {
        return soundclic;
    }

    public void setSoundclic(MediaPlayer soundclic) {
        this.soundclic = soundclic;
    }


    Game() {
        twoPlayers = false;
        onePlayer = false;
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '-';
            }
        }
        freeSpots = 9;
        scoreO = 0;
        easy = false;
        scoreT = 0;
        tie = 0;
        turn = 'x';
        xWin = false;
        oWin = false;
        Tie = false;
        hard = false;
        oplacee = -1;
        winSound=false;
        tieSound=false;
        img = new ImageButton[9];
        res = new Integer[9];
        Sound=true;
    }

    public boolean getHard() {
        return hard;
    }

    public void setHard(boolean gameMode) {
        this.hard = gameMode;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public void setImg(ImageButton img, int x) {
        this.img[x] = img;
    }

    public boolean isTwoPlayers() {
        return twoPlayers;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
    }

    public boolean isOnePlayer() {
        return onePlayer;
    }

    public void setOnePlayer(boolean onePlayer) {
        this.onePlayer = onePlayer;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public int getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(int freeSpots) {
        this.freeSpots = freeSpots;
    }

    public int getScoreO() {
        return scoreO;
    }

    public void setScoreO(int scoreO) {
        this.scoreO = scoreO;
    }

    public boolean isEasy() {
        return easy;
    }

    public void setEasy(boolean easy) {
        this.easy = easy;
    }

    public int getScoreT() {
        return scoreT;
    }

    public void setScoreT(int scoreT) {
        this.scoreT = scoreT;
    }

    public int getTie() {
        return tie;
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }

    public boolean isxWin() {
        return xWin;
    }

    public void setxWin(boolean xWin) {
        this.xWin = xWin;
    }

    public boolean isoWin() {
        return oWin;
    }

    public void setoWin(boolean oWin) {
        this.oWin = oWin;
    }

    public boolean isTie() {
        return Tie;
    }

    public void setTie(boolean tie) {
        Tie = tie;
    }

    public ImageButton getImg(int x) {
        return img[x];
    }

    public Integer[] getRes() {
        return res;
    }

    public void setRes(Integer[] res) {
        this.res = res;
    }

    public void newGame() {

        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '-';
            }
        }
        freeSpots = 9;
        turn = 'x';
        winSound=false;
        tieSound=false;
        removeImg();
        resetTags();

    }

    public void incScoreO() {
        scoreO++;
    }

    public void incScoreT() {
        scoreT++;
    }

    public void incTie() {
        tie++;
    }

    public void resetTags() {
        for (int i = 0; i < 9; i++) {
            img[i].setTag(null);
            res[i] = (Integer) img[i].getTag();
        }
    }

    public void removeImg() {
        for (int i = 0; i < 9; i++) {
            img[i].setImageResource(android.R.color.transparent);
        }

    }

    public boolean doChecks() {
        String winnerMessage = checkGameWinner();
        if (!winnerMessage.equals("None")) {
            if (winnerMessage == "X Win") {
                xWin = true;
            } else if (winnerMessage == "O Win") {
                oWin = true;
            } else if (winnerMessage == "Tie") {
                Tie = true;
            }
            return true;
        }
        return false;
    }

    public boolean defand() {
        if (freeSpots == 8) {
            if (grid[1][1] == 'x') {
                int Place = (int) (Math.random() * 4);
                if (Place == 0) {
                    grid[0][0] = 'o';
                    setO(0);
                    oplacee = 0;
                } else if (Place == 1) {
                    grid[0][2] = 'o';
                    setO(2);
                    oplacee = 2;
                } else if (Place == 2) {
                    grid[2][0] = 'o';
                    setO(6);
                    oplacee = 6;
                } else {
                    grid[2][2] = 'o';
                    setO(8);
                    oplacee = 8;
                }
                return true;
            } else if (hard && grid[1][1] == '-') {
                grid[1][1] = 'o';
                setO(4);
                return true;
            }
        }

        else if (freeSpots == 6 && hard==true) {
            int Placew = (int) (Math.random() * 2);
            if(CheckTwoCells()){
                return true;
            }
            return hardcase();
        }
        return false;
    }

    public boolean hardcase() {
        int Place = (int) (Math.random() * 2);
        if((grid[0][0]=='x'&&grid[2][2]=='x')||(grid[0][2]=='x'&&grid[2][0]=='x')){
            if(Place==0){
                grid[1][0]='o';
                setO(3);
            }
            else if(Place==1){
                grid[1][2]='o';
                setO(5);
            }
            return true;
        }
        if ((oplacee == 0 || oplacee == 8) && (grid[0][0] == 'x' || grid[2][2] == 'x')) {
            if (Place == 0) {
                grid[0][2] = 'o';
                setO(2);

            } else {
                grid[2][0] = 'o';
                setO(6);
            }
            return true;
        }
        if ((oplacee == 2 || oplacee == 6) && (grid[0][2] == 'x' || grid[2][0] == 'x')) {
            if (Place == 0) {
                grid[0][0] = 'o';
                setO(0);
            } else {
                grid[2][2] = 'o';
                setO(8);
            }
            return true;
        }

        return false;
    }

    public int returnCell(char type) {
        int place1 = -1, place2 = -1, place3 = -1;
        //search row
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                if (grid[i][j] == type && place1 == -1) {
                    place1 = j;
                } else if (grid[i][j] == type && place1 != -1 && place2 == -1) {
                    place2 = j;
                }
            }
            if (place1 != -1 && place2 != -1 && place1 != place2) {
                if (place1 == 0 && place2 == 1) {
                    place3 = 2;
                } else if (place1 == 1 && place2 == 2) {
                    place3 = 0;
                } else if (place1 == 0 && place2 == 2) {
                    place3 = 1;
                }
            }
            if (place3 != -1 && grid[i][place3] != '-') {
                place3 = -1;
            } else if (place3 != -1 && grid[i][place3] == '-') {
                grid[i][place3] = 'o';
                if (i == 0) {
                    setO((i + place3));
                    return place3;
                } else if (i == 1) {
                    setO((i + place3 + 2));
                    return place3;
                } else if (i == 2) {
                    setO((i + place3 + 4));
                    return place3;
                }
            }
            place1 = place2 = place3 = -1;

        }
        //search column
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == type && place1 == -1) {
                    place1 = j;
                } else if (grid[j][i] == type && place1 != -1 && place2 == -1) {
                    place2 = j;
                }
            }
            if (place1 != -1 && place2 != -1 && place1 != place2) {
                if (place1 == 0 && place2 == 1) {
                    place3 = 2;
                } else if (place1 == 1 && place2 == 2) {
                    place3 = 0;
                } else if (place1 == 0 && place2 == 2) {
                    place3 = 1;
                }
            }
            if (place3 != -1 && grid[place3][i] != '-') {
                place3 = -1;
            } else if (place3 != -1 && grid[place3][i] == '-') {
                grid[place3][i] = 'o';
                if (place3 == 0) {
                    setO((i + place3));
                    return place3;
                } else if (place3 == 1) {
                    setO((i + place3 + 2));
                    return place3;
                } else if (place3 == 2) {
                    setO((i + place3 + 4));
                    return place3;
                }
            }
            place1 = place2 = place3 = -1;
        }
        //daig
        for (int i = 0; i < 3; i++) {

            if (grid[i][i] == type && place1 == -1) {
                place1 = i;
            } else if (grid[i][i] == type && place1 != -1 && place2 == -1) {
                place2 = i;
            }
            if (place1 != -1 && place2 != -1 && place1 != place2) {
                if (place1 == 0 && place2 == 1)
                    place3 = 2;
                else if (place1 == 0 && place2 == 2)
                    place3 = 1;
                else if (place1 == 1 && place2 == 2) {
                    place3 = 0;
                }
            }
            if (place3 != -1 && grid[place3][place3] != '-') {
                place3 = -1;
            } else if (place3 != -1 && grid[place3][place3] == '-') {
                grid[place3][place3] = 'o';
                if (place3 == 0) {
                    setO((place3 + place3));
                    return place3;
                } else if (place3 == 1) {
                    setO((place3 + place3 + 2));
                    return place3;
                } else if (place3 == 2) {
                    setO((place3 + place3 + 4));
                    return place3;
                }
            }
        }
        place1 = place2 = place3 = -1;
        if (grid[0][2] == grid[1][1] && grid[0][2] == type && grid[2][0] == '-' && place3 == -1) {
            place3 = 2;
            grid[place3][0] = 'o';
            setO(6);
            return place3;
        }

        if (grid[0][2] == grid[2][0] && grid[0][2] == type && grid[1][1] == '-' && place3 == -1) {
            place3 = 1;
            grid[place3][place3] = 'o';
            setO(4);
            return place3;
        }
        if (grid[2][0] == grid[1][1] && grid[2][0] == type && grid[0][2] == '-' && place3 == -1) {
            place3 = 0;
            grid[place3][2] = 'o';
            setO(2);
            return place3;
        }
        return place3;
    }

    public boolean CheckTwoCells() {
        boolean found = false;
        int defend = returnCell('o');
        if (defend != -1) {
            found = true;
        }
        if (!found) {
            defend = returnCell('x');
            if (defend != -1) {
                found = true;
            }
        }

        return found;
    }

    public String checkGameWinner() {
        String result = "None";
        boolean equals = false;
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] != '-') {
                equals = true;
                if (grid[i][0] == 'x') {
                    result = "X Win";
                } else {
                    result = "O Win";
                }
                break;
            } else if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] != '-') {
                equals = true;
                if (grid[0][i] == 'x') {
                    result = "X Win";
                } else {
                    result = "O Win";
                }
                break;
            }
        }
        if (result == "None") {
            if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] != '-') {
                equals = true;
                if (grid[0][0] == 'x') {
                    result = "X Win";
                } else {
                    result = "O Win";
                }
            } else if (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] != '-') {
                equals = true;
                if (grid[2][0] == 'x') {
                    result = "X Win";
                } else {
                    result = "O Win";
                }
            }
        }
        if (equals == false) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == '-')
                        return result;
                }
            }
            return "Tie";
        }
        return result;

    }
    public void changeWinningColor(char win)
    {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] != '-') {
                if (win == 'x') {
                    if (i==0)
                    {
                        img[0].setImageResource(R.drawable.x7);
                        img[1].setImageResource(R.drawable.x7);
                        img[2].setImageResource(R.drawable.x7);
                    }
                    else if (i==1)
                    {
                        img[3].setImageResource(R.drawable.x7);
                        img[4].setImageResource(R.drawable.x7);
                        img[5].setImageResource(R.drawable.x7);
                    }
                    else if (i==2) {
                        img[6].setImageResource(R.drawable.x7);
                        img[7].setImageResource(R.drawable.x7);
                        img[8].setImageResource(R.drawable.x7);
                    }
                } else if (win=='o') {
                    if (i==0)
                    {
                        img[0].setImageResource(R.drawable.o7);
                        img[1].setImageResource(R.drawable.o7);
                        img[2].setImageResource(R.drawable.o7);
                    }
                    else if (i==1)
                    {
                        img[3].setImageResource(R.drawable.o7);
                        img[4].setImageResource(R.drawable.o7);
                        img[5].setImageResource(R.drawable.o7);
                    }
                    else if (i==2) {
                        img[6].setImageResource(R.drawable.o7);
                        img[7].setImageResource(R.drawable.o7);
                        img[8].setImageResource(R.drawable.o7);
                    }

                }
                break;
            } else if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] != '-') {
                if (win == 'x') {
                    if (i==0)
                    {
                        img[0].setImageResource(R.drawable.x7);
                        img[3].setImageResource(R.drawable.x7);
                        img[6].setImageResource(R.drawable.x7);
                    }
                    else if (i==1)
                    {
                        img[1].setImageResource(R.drawable.x7);
                        img[4].setImageResource(R.drawable.x7);
                        img[7].setImageResource(R.drawable.x7);
                    }
                    else if (i==2) {
                        img[2].setImageResource(R.drawable.x7);
                        img[5].setImageResource(R.drawable.x7);
                        img[8].setImageResource(R.drawable.x7);
                    }


                } else if(win =='o') {
                    if (i==0)
                    {
                        img[0].setImageResource(R.drawable.o7);
                        img[3].setImageResource(R.drawable.o7);
                        img[6].setImageResource(R.drawable.o7);
                    }
                    else if (i==1)
                    {
                        img[1].setImageResource(R.drawable.o7);
                        img[4].setImageResource(R.drawable.o7);
                        img[7].setImageResource(R.drawable.o7);
                    }
                    else if (i==2) {
                        img[2].setImageResource(R.drawable.o7);
                        img[5].setImageResource(R.drawable.o7);
                        img[8].setImageResource(R.drawable.o7);
                    }

                }
                break;
            }
        }
            if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] != '-') {
                if (win== 'x') {

                    img[0].setImageResource(R.drawable.x7);
                    img[4].setImageResource(R.drawable.x7);
                    img[8].setImageResource(R.drawable.x7);
                } else if (win=='o') {

                    img[0].setImageResource(R.drawable.o7);
                    img[4].setImageResource(R.drawable.o7);
                    img[8].setImageResource(R.drawable.o7);
                }
            } else if (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] != '-') {
                if (win == 'x') {
                    img[2].setImageResource(R.drawable.x7);
                    img[4].setImageResource(R.drawable.x7);
                    img[6].setImageResource(R.drawable.x7);
                } else if (win=='o') {
                    img[2].setImageResource(R.drawable.o7);
                    img[4].setImageResource(R.drawable.o7);
                    img[6].setImageResource(R.drawable.o7);

                }
            }

    }

    public void nextTurn() {
        if (!twoPlayers) {
            if (freeSpots == 0) {
                return;
            }

            if ((easy) || (!defand()&&!CheckTwoCells())) {
                int ai_i, ai_j;
                do {
                    ai_i = (int) (Math.random() * 3);
                    ai_j = (int) (Math.random() * 3);
                } while (grid[ai_i][ai_j] != '-');
                grid[ai_i][ai_j] = 'o';
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ai_i == i && ai_j == j) {
                            if (ai_i == 0) {
                                setO((i + j));
                                break;
                            } else if (ai_i == 1) {
                                setO((i + j + 2));
                                break;
                            } else if (ai_i == 2) {
                                setO((i + j + 4));
                                break;
                            }
                        }
                    }
                }
            }

        } else {
            if (turn == 'x') {
                turn = 'o';
            } else {
                turn = 'x';
            }
        }
        freeSpots--;
        return;
    }

    public void playAt() {

        for (int i = 0; i < 9; i++) {
            if (img[i].getTag() != res[i]) {
                if ((Integer) img[i].getTag() == R.drawable.x6) {
                    if (i < 3) {
                        grid[0][i] = 'x';
                    } else if (i > 2 && i < 6) {
                        grid[1][i - 3] = 'x';
                    } else if (i > 5) {
                        grid[2][i - 6] = 'x';
                    }
                    res[i] = (Integer) img[i].getTag();
                } else if ((Integer) img[i].getTag() == R.drawable.o5) {
                    if (i < 3) {
                        grid[0][i] = 'o';
                    } else if (i > 2 && i < 6) {
                        grid[1][i - 3] = 'o';
                    } else if (i > 5) {
                        grid[2][i - 6] = 'o';
                    }
                    res[i] = (Integer) img[i].getTag();
                }
                freeSpots--;
                break;
            }
        }
    }

    public void setO(final int x) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img[x].setImageResource(R.drawable.o5);
            }
        }, 400);
        img[x].setTag(R.drawable.o5);
        res[x] = (Integer) img[x].getTag();

    }
    public void releaseSoundClic()
    {
        if (soundclic!=null){
           soundclic.release();
            soundclic=null;
        }
    }
    public void mediaPlayerLoad(final View view, char type){
        if(Sound) {
            releaseSoundClic();
            if (type == 'o')
                soundclic = MediaPlayer.create(view.getContext(), R.raw.o);
            else if (type == 'x')
                soundclic = MediaPlayer.create(view.getContext(), R.raw.x);
            else if (type == 'w')
                soundclic = MediaPlayer.create(view.getContext(), R.raw.tada);
            else if (type == 't')
                soundclic = MediaPlayer.create(view.getContext(), R.raw.tie);
            else if (type=='l')
                soundclic = MediaPlayer.create(view.getContext(), R.raw.losing);
            soundclic.start();
        }
    }

}
