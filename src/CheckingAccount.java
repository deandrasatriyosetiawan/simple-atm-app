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
  public void printAccountSummary() {
    String bankCardId = this.getBankCardId();
    String accountId = this.getAccountId();
    double balance = this.getBalance();
    System.out.print("\n\n" + this.getHolder().getFirstName() + "'s ");
    System.out.println("checking account summary");
    System.out.println("Bank Card ID\t: " + bankCardId);
    System.out.println("Account ID\t: " + accountId);
    System.out.printf("Balance\t: $%.2f\n", balance);
  }

  // TODO : Make a new method that to print balance inquiry

}
