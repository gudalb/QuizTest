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
            player_1.start();
            player_2.start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listen.close();
        }
    }
}
