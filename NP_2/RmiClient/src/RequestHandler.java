import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class RequestHandler {

    private Path originalImage;

    private static ImageTranslator translator;

    public RequestHandler(String path) {
        this.originalImage = Paths.get(path);
    }

    public void send() throws IOException, NotBoundException {

        translator = (ImageTranslator) Naming.lookup("//localhost/Translator");

        byte[] filtered = translator.translate(Files.readAllBytes(originalImage));

        BufferedImage filteredImage = ImageIO.read(new ByteArrayInputStream(filtered));

        ImageIO.write(filteredImage , "PNG" , new File("resources/filtered_image.png"));

    }
}
