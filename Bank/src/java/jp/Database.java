package jp;
//Model
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Database {

    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
   
    }

    public static Database connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");

   
        Connection con = DriverManager.getConnection("jdbc:sqlite:bank.db");
        con.setAutoCommit(false);
        Database db = new Database(con);
        db.initialise();
        return db;
    }

    public void close() {

        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException ex) {

        }
    }

    public void begin() {

    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
   
        
        
        
    private void initialise() throws SQLException {
        Statement state = connection.createStatement();
        ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='account'");
        if (!res.next()) {

            Statement state2 = connection.createStatement();
            state2.executeUpdate("create table account("
                    + "id integer PRIMARY KEY AUTOINCREMENT,"
                    + "firstName varchar(60),"
                    + "lastName varchar(60),"
                    + "login varchar(60),"
                    + "password varchar(60),"
                    + "accountNumber varchar(60),"
                    + "deposit real"
                    + ");");

            state2.close();
        }
        res.close();
        state.close();
    }

    public void setAccountDeposit(int id, double deposit) throws SQLException {

        PreparedStatement state = connection.prepareStatement("UPDATE account SET deposit = ? WHERE id = ?");

        state.setDouble(1, deposit);
        state.setInt(2, id);

        state.executeUpdate();
        state.close();

    }

    public List<Account> findAllAccounts() throws SQLException {

        Statement state = connection.createStatement();
        List<Account> accounts = new ArrayList<>();
        ResultSet result = state.executeQuery("SELECT * FROM account");
        while (result.next()) {
            Account account = readAccount(result);
            accounts.add(account);
        }
        state.close();
        return accounts;
    }

    public Account findAccountById(int id) throws SQLException {

        PreparedStatement state = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
        state.setInt(1, id);

        Account account;
        ResultSet result = state.executeQuery();
        if (result.next()) {
            account = readAccount(result);
        } else {
            account = null;
        }
        state.close();
        return account;
    }

    public Account findAccount(String login, String password) throws SQLException {

        PreparedStatement state = connection.prepareStatement("SELECT * FROM account WHERE login = ? AND password = ?");

        state.setString(1, login);
        state.setString(2, password);

        Account account;
        ResultSet result = state.executeQuery();
        if (result.next()) {

            account = readAccount(result);

        } else {
            account = null;
        }
        state.close();
        return account;
    }

    private Account readAccount(ResultSet result) throws SQLException {

        int id = result.getInt("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        String accountNumber = result.getString("accountNumber");
        Double deposit = result.getDouble("deposit");

        Account account = new Account(id, firstName, lastName, accountNumber);
        account.setDeposit(deposit);

        return account;
    }

    public Account createAccount(String login, String password, String firstName, String lastName, String accountNumber) throws SQLException {

        PreparedStatement state = connection.prepareStatement("INSERT INTO account(firstName, lastName, login, password, accountNumber, deposit) VALUES (?, ?, ?, ?, ?, 0)");

        state.setString(1, firstName);
        state.setString(2, lastName);
        state.setString(3, login);
        state.setString(4, password);
        state.setString(5, accountNumber);

        state.executeUpdate();
        state.close();

        Statement state2 = connection.createStatement();
        ResultSet result = state2.executeQuery("SELECT MAX(id) FROM account");
        int maxId = result.getInt(1);
        result.close();
        state2.close();

        Account account = new Account(maxId, firstName, lastName, accountNumber);
        account.setDeposit(0);

        return account;
    }
}
