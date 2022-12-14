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
  // The list of bank cards id number in this bank
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

  // Get the name of the bank
  public String getName() {
    return this.name;
  }

  // Generate a new user id number for a user
  public String getNewUserId() {
    String userId;
    Random random = new Random();
    int lengthUserId = 16;
    boolean isUserIdNotUnique = false;
    do {
      userId = "";
      for (int a = 0; a < lengthUserId; a++) {
        userId += ((Integer) random.nextInt(10)).toString();
      }
      for (User user : users) {
        if (userId.compareTo(user.getUserId()) == 0) {
          isUserIdNotUnique = true;
          break;
        }
      }
    } while (isUserIdNotUnique);
    return userId;
  }

  // Generate a new account id number for an account
  public String getNewAccountId() {
    String accountId;
    Random random = new Random();
    int lengthAccountId = 10;
    boolean isAccountIdNotUnique = false;
    do {
      accountId = "";
      for (int a = 0; a < lengthAccountId; a++) {
        accountId += ((Integer) random.nextInt(10)).toString();
      }
      for (Account checkingAccount : checkingAccounts) {
        if (accountId.compareTo(checkingAccount.getAccountId()) == 0) {
          isAccountIdNotUnique = true;
          break;
        }
      }
      for (Account savingsAccount : savingsAccounts) {
        if (accountId.compareTo(savingsAccount.getAccountId()) == 0) {
          isAccountIdNotUnique = true;
          break;
        }
      }
    } while (isAccountIdNotUnique);
    return accountId;
  }

  // Generate a new bank card id number for a user's card
  public String getNewBankCardId() {
    String bankCardId;
    Random random = new Random();
    int lengthBankCardId = 16;
    boolean isBankCardIdNotUnique = false;
    do {
      bankCardId = "";
      for (int a = 0; a < lengthBankCardId; a++) {
        bankCardId += ((Integer) random.nextInt(10)).toString();
      }
      for (User user : users) {
        if (bankCardId.compareTo(user.getUserId()) == 0) {
          isBankCardIdNotUnique = true;
          break;
        }
      }
    } while (isBankCardIdNotUnique);
    return bankCardId;
  }

  // Add a new user to the bank and also create checking account
  // and savings account for the user
  public User addUser(String fullName, String pin) {
    String bankCardId = getNewBankCardId();
    this.addBankCardId(bankCardId);

    User newUser = new User(fullName, bankCardId, this);
    this.users.add(newUser);

    String accountId = getNewAccountId();
    CheckingAccount newCheckingAccount = new CheckingAccount(bankCardId, accountId, pin, newUser);
    this.addAccount(newCheckingAccount);
    accountId = getNewAccountId();
    SavingsAccount newSavingsAccount = new SavingsAccount(bankCardId, accountId, pin, newUser);
    this.addAccount(newSavingsAccount);
    newUser.addAccount(newCheckingAccount, newSavingsAccount);

    return newUser;
  }

  // Get the Account object if user id number, bank card id number, and PIN are
  // valid
  public Account login(String userId, String bankCardId, String pin) {
    boolean isThePinCorrect;
    for (User user : users) {
      if (userId.compareTo(user.getUserId()) != 0) {
        return null;
      }
    }
    for (String registeredBankCardId : bankCardIdList) {
      if (bankCardId.compareTo(registeredBankCardId) != 0) {
        return null;
      }
    }
    for (Account checkingAccount : checkingAccounts) {
      if (bankCardId.compareTo(checkingAccount.getBankCardId()) == 0) {
        isThePinCorrect = checkingAccount.validatePin(pin);
        if (isThePinCorrect) {
          return checkingAccount;
        }
      }
    }
    return null;
  }

  // Add a new checking account to the bank
  public void addAccount(CheckingAccount newCheckingAccount) {
    this.checkingAccounts.add(newCheckingAccount);
  }

  // Add a new savings account to the bank
  public void addAccount(SavingsAccount newSavingsAccount) {
    this.savingsAccounts.add(newSavingsAccount);
  }

  // Add a new bank card id to the bank
  public void addBankCardId(String newBankCardId) {
    this.bankCardIdList.add(newBankCardId);
  }

  // Get a checking account from the list of checking account
  public Account getAccount(Account account) {
    String bankCardId = account.getBankCardId();
    String accountId = account.getAccountId();
    for (Account checkingAccount : checkingAccounts) {
      if (accountId.compareTo(checkingAccount.getAccountId()) == 0) {
        break;
      }
      if (bankCardId.compareTo(checkingAccount.getBankCardId()) == 0) {
        return checkingAccount;
      }
    }
    for (Account savingsAccount : savingsAccounts) {
      if (bankCardId.compareTo(savingsAccount.getBankCardId()) == 0) {
        return savingsAccount;
      }
    }
    return null;
  }

  // Get a savings account from the list of savings account
  // public Account getAccount(Account checkingAccount) {
  // String bankCardId = checkingAccount.getBankCardId();
  // for (Account savingsAccount : savingsAccounts) {
  // if (bankCardId.compareTo(savingsAccount.getBankCardId()) == 0) {
  // return savingsAccount;
  // }
  // }
  // return null;
  // }

}
