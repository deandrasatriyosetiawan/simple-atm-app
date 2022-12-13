import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
  // The amount of the transaction
  private double amount;
  // The time and date of the transaction
  private LocalDateTime timestamp;
  // The note for the transaction
  private String note;
  // The checking account in which the transaction was performed
  private CheckingAccount inCheckingAccount;
  // The savings account in which the transaction was performed
  private SavingsAccount inSavingsAccount;

  // Create a new transaction with overloaded constructor
  public Transaction(double amount, CheckingAccount inCheckingAccount) {
    this.amount = amount;
    this.inCheckingAccount = inCheckingAccount;
    this.timestamp = LocalDateTime.now();
    this.note = "";
  }

  public Transaction(double amount, SavingsAccount inSavingsAccount) {
    this.amount = amount;
    this.inSavingsAccount = inSavingsAccount;
    this.timestamp = LocalDateTime.now();
    this.note = "";
  }

  public Transaction(double amount, String note, CheckingAccount inCheckingAccount) {
    this(amount, inCheckingAccount);
    this.note = note;
  }

  public Transaction(double amount, String note, SavingsAccount inSavingsAccount) {
    this(amount, inSavingsAccount);
    this.note = note;
  }

  // Get the amount of the transaction
  public double getAmount() {
    return this.amount;
  }

  // Change the format of the transaction's timestamp
  private String changeTimestampFormat(LocalDateTime timestamp) {
    DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedTimestamp = timestamp.format(timestampFormatter);
    return formattedTimestamp;
  }

  // Get a summary of the transaction
  public String getSummaryTransaction() {
    String timestamp = this.changeTimestampFormat(this.timestamp);
    return String.format("%s : $%.2f : %s", timestamp, this.amount, this.note);
  }

}
