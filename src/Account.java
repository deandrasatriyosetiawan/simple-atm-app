import java.util.*;

public abstract class Account {
  // The account id number of the account
  private String accountId;
  // The PIN of the account
  private String pin;
  // The current balance of the account
  private double balance;
  // The user that owns this account
  private User holder;
  // The bank card id number in which the account was linked
  private String inBankCardId;
  // The list of transactions for this account
  private ArrayList<Transaction> transactions;

  // Create a new account
  public Account(String bankCardId, String accountId, String pin, User holder) {
    this.transactions = new ArrayList<Transaction>();
    this.inBankCardId = bankCardId;
    this.accountId = accountId;
    this.pin = pin;
    this.balance = 0.00;
    this.holder = holder;
  }

  // Add a new transaction of the account
  abstract public void addTransaction(double amount, String typeOfTransaction, String note);

  // Get summary line for the account
  abstract public void printAccountSummary();

  // Print the balance inquiry of the account
  abstract public void printBalanceInquiry();

  // Update the balance of the account
  abstract public void updateBalance(double amount);

  // Print the transaction history of the account
  abstract public void printTransactionHistory();

  // Get the account id number of the account
  protected String getAccountId() {
    return this.accountId;
  }

  // Set a new balance of the account
  protected void setBalance(double newBalance) {
    this.balance = newBalance;
  }

  // Get the balance of the account
  protected double getBalance() {
    return this.balance;
  }

  // Get the holder of the account
  protected User getHolder() {
    return this.holder;
  }

  // Get the bank card id number of the account
  protected String getBankCardId() {
    return this.inBankCardId;
  }

  // Set a new PIN of the account
  protected void setPin(String newPin) {
    this.pin = newPin;
  }

  // Get the personal identification number of the account
  protected String getPin() {
    return this.pin;
  }

  // Get the list of transactions of the account
  protected ArrayList<Transaction> getTransactionList() {
    return this.transactions;
  }

  // Validate the PIN with the bank card id number
  public boolean validatePin(String pin) {
    if (pin.compareTo(this.getPin()) == 0) {
      return true;
    }
    return false;
  }

  // Add a bank card id number of the account
  // public void addBankCardId(String newBankCardId) {
  // this.inBankCardId = newBankCardId;
  // }

  // Print the transaction history of the account
  // public void printTransactionHistory() {
  // System.out.printf("\nTransaction history for account %s\n", this.accountId);
  // for (int len = this.transactions.size() - 1; len >= 0; len--) {
  // System.out.println(this.transactions.get(len).getSummaryTransaction());
  // }
  // System.out.println();
  // }

}