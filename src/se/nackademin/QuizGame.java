package se.nackademin;

import java.util.*;

public class QuizGame {
    static List<QuizPlayer> players = new ArrayList<>();
    static int player1_score = 0;
    static int player2_score = 0;
    boolean player1_answered = false;
    boolean player2_answered = false;


    public void addPoints (String playerName) {
        if (playerName.equalsIgnoreCase("Player1"))
            player1_score ++;
        if (playerName.equalsIgnoreCase("Player2"))
            player2_score ++;
    }

    public void addAnswered (String playerName, boolean status) {
        if (playerName.equalsIgnoreCase("Player1"))
            player1_answered = status;
        if (playerName.equalsIgnoreCase("Player2"))
            player2_answered = status;
        if (bothAnswered()) {
            setCont();
        }
    }

    public void setCont () {
        for(QuizPlayer p : players) {
            p.cont = true;
        }
    }
    public boolean bothAnswered () {
        if(player2_answered && player1_answered)
            return true;
        else
            return false;
    }

    public String getPlayersScore () {
        return "p1: " + player1_score + " p2: " + player2_score;
    }

    public void addPlayer (QuizPlayer qp) {
        players.add(qp);
    }
}
