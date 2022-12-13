import java.util.*;

public class SavingsAccount extends Account {
  // Initialize values to the attribute of the account
  public SavingsAccount(String accountId, String pin, User holder) {
    super(accountId, pin, holder);
  }

  // Add a new transaction in the savings account
  @Override
  public void addTransaction(double amount, String note) {
    Transaction newTransaction = new Transaction(amount, note, this);
    this.getTransactionList().add(newTransaction);
  }

  // Get summary line for the savings account
  @Override
  public String getSummaryLine() {
    double balance = this.getBalance();
    String accountId = this.getAccountId();
    return String.format("savings account summary\n%s : $%.2f", accountId, balance);
  }

}
