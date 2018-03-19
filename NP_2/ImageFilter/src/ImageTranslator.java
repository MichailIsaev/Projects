import java.io.IOException;
import java.rmi.Remote;

public interface ImageTranslator extends Remote {
    public byte[] translate(byte[] image) throws IOException;
}
