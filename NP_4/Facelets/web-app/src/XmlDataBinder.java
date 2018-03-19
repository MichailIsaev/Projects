import parser.JAXBParser;
import parser.Parser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("result.xml")
public class XmlDataBinder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/xml");
        MultiplyBean multiplyBean = (MultiplyBean) session.getAttribute("multiplyBean");
        try (OutputStream outputStream = response.getOutputStream()) {
            Parser parser = new JAXBParser();
            parser.saveObject(outputStream, multiplyBean);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

    }
}
