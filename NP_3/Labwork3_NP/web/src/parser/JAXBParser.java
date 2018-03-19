package parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.OutputStream;

public class JAXBParser implements Parser {
    @Override
    public Object getObject(File file, Class c) throws JAXBException {
        return JAXBContext.newInstance(c).createUnmarshaller().unmarshal(file);
    }

    @Override
    public void saveObject(OutputStream outputStream , Object o) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(o, outputStream);
    }
}
