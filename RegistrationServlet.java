import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("full-name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);

        int userId = UserDAO.registerUser(user);

        if (userId != -1) {
            // Set user ID as a session attribute
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);

            // Redirect to a thank-you page or display a success message
            response.sendRedirect("thankyou.html");
        } else {
            // Handle registration error
            response.sendRedirect("error.html");
        }
    }
}
