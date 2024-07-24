package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.EmailUtil;
import dao.CustomerDAO;
import java.sql.SQLException;

@WebServlet("/withdrawServlet")
public class withdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public withdrawServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Get parameters from the form
        String amountStr = request.getParameter("amount");
        double amount = Double.parseDouble(amountStr); // Convert amount to double

        // Retrieve session attributes
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        CustomerDAO customerDAO = new CustomerDAO();

        try {
            // Retrieve current balance
            double currentBalance = customerDAO.getCurrentBalance(username);

            if (currentBalance >= 0) {
                // Calculate new balance after withdrawal
                double newBalance = currentBalance - amount;

                if (newBalance < 0) {
                    out.println("<html><body><script>");
                    out.println("alert('Insufficient balance.');");
                    out.println("window.location.href = 'withdraw.jsp';");
                    out.println("</script></body></html>");
                } else {
                    // Update balance in the database
                    if (customerDAO.updateBalance(username, newBalance)) {
                        session.setAttribute("balance", newBalance);
                        // Insert transaction record into transactions table
                        customerDAO.insertTransaction(username, "Withdraw", amount, newBalance);
                       
                    

                        out.println("<html><body><script>");
                        out.println("alert('Successfully withdrawn amount');");
                        out.println("window.location.href = 'withdraw.jsp';");
                        out.println("</script></body></html>");
                    } else {
                        out.println("<html><body><script>");
                        out.println("alert('Failed to update balance. Please try again.');");
                        out.println("window.location.href = 'withdraw.jsp';");
                        out.println("</script></body></html>");
                    }
                }
            } else {
                out.println("<html><body><script>");
                out.println("alert('Failed to retrieve current balance. Please try again.');");
                out.println("window.location.href = 'withdraw.jsp';");
                out.println("</script></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><script>");
            out.println("alert('An unexpected error occurred. Please try again later.');");
            out.println("window.location.href = 'withdraw.jsp';");
            out.println("</script></body></html>");
        } finally {
            out.close();
        }
    }
}
