package jp;
//Controller
import java.io.IOException;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OpenAccount", urlPatterns = {"/OpenAccount"})
public class OpenAccount extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            InitialContext context = new InitialContext();
            
            BankRemote b = (BankRemote) context.lookup("myBank");
            AccountBeanLocal local = (AccountBeanLocal) context.lookup("java:global/Bank/AccountBean");
            
            HttpSession session = request.getSession();
            session.setAttribute("remote", b);
            session.setAttribute("accountBean", local);
            
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            Account account = b.createAccount(login, password, firstName, lastName);
            session.setAttribute("account", account);
            
            response.sendRedirect(request.getContextPath() + "/operation.jsp");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
