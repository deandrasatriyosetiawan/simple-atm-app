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
  // The list of transactions for this account
  private ArrayList<Transaction> transactions;

  // Initialize values to the attribute of the account
  public Account(String accountId, String pin, User holder) {
    this.accountId = accountId;
    this.pin = pin;
    this.balance = 0.00;
    this.holder = holder;
  }

  // Getting the name of the account
  // protected String getName() {
  // return this.name;
  // }

  // Getting the account identity number of the account
  protected String getAccountId() {
    return this.accountId;
  }

  // Getting the personal identification number of the account
  protected String getPin() {
    return this.pin;
  }

  // Getting the balance of the account
  protected double getBalance() {
    return this.balance;
  }

  // Getting the holder of the account
  protected User getHolder() {
    return this.holder;
  }

}
