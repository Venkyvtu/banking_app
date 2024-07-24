package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import java.sql.SQLException;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DepositServlet() {
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
                // Calculate new balance after deposit
                double newBalance = currentBalance + amount;

                // Update balance in the database
                if (customerDAO.updateBalance(username, newBalance)) {
                    session.setAttribute("balance", newBalance);
                    // Insert transaction record into transactions table
                    customerDAO.insertTransaction(username, "Deposit", amount, newBalance);
                    out.println("<html><body><script>");
                    out.println("alert('Successfully deposited amount');");
                    out.println("window.location.href = 'deposit.jsp';");
                    out.println("</script></body></html>");
                } else {
                    out.println("<html><body><script>");
                    out.println("alert('Failed to update balance. Please try again.');");
                    out.println("window.location.href = 'deposit.jsp';");
                    out.println("</script></body></html>");
                }
            } else {
                out.println("<html><body><script>");
                out.println("alert('Failed to retrieve current balance. Please try again.');");
                out.println("window.location.href = 'deposit.jsp';");
                out.println("</script></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><script>");
            out.println("alert('An unexpected error occurred. Please try again later.');");
            out.println("window.location.href = 'deposit.jsp';");
            out.println("</script></body></html>");
        } finally {
            out.close();
        }
    }
}
