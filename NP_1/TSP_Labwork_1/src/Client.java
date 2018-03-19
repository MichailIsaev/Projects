import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        try (Socket socket = new Socket("localhost", 5555);
             BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Matrix C;

            double[][] result;

            while (!socket.isClosed()) {

                if (bufferedInputStream.ready()) {

                    String[] files = bufferedInputStream.readLine().split(" ");

                    Matrix a = Matrix.read(Paths.get("resources/" + files[0] + ".txt"));
                    Matrix b = Matrix.read(Paths.get("resources/" + files[1] + ".txt"));

                    if (a != null && b != null) {

                        out.writeObject(a.getMatrix());
                        out.writeObject(b.getMatrix());

                        if (in.available() != -1) {
                            result = (double[][]) in.readObject();
                            C = new Matrix(result.length, result[0].length);
                            C.setMatrix(result);
                            Matrix.write(Paths.get("resources/" + files[2] + ".txt"), C);
                        }

                    }
                    else{
                        System.out.println("Check matrix dimensions.");
                    }
                    break;
                }

            }

        } catch (IOException e) {
            System.out.println("Some IO problems");
        }
    }
}
