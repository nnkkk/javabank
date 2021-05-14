package jp;
//Controller
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(mappedName = "accountBean")
@TransactionManagement(value = TransactionManagementType.BEAN)
public class AccountBean implements AccountBeanLocal {


    private Database database;

    public void transferFund(Account fromAccount, double fund, int toAccountId)
            throws Exception {
        try {
           
            database = Database.connect();
            Account toAccount = database.findAccountById(toAccountId);
            database.begin();
            confirmAccountDetail(fromAccount);
            withdrawAmount(fromAccount, fund);
            confirmAccountDetail(toAccount);
            depositAmount(toAccount, fund);
          
            database.commit();
        } catch (InvalidAccountException | InsufficientFundException | PaymentException exception) {
          
            if (database != null) {
                database.rollback();
            }
            throw exception;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    private void confirmAccountDetail(Account account) throws InvalidAccountException, SQLException {

        if (!existsAccount(account)) {
            throw new InvalidAccountException("Podane konto " + account.getFullName() + " nie istnieje");
        }
    }

    private void withdrawAmount(Account fromAccount, double amount) throws InsufficientFundException, SQLException {

        if (fromAccount.getDeposit() < amount) {
            throw new InsufficientFundException("Brak wystarczających środków");
        }

        double newAmount = fromAccount.getDeposit() - amount;
        database.setAccountDeposit(fromAccount.getId(), newAmount);
        fromAccount.setDeposit(newAmount);
    }

    private void depositAmount(Account toAccount, double amount) throws PaymentException, SQLException {

        if (amount < 0) {
            throw new PaymentException("Płatność nie może być mniejsza niż 0");
        }

        double newAmount = toAccount.getDeposit() + amount;
        database.setAccountDeposit(toAccount.getId(), newAmount);
        toAccount.setDeposit(newAmount);
    }

    private boolean existsAccount(Account account) throws SQLException {

        Account exists = database.findAccountById(account.getId());
        if (exists == null) {
            return false;
        } else {
            return true;
        }
    }
}
