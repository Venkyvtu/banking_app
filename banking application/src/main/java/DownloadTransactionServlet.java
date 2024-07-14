import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/DownloadTransactionServlet")
public class DownloadTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DownloadTransactionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"transactions.csv\"");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_apps", "root", "root");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE account_no = ? ORDER BY transaction_date DESC LIMIT 10");
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            out.println("Transaction ID,Transaction Date,Transaction Type,Amount,Balance");

            while (rs.next()) {
                out.println(rs.getString("transaction_id") + "," +
                            rs.getTimestamp("transaction_date") + "," +
                            rs.getString("transaction_type") + "," +
                            rs.getFloat("amount") + "," +
                            rs.getFloat("balance"));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
