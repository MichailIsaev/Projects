import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {

            while (!serverSocket.isClosed()) {

                Socket client = serverSocket.accept();

                System.out.println(client);

                executor.execute(
                        new ClientHandler(client)
                );

            }

            executor.shutdown();

        } catch (IOException e) {
            return;
        }

    }
}
