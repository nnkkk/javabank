package jp;
//Controller
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Operation", urlPatterns = {"/Operation"})
public class Operation extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        try {
            
            BankRemote b = (BankRemote)session.getAttribute("remote");
            AccountBeanLocal local = (AccountBeanLocal)session.getAttribute("accountBean");
            Account account = (Account)session.getAttribute("account");
            
            System.out.println("operation start: " + account.getDeposit());
            
            String operation = request.getParameter("operation");
            double amount = Double.parseDouble(request.getParameter("amount"));
            
            switch(operation) {
                case "deposit":
                    account = b.deposit(account, amount);
                    break;
                case "withdraw":
                    account = b.credit(account, amount);
                     break;
                case "transfer":                    
                    int toAccountId = Integer.parseInt(request.getParameter("to"));
                    local.transferFund(account, amount, toAccountId);
                    break;
            }
            
            session.setAttribute("account", account);
            System.out.println("operation end: " + account.getDeposit());
            response.sendRedirect(request.getContextPath() + "/operation.jsp");
            //request.getRequestDispatcher("/operation.jsp").forward(request, response);
        
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/operation.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
