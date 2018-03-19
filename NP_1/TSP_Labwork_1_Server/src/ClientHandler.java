import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

            double[][] a, b;

            while (!client.isClosed()) {

                a = (double[][]) in.readObject();
                b = (double[][]) in.readObject();

                Matrix A = new Matrix(a.length, a[0].length);
                A.setMatrix(a);

                Matrix B = new Matrix(b.length, b[0].length);
                B.setMatrix(b);

                out.writeObject(Matrix.add(A, B).getMatrix());

            }

            client.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client has closed already.");
        }
    }
}
