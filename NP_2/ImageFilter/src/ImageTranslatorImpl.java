import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class ImageTranslatorImpl extends UnicastRemoteObject implements ImageTranslator {

    private final static int SIZE = 3;

    private static final long serialVersionUID = 1L;

    protected ImageTranslatorImpl() throws RemoteException {
        super();
    }

    public byte[] translate(byte[] image) throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(byteArrayInputStream);
        byteArrayInputStream.close();

        int height = originalImage.getHeight();
        int width = originalImage.getWidth();

        int square[];
        int median;

        for (int i = 0; i < height - SIZE + 1; i++) {
            for (int j = 0; j < width - SIZE + 1; j++) {
                square = originalImage.getRGB(j, i, SIZE, SIZE, null, 0, SIZE);
                median = getMedian(square);
                originalImage.setRGB(j + ((SIZE) / 2), i + ((SIZE) / 2), median);
            }
        }


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(originalImage, "png", byteArrayOutputStream);

        byteArrayOutputStream.flush();

        byte[] filtered = byteArrayOutputStream.toByteArray();

        byteArrayOutputStream.close();

        return filtered;
    }


    private static int getMedian(int[] data) {
        Arrays.sort(data);
        return data[(data.length) / 2];
    }

    public static void main(String[] args) throws IOException {
        try {
            Naming.rebind("//localhost/Translator", new ImageTranslatorImpl());
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
