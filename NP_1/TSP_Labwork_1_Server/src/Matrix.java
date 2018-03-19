import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class Matrix implements Serializable {

    private double[][] matrix;

    private int m, n;

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.matrix = new double[m][n];
    }

    public int getColumns() {
        return this.n;
    }

    public int getRows() {
        return this.m;
    }

    public void setElement(int m, int n, double element) {
        this.matrix[m][n] = element;
    }

    public double getElement(int m, int n) {
        return this.matrix[m][n];
    }

    public static Matrix add(Matrix first, Matrix second) {
        for (int i = 0; i < second.getRows(); i++) {
            for (int j = 0; j < second.getColumns(); j++) {
                first.matrix[i][j] += second.matrix[i][j];
            }
        }
        return first;
    }

    public static Matrix read(Path path) {
        Matrix matrix = null;
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            int i = 0;
            bufferedReader.mark((int) Files.size(path));
            int m = (int) Files.lines(path).count();
            bufferedReader.reset();
            double[][] array = new double[m][];
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] elements = row.split(" ");
                int n = elements.length;
                array[i] = new double[n];
                for (int j = 0; j < n; j++) {
                    array[i][j] = Double.valueOf(elements[j]);
                }
                i++;
                matrix = new Matrix(m, n);
                matrix.setMatrix(array);
            }
        } catch (IOException e) {
            System.out.println("Some troubles while reading matrix.");
        }
        return matrix;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++)
                out += this.matrix[i][j] + " ";
            out += '\n';
        }
        return out;
    }


    public static void write(Path path, Matrix matrix) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = Files.newBufferedWriter(path);
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++)
                    bufferedWriter.write(String.valueOf(matrix.getElement(i, j)) + " ");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            bufferedWriter.write("Some troubles while writing ");
        } finally {
            bufferedWriter.close();
        }
    }

}
