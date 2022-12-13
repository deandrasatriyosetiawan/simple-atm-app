import java.util.*;

public abstract class Account {
  // The account identity number of the account
  private String accountId;
  // The PIN of the account
  private String pin;
  // The current balance of the account
  private double balance;
  // The user that owns this account
  private User holder;
  // The bank card identity number in which the account was linked
  private String inBankCardId;
  // The list of transactions for this account
  private ArrayList<Transaction> transactions;

  // Create a new account
  public Account(String bankCardId, String accountId, String pin, User holder) {
    this.inBankCardId = bankCardId;
    this.accountId = accountId;
    this.pin = pin;
    this.balance = 0.00;
    this.holder = holder;
  }

  // Add a new transaction of the account
  abstract public void addTransaction(double amount, String note);

  // Get summary line for the account
  abstract public String getSummaryLine();

  // Get the account identity number of the account
  protected String getAccountId() {
    return this.accountId;
  }

  // Get the personal identification number of the account
  protected String getPin() {
    return this.pin;
  }

  // Get the balance of the account
  protected double getBalance() {
    return this.balance;
  }

  // Get the holder of the account
  protected User getHolder() {
    return this.holder;
  }

  // Get the bank card identity number of the account
  protected String getBankCardId() {
    return this.inBankCardId;
  }

  // Add a bank card identity number of the account
  // public void addBankCardId(String newBankCardId) {
  // this.inBankCardId = newBankCardId;
  // }

  // Get the list of transactions of the account
  protected ArrayList<Transaction> getTransactionList() {
    return this.transactions;
  }

  // Print the transaction history of the account
  public void printTransactionHistory() {
    System.out.printf("\nTransaction history for account %s\n", this.accountId);
    for (int len = this.transactions.size() - 1; len >= 0; len--) {
      System.out.println(this.transactions.get(len).getSummaryTransaction());
    }
    System.out.println();
  }

  //
  public boolean validatePin(String pin) {

  }

}