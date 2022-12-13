import java.util.*;

public class User {
  // The full name of the user
  private String fullName;
  // The first name of the user
  private String firstName;
  // The user identity number of the user
  private String userId;
  // The bank card identity number of the user
  private ArrayList<String> bankCardIdList;
  // The checking account of the user
  private ArrayList<CheckingAccount> checkingAccounts;
  // The savings account of the user
  private ArrayList<SavingsAccount> savingsAccounts;

  // Set a user's fullname, user's id, user's bank card id,
  // create an empty lists of checking accounts and saving accounts,
  // and also print an information that a new user has been created
  public User(String fullName, Bank bank) {
    this.fullName = fullName;
    this.userId = bank.getNewUserId();
    String bankCardId = bank.getNewBankCardId();
    bank.addBankCardId(bankCardId);
    this.checkingAccounts = new ArrayList<CheckingAccount>();
    this.savingsAccounts = new ArrayList<SavingsAccount>();
    System.out.printf("New user " + fullName + " with ID " + this.userId + " created");
  }

  // Get the user identity number
  public String getUserId() {
    return userId;
  }

  // Set the user's first name
  public void setFirstName(String fullName) {
    fullName = fullName.trim();
    this.firstName = "";
    for (int index = 0; index < fullName.length(); index++) {
      if (fullName.charAt(index) == ' ')
        break;
      this.firstName += fullName.charAt(index);
    }
  }

  // Get the user's first name
  public String getFirstName() {
    return this.firstName;
  }

  // Add a new checking account and savings account of the user
  public void addAccount(CheckingAccount newCheckingAccount, SavingsAccount newSavingsAccount) {
    this.checkingAccounts.add(newCheckingAccount);
    this.savingsAccounts.add(newSavingsAccount);
  }

  // Print summary for the account of this user
  public void printAccountSummary(CheckingAccount checkingAccount) {
    System.out.print("\n\n" + this.getFirstName() + "'s ");
    System.out.println(checkingAccount.getSummaryLine() + "\n");
  }

}
