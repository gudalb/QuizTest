package se.nackademin;

import java.io.IOException;
import java.net.ServerSocket;

public class QuizServer {
    public static void main () throws IOException {
        ServerSocket listen = new ServerSocket(54321);
        System.out.println("Running");

        try {
            QuizGame game = new QuizGame();


            QuizPlayer player_1 = new QuizPlayer(listen.accept(), game, "Player1");
            QuizPlayer player_2 = new QuizPlayer(listen.accept(), game, "Player2");

            Thread p1 = new Thread(player_1);
            Thread p2 = new Thread(player_2);

            player_1.addOpponentThread(p2);
            player_2.addOpponentThread(p1);
            player_1.addOpponentQP(player_2);
            player_2.addOpponentQP(player_1);

            p1.start();
            p2.start();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listen.close();
        }
    }
}
