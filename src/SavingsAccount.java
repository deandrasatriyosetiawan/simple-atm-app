import java.util.*;

public class SavingsAccount extends Account {
  // Initialize values to the attribute of the account
  public SavingsAccount(String bankCardId, String accountId, String pin, User holder) {
    super(bankCardId, accountId, pin, holder);
  }

  // Add a new transaction in the savings account
  @Override
  public void addTransaction(double amount, String note) {
    Transaction newTransaction = new Transaction(amount, note, this);
    this.getTransactionList().add(newTransaction);
  }

  // Get summary line for the savings account
  @Override
  public void printAccountSummary() {
    double balance = this.getBalance();
    String accountId = this.getAccountId();
    System.out.print("\n\n" + this.getHolder().getFirstName() + "'s ");
    System.out.printf("savings account summary\n%s : $%.2f", accountId, balance);
  }

  // TODO : Make a new method that to print balance inquiry

}
