package controller;

import dao.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

@WebServlet("/registerCustomerServlet")
@MultipartConfig
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String gender=request.getParameter("gender");
        String idProofType = request.getParameter("idProofType");
        String idProofNumber = idProofType.equals("PAN") ? request.getParameter("panNumber") : request.getParameter("aadharNumber");
       
        
        Customer customer = new Customer(fullName, address, mobileNo, email, accountType, initialBalance, dob,gender, idProofType,idProofNumber);

        try {
            customerDAO.registerCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("customeCheck.jsp");
    }
}
