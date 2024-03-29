package se.nackademin;

import java.io.*;
import java.net.Socket;

public class QuizPlayer extends Thread {

    Socket socket;
    QuizGame game;
    String playerName;
    QuizPlayer opponentQP;
    int howManyQuestions = 4;
    PrintWriter output;
    BufferedReader input;
    public boolean cont = false;
    Thread opponentThread;

    public QuizPlayer (Socket clientSocket, QuizGame game, String playerName) {
        this.game = game;
        this.socket = clientSocket;
        this.playerName = playerName;

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            output.println("Hello " + playerName);
            if (playerName.equals("Player1"))
                output.println("Waiting for Players...\n");
        }
        catch (IOException e) {
            System.out.println("IO ERROR");
        }
    }

    public synchronized void run () {

        game.addPlayer(this);

        String playerAns = "";
        QuestionDB qdb = new QuestionDB();


        for (int i = 0; i < howManyQuestions; i++) {
            game.addAnswered(playerName, false);
            output.println(qdb.questionList.get(i).getQuestionText());
            try {
                playerAns = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            game.addAnswered(playerName, true);

            if (!game.isSomeoneWaiting()) {
                try {
                    game.setWaiting();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
                game.notifyAll_();

            game.setNotWaiting();


            if (playerAns.equalsIgnoreCase(qdb.questionList.get(i).getCorrectIndex())) {
                game.addPoints(playerName);
                output.println("player score ++");
            } else {
                output.println("wrong answer");
            }

        }

        //send game results
        output.println(game.getPlayersScore());


    }

    public void addOpponentQP (QuizPlayer qp) {
        this.opponentQP = qp;
    }

    public synchronized boolean stateWaiting () {
        if (getState() == State.WAITING)
            return true;
        else
            return false;
    }
    public void addOpponentThread (Thread t){
        this.opponentThread = t;
    }
    public void sendToClient (String s) {
        output.println(s);
    }

}
