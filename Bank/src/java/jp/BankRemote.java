package jp;
//Controller
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface BankRemote {

    Account createAccount(String login, String password, String firstName, String lastName) throws Exception;
    
    Account credit(Account account, double amount) throws Exception;

    Account deposit(Account account, double amount) throws Exception;

    List<Account> getAllAccounts() throws Exception;
}
