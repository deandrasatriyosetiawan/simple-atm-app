public class SavingsAccount extends Account {
  // Initialize values to the attribute of the account
  public SavingsAccount(String bankCardId, String accountId, String pin, User holder) {
    super(bankCardId, accountId, pin, holder);
  }

  // Add a new transaction in the savings account
  @Override
  public void addTransaction(double amount, String note) {
    Transaction newTransaction = new Transaction(amount, note, this);
    this.getTransactionList().add(newTransaction);
  }

  // Get summary line for the savings account
  @Override
  public void printAccountSummary() {
    double balance = this.getBalance();
    String accountId = this.getAccountId();
    System.out.print("\n\n" + this.getHolder().getFirstName() + "'s ");
    System.out.printf("savings account summary\n%s : $%.2f", accountId, balance);
  }

  // Print the balance inquiry of the account
  @Override
  public void printBalanceInquiry() {
    double balance = this.getBalance();
    System.out.printf("Balance\t: $%.2f\n", balance);
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
    System.out.printf("\nTransaction history for savings account %s\n", this.getAccountId());
    for (int len = this.getTransactionList().size() - 1; len >= 0; len--) {
      System.out.println(this.getTransactionList().get(len).getSummaryTransaction());
    }
    System.out.println();
  }

}
