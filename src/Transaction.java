import java.util.*;

public class Transaction {
  // The amount of the transaction
  private double amount;
  // The time and date of the transaction
  private Date timestamp;
  // The note for the transaction
  private String note;
  // The checking account in which the transaction was performed
  private CheckingAccount inCheckingAccount;
  // The savings account in which the transaction was performed
  private SavingsAccount inSavingsAccount;
}
