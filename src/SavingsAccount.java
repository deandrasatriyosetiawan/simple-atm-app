import java.util.*;

public class SavingsAccount extends Account {
  // Initialize values to the attribute of the account
  public SavingsAccount(String bankCardId, String accountId, String pin, User holder) {
    super(bankCardId, accountId, pin, holder);
  }

  // Add a new transaction in the savings account
  @Override
  public void addTransaction(double amount, String typeOfTransaction, String note) {
    Transaction newTransaction = new Transaction(amount, typeOfTransaction, note, this);
    this.getTransactionList().add(newTransaction);
  }

  // Get summary line for the savings account
  @Override
  public void printAccountSummary() {
    String bankCardId = this.getBankCardId();
    String accountId = this.getAccountId();
    System.out.print("\n" + this.getHolder().getFirstName() + "'s ");
    System.out.println("savings account summary");
    System.out.println("Bank card ID : " + bankCardId);
    System.out.println("Account ID : " + accountId);
  }

  // Print the balance inquiry of the account
  @Override
  public void printBalanceInquiry() {
    double balance = this.getBalance();
    System.out.printf("\nBalance\t: $%.2f\n", balance);
  }

  // Update the balance of the savings account
  @Override
  public void updateBalance(double amount) {
    double newBalance = this.getBalance();
    newBalance += amount;
    this.setBalance(newBalance);
  }

  // Print the transaction history of the savings account
  @Override
  public void printTransactionHistory() {
    ArrayList<Transaction> transactions = this.getTransactionList();
    System.out.printf("\nTransaction history for savings account with ID %s :\n", this.getAccountId());
    if (!transactions.isEmpty()) {
      for (int len = transactions.size() - 1; len >= 0; len--) {
        System.out.println(transactions.get(len).getSummaryTransaction());
      }
    } else {
      System.out.println("\nNo transactions.");
    }
  }

}
