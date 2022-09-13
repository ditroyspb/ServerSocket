import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8090;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.printf("Новое подключение принято. Порт: %d%n", clientSocket.getPort());
                    out.println(String.format("Ваш порт - %d. Пожалуйста, назовите Ваше имя.", clientSocket.getPort()));
                    final String name = in.readLine();
                    System.out.println(name);
                    out.println(String.format("Добрый день, %s, Вы ребенок? (да/нет)", name));
                    final String childOrNot = in.readLine();
                    if (childOrNot.equals("да")) {
                        System.out.println(childOrNot);
                        out.println("Добро пожаловать на детскую арену, " + name + ". Давай играть!");
                    } else if (childOrNot.equals("нет")) {
                        System.out.println(childOrNot);
                        out.println("Добро пожаловать в зону, свободную от детей, " + name + "! Теперь ты сможешь хорошо провести время!");
                    }
                }
            }
        }
    }
}
