package jp;
//Controller
import java.util.List;
import java.util.Random;
import javax.ejb.Stateful;

@Stateful(mappedName = "myBank")
public class BankAccountBean implements BankRemote {
    
    @Override
    public Account credit(Account account, double amount) throws Exception {

        Database db = Database.connect();

        try {
            double currentAmount = account.getDeposit();

            if (amount > currentAmount) {
                throw new InsufficientFundException("Brak wystarczających środków");
            }
            currentAmount -= amount;
            db.begin();
            db.setAccountDeposit(account.getId(), currentAmount);
            db.commit();
            account.setDeposit(currentAmount);
            
            return account;
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        } finally {
            db.close();
        }
    }

    @Override
    public Account deposit(Account account, double amount) throws Exception {

        Database db = Database.connect();

        try {
            db.begin();
            double currentAmount = account.getDeposit();
            if (amount < 0) {
                throw new PaymentException("Płatność nie może być mniejsza niż 0");
            }
            currentAmount += amount;
            db.setAccountDeposit(account.getId(), currentAmount);
            account.setDeposit(currentAmount);
            
            db.commit();
            return account;
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        } finally {
            db.close();
        }
    }

    private static final Random random = new Random();

    @Override
    public Account createAccount(String login, String password, String firstName, String lastName) throws Exception {

        Database db = Database.connect();

        try {
            db.begin();
            String accountNumber = "AC" + random.nextLong();
            Account account = db.createAccount(login, password, firstName, lastName, accountNumber);
            db.commit();
            return account;
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        } finally {
            db.close();
        }
    }

    public List<Account> getAllAccounts() throws Exception {
        Database db = Database.connect();        
        try {
            List<Account> accounts = db.findAllAccounts();            
            return accounts;
        } finally {
            db.close();
        }
    }
}
