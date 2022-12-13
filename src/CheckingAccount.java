import java.util.*;

public class CheckingAccount extends Account {
  // Initialize values to the attribute of the account
  public CheckingAccount(String bankCardId, String accountId, String pin, User holder) {
    super(bankCardId, accountId, pin, holder);
  }

  // Add a new transaction in the checking account
  @Override
  public void addTransaction(double amount, String note) {
    Transaction newTransaction = new Transaction(amount, note, this);
    this.getTransactionList().add(newTransaction);
  }

  // Get summary line for the checking account
  @Override
  public String getSummaryLine() {
    double balance = this.getBalance();
    String accountId = this.getAccountId();
    return String.format("checking account summary\n%s : $%.2f", accountId, balance);
  }

}
