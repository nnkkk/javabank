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

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Database db = null;
        try {

            String logout = request.getParameter("logout");
            if (logout != null && logout.equals("true")) {

                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/");
                
            } else {

                InitialContext context = new InitialContext();
                String login = request.getParameter("user");
                String password = request.getParameter("pass");
                db = Database.connect();
                Account account = db.findAccount(login, password);
                
                if(account == null) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp?invalid=true");
                } else {                

                    BankRemote b = (BankRemote) context.lookup("myBank");

                    AccountBeanLocal local = (AccountBeanLocal) context.lookup("java:global/Bank/AccountBean");
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
                    session.setAttribute("remote", b);
                    session.setAttribute("accountBean", local);

                    response.sendRedirect(request.getContextPath() + "/operation.jsp");
                  
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
