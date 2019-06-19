import com.epamTranings.bankSystem.dao.BankAccountDAO;
import com.epamTranings.bankSystem.dao.UserDAO;
import com.epamTranings.bankSystem.entity.bankAccount.BankAccount;
import com.epamTranings.bankSystem.entity.bankAccount.BankAccountOrder;
import com.epamTranings.bankSystem.entity.bankAccount.BankAccountTransaction;
import com.epamTranings.bankSystem.entity.userAccount.Role;
import com.epamTranings.bankSystem.utils.dbConnectionUtils.MySQLConnectionUtil;
import com.epamTranings.bankSystem.entity.userAccount.UserAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class DAOTest {
    private UserDAO userDAO;
    private BankAccountDAO bankAccountDAO;
    private UserAccount userAccount;
    private BankAccount bankAccount;
    private List<BankAccount> accounts;
    private List<BankAccountTransaction> transactions;
    private Connection connection;
    private Mockito mockito;

    private final int TEST_COUNTER = 10;

    @Before
    public void init(){
        connection = MySQLConnectionUtil.getMySQLConnection();
    }

    @Test
    public void findUserByEmailTest(){
        userAccount = userDAO.findUserByEmail(connection, "grishachapliy1@gmail.com");
    }

    @Test
    public void findBankAccountsByUserAccountEmailTest(){
        userAccount = userDAO.findUserByEmail(connection, "grishachapliy1@gmail.com");
        accounts = userDAO.findUserBankAccounts(connection, userAccount);
    }

    @Test
    public void findBankAccountByUuidTest(){
        String uuid = "82b07ab1-d082-4be4-a9c4-52f959c295cb";
        bankAccount = bankAccountDAO.findBankAccountByUuid(connection, uuid);
    }

    @Test
    public void findBankAccountTransactionsByBankAccountUuidTest(){
        String uuid = "82b07ab1-d082-4be4-a9c4-52f959c295cb";
        bankAccount = bankAccountDAO.findBankAccountByUuid(connection, uuid);
        transactions = bankAccountDAO.findBankAccountTransactionsByUuid(connection, bankAccount);
    }

    @Test
    public void insertBankAccountOrderTest(){
        BankAccountOrder bankAccountOrder;
        BankAccountOrder bankAccountOrderRead;
        userAccount = userDAO.findUserByEmail(connection, "grishachapliy1@gmail.com");
        double balance;

        for (int i = 0; i < TEST_COUNTER; i++) {
            balance = i;

            bankAccountOrder = mockito.mock(BankAccountOrder.class);

            setMockBankAccountOrderGetDataRules(mockito, bankAccountOrder, userAccount, balance);

            Assert.assertTrue(bankAccountDAO.insertBankAccountOrder(connection, bankAccountOrder));

            List<BankAccountOrder> orders = bankAccountDAO.findBankAccountOrdersByUserAccount(connection, userAccount);

            bankAccountOrderRead = orders.get(orders.size() - 1);

            Assert.assertTrue(bankAccountOrder.getAccountBalance() == bankAccountOrderRead.getAccountBalance());

            Assert.assertTrue(bankAccountDAO.deleteBankAccountOrderById(connection, bankAccountOrderRead.getOrderId()));
        }
    }

    @Test
    public void insertUserAccountTest(){
        UserAccount userAccount;
        UserAccount userAccountRead;

        for (int i = 3; i < TEST_COUNTER; i++) {
            userAccount = mockito.mock(UserAccount.class);

            setMockUserAccountGetDataRules(mockito, userAccount, i);

            Assert.assertTrue(userDAO.insertUserAccount(connection, userAccount));

            userAccountRead = userDAO.findUserByEmail(connection, userAccount.getUserAccountEmail());
            Assert.assertTrue(userAccountRead != null);

            Assert.assertTrue(userDAO.deleteUserAccount(connection, userAccount));
        }

    }

    @Test
    public void findRoleTest(){

        Role roleAdmin = UserDAO.findUserRoleByName(connection, "ROLE_ADMIN");
        Role roleUser = UserDAO.findUserRoleByName(connection, "ROLE_USER");

        Assert.assertTrue(roleAdmin.getRoleName().equals("ROLE_ADMIN"));
        Assert.assertTrue(roleUser.getRoleName().equals("ROLE_USER"));
    }

    private void setMockUserAccountGetDataRules(Mockito mock, UserAccount userAccount, int number){
        Role role = new Role();
        role.setRoleID(2);
        role.setRoleName("ROLE_USER");

        mock.when(userAccount.getUserAccountEmail()).thenReturn("email" + number);
        mock.when(userAccount.getUserAccountName()).thenReturn("name" + number);
        mock.when(userAccount.getUserAccountRole()).thenReturn(role);
        mock.when(userAccount.getUserAccountEncryptedPassword()).thenReturn("password" + number);
        mock.when(userAccount.getUserAccountGender()).thenReturn("male");
        mock.when(userAccount.getAccountCreateDate()).thenReturn(new Date());
        mock.when(userAccount.getUserAccountPhone()).thenReturn("phone" + number);

    }

    private void setMockBankAccountOrderGetDataRules(Mockito mock, BankAccountOrder bankAccountOrder, UserAccount userAccount, double balance){
        mock.when(bankAccountOrder.getOrderCreateDate()).thenReturn(new Date());
        mock.when(bankAccountOrder.getAccountExpirationDate()).thenReturn(new Date());
        mock.when(bankAccountOrder.getOrderOwner()).thenReturn(userAccount);
        mock.when(bankAccountOrder.getOrderStatus()).thenReturn(BankAccountOrder.OrderStatus.ALLOWED);
        mock.when(bankAccountOrder.getAccountType()).thenReturn(BankAccount.AccountType.PAYMENT);
        mock.when(bankAccountOrder.getAccountBalance()).thenReturn(balance);
    }
}
