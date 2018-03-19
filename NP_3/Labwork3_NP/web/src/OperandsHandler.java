import multiply.Multiply;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class OperandsHandler extends HttpServlet {
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(true);
        Multiply multiply = (Multiply) session.getAttribute("multiply");
        if (multiply != null) {
            multiply.multiply();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/result.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
