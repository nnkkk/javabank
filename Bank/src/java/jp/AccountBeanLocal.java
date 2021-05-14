package jp;
//Controller
public interface AccountBeanLocal {
    public void transferFund(Account fromAccount, double fund, int toAccountId) throws Exception;
}
