
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SpecialsServlet"})
public class SpecialsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] specialsList = request.getParameterValues("specials");
        for (String specials : specialsList) {
            System.out.println(specials);
        }

        request.setAttribute("updated", specialsList.length);
        request.getRequestDispatcher("specials.jsp").forward(request, response);
    }
}
