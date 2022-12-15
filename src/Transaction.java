import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
  // The amount of the transaction
  private double amount;
  // The time and date of the transaction
  private LocalDateTime timestamp;
  // The note for the transaction
  private String note;
  // The type of the transaction
  private String typeOfTransaction;
  // The checking account in which the transaction was performed
  private CheckingAccount inCheckingAccount;
  // The savings account in which the transaction was performed
  private SavingsAccount inSavingsAccount;

  // Create a new transaction with overloaded constructor
  public Transaction(double amount, CheckingAccount inCheckingAccount) {
    this.amount = amount;
    this.inCheckingAccount = inCheckingAccount;
    inCheckingAccount.updateBalance(amount);
    this.timestamp = LocalDateTime.now();
    this.typeOfTransaction = "";
    this.note = "";
  }

  public Transaction(double amount, SavingsAccount inSavingsAccount) {
    this.amount = amount;
    this.inSavingsAccount = inSavingsAccount;
    inSavingsAccount.updateBalance(amount);
    this.timestamp = LocalDateTime.now();
    this.typeOfTransaction = "";
    this.note = "";
  }

  public Transaction(double amount, String typeOfTransaction, String note, CheckingAccount inCheckingAccount) {
    this(amount, inCheckingAccount);
    this.typeOfTransaction = typeOfTransaction;
    this.note = note;
  }

  public Transaction(double amount, String typeOfTransaction, String note, SavingsAccount inSavingsAccount) {
    this(amount, inSavingsAccount);
    this.typeOfTransaction = typeOfTransaction;
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
    return String.format("%s | $%.2f : %s : %s", timestamp, this.amount, this.typeOfTransaction, this.note);
  }

}
