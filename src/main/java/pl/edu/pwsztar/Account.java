package pl.edu.pwsztar;

final class Account {

  private final int accountNumber;
  private int accountBalance;

  Account(int accountNumber, int accountBalance) {
    this.accountNumber = accountNumber;
    this.accountBalance = accountBalance;
  }

  int getAccountNumber() {
    return accountNumber;
  }

  int getAccountBalance() {
    return accountBalance;
  }

  void deposit(int amount) {
    this.accountBalance += amount;
  }

  boolean withdraw(int amount) {
    if (this.accountBalance >= amount) {
      this.accountBalance -= amount;
      return true;
    }

    return false;
  }

  boolean transfer(Account to, int amount) {
    if (this.accountBalance >= amount) {
      withdraw(amount);
      to.deposit(amount);
      return true;
    }

    return false;
  }
}
