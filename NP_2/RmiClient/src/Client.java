import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

public class Client {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            RequestHandler handler = new RequestHandler("resources/" + reader.readLine() + ".jpg");
            handler.send();

        } catch (IOException | NotBoundException e) {
            System.out.println("Check your connection or data!");
            System.out.println(e.getStackTrace());
        }

    }
}
