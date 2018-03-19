import java.io.IOException;

public interface ImageTranslator {
    public byte[] translate(byte[] image) throws IOException;
}