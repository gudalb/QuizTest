package se.nackademin;

import java.util.*;

public class QuizGame {
    static List<QuizPlayer> players = new ArrayList<>();
    static int player1_score = 0;
    static int player2_score = 0;
    boolean player1_answered = false;
    boolean player2_answered = false;
    boolean someoneWaiting = false;


    public synchronized void setWaiting () throws InterruptedException {
        this.someoneWaiting = true;
        wait();
    }

    public synchronized void setNotWaiting () {
        this.someoneWaiting = false;
    }

    public synchronized void notifyAll_ () {
        notifyAll();
    }
    public synchronized boolean isSomeoneWaiting () {
        if (someoneWaiting) {
            return true;
        } else
            return false;
    }


    public synchronized void addPoints (String playerName) {
        if (playerName.equalsIgnoreCase("Player1"))
            player1_score ++;
        if (playerName.equalsIgnoreCase("Player2"))
            player2_score ++;
    }

    public synchronized void addAnswered (String playerName, boolean status) {
        if (playerName.equalsIgnoreCase("Player1"))
            player1_answered = status;
        if (playerName.equalsIgnoreCase("Player2"))
            player2_answered = status;
        if (bothAnswered()) {
            setCont();
        }
    }

    public synchronized void setCont () {
        for(QuizPlayer p : players) {
            p.cont = true;
        }
    }
    public synchronized boolean bothAnswered () {
        if(player2_answered && player1_answered)
            return true;
        else
            return false;
    }

    public synchronized String getPlayersScore () {
        return "p1: " + player1_score + " p2: " + player2_score;
    }

    public synchronized void addPlayer (QuizPlayer qp) {
        players.add(qp);
    }
}
