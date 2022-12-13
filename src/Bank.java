import java.util.*;

public class Bank {
  // The name of the bank
  private String name;
  // The list of users in this bank
  private ArrayList<User> users;
  // The list of checking accounts in this bank
  private ArrayList<CheckingAccount> checkingAccounts;
  // The list of savings accounts in this bank
  private ArrayList<SavingsAccount> savingsAccounts;
  // The list of bank cards identity number in this bank
  private ArrayList<String> bankCardIdList;

  // Set a name of the bank and create an empty lists of users,
  // checking accounts, savings accounts, and bank card id list
  public Bank(String name) {
    this.name = name;
    this.users = new ArrayList<User>();
    this.checkingAccounts = new ArrayList<CheckingAccount>();
    this.savingsAccounts = new ArrayList<SavingsAccount>();
    this.bankCardIdList = new ArrayList<String>();
  }

  // Generate a new user identity number for a user
  public String getNewUserId() {
    String userId;
    Random random = new Random();
    int lengthUserId = 16;
    boolean isUserIdUnique = false;
    do {
      userId = "";
      for (int a = 0; a < lengthUserId; a++) {
        userId += ((Integer) random.nextInt(10)).toString();
      }
      for (User user : users) {
        if (userId.compareTo(user.getUserId()) == 0) {
          isUserIdUnique = true;
          break;
        }
      }
    } while (isUserIdUnique);
    return userId;
  }

  // Generate a new account identity number for an account
  public String getNewAccountId() {
    String accountId;
    Random random = new Random();
    int lengthAccountId = 10;
    boolean isAccountIdUnique = false;
    do {
      accountId = "";
      for (int a = 0; a < lengthAccountId; a++) {
        accountId += ((Integer) random.nextInt(10)).toString();
      }
      for (Account checkingAccount : checkingAccounts) {
        if (accountId.compareTo(checkingAccount.getAccountId()) == 0) {
          isAccountIdUnique = true;
          break;
        }
      }
      for (Account savingsAccount : savingsAccounts) {
        if (accountId.compareTo(savingsAccount.getAccountId()) == 0) {
          isAccountIdUnique = true;
          break;
        }
      }
    } while (isAccountIdUnique);
    return accountId;
  }

  // Generate a new bank card identity number for a user's card
  public String getNewBankCardId() {
    String bankCardId;
    Random random = new Random();
    int lengthBankCardId = 16;
    boolean isBankCardIdUnique = false;
    do {
      bankCardId = "";
      for (int a = 0; a < lengthBankCardId; a++) {
        bankCardId += ((Integer) random.nextInt(10)).toString();
      }
      for (User user : users) {
        if (bankCardId.compareTo(user.getUserId()) == 0) {
          isBankCardIdUnique = true;
          break;
        }
      }
    } while (isBankCardIdUnique);
    return bankCardId;
  }

  // Add a new checking account to the bank
  public void addAccount(CheckingAccount newCheckingAccount) {
    this.checkingAccounts.add(newCheckingAccount);
  }

  // Add a new savings account to the bank
  public void addAccount(SavingsAccount newSavingsAccount) {
    this.savingsAccounts.add(newSavingsAccount);
  }

  // Add a new bank card identity to the bank
  public void addBankCardId(String newBankCardId) {
    this.bankCardIdList.add(newBankCardId);
  }

  // Add a new user to the bank and also create checking account
  // and savings account for the user
  public User addUser(String fullName, String pin) {
    User newUser = new User(fullName, this);
    this.users.add(newUser);
    String accountId = getNewAccountId();
    CheckingAccount newCheckingAccount = new CheckingAccount(accountId, pin, newUser);
    this.addAccount(newCheckingAccount);
    accountId = getNewAccountId();
    SavingsAccount newSavingsAccount = new SavingsAccount(accountId, pin, newUser);
    this.addAccount(newSavingsAccount);
    newUser.addAccount(newCheckingAccount, newSavingsAccount);
    return newUser;
  }

}
