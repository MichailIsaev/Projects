package parser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.OutputStream;

public interface Parser {
    Object getObject(File file, Class c) throws JAXBException;

    void saveObject(OutputStream outputStream, Object o) throws JAXBException;
}
