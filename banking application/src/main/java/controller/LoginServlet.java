package controller;

import model.UserLoginResult;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();

        LoginDAO obj = new LoginDAO();
        UserLoginResult result = obj.validate(username, password);

        if (result == null) {
        	out.println("<html><body><script>");
            out.println("alert('Your Enter username and password is wrongly.');");
            out.println("window.location.href = 'login.jsp';");
            out.println("</script></body></html>");
            
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", result.getRole());
            session.setAttribute("firstLogin", result.isFirstLogin());
            session.setAttribute("fullName", result.getFullName());
            session.setAttribute("accountNumber", result.getAccountNumber());
            session.setAttribute("balance", result.getBalance());

            if (result.getRole().equalsIgnoreCase("admin")) {
                response.sendRedirect("welcome.jsp");
            } else if (result.getRole().equalsIgnoreCase("user") && result.isFirstLogin()) {
                response.sendRedirect("changepassword.jsp");
            } else if(result.getRole().equalsIgnoreCase("user") && !result.isFirstLogin()) {
                response.sendRedirect("user_dashboard.jsp");
            } else {
                response.sendRedirect("default.jsp");
            }
        }
    }
}
