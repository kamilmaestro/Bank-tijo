package pl.edu.pwsztar;

import java.util.Optional;

// TODO: Prosze dokonczyc implementacje oraz testy jednostkowe
// TODO: Prosze nie zmieniac nazw metod - wszystkie inne chwyty dozwolone
// TODO: (prosze jedynie trzymac sie dokumentacji zawartej w interfejsie BankOperation)
class Bank implements BankOperation {

    private static final int EMPTY_BALANCE = 0;
    private static int accountNumber = 0;
    private final AccountRepository accountRepository;

    public Bank() {
        this.accountRepository = new InMemoryAccountRepository();
    }

    public int createAccount() {
        ++accountNumber;
        accountRepository.save(new Account(accountNumber, EMPTY_BALANCE));

        return accountNumber;
    }

    public int deleteAccount(int accountNumber) {
        final int deletedAccountBalance = accountBalance(accountNumber);
        if (deletedAccountBalance != ACCOUNT_NOT_EXISTS) {
            accountRepository.delete(accountNumber);
        }

        return deletedAccountBalance;
    }

    public boolean deposit(int accountNumber, int amount) {
        final Account account = getAccount(accountNumber);
        if (Optional.ofNullable(account).isPresent()) {
            account.deposit(amount);
            accountRepository.save(account);
            return true;
        }

        return false;
    }

    public boolean withdraw(int accountNumber, int amount) {
        final Account account = getAccount(accountNumber);
        if (Optional.ofNullable(account).isPresent()) {
            final boolean wasWithdrawn = account.withdraw(amount);
            accountRepository.save(account);
            return wasWithdrawn;
        }

        return false;
    }

    public boolean transfer(int fromAccount, int toAccount, int amount) {
        final Account account = getAccount(fromAccount);
        final Account sndAccount = getAccount(toAccount);
        if (Optional.ofNullable(account).isPresent() && Optional.ofNullable(sndAccount).isPresent()) {
            return account.transfer(sndAccount, amount);
        }

        return false;
    }

    public int accountBalance(int accountNumber) {
        final Account account = getAccount(accountNumber);
        return Optional.ofNullable(account)
            .map(Account::getAccountBalance)
            .orElse(ACCOUNT_NOT_EXISTS);
    }

    public int sumAccountsBalance() {
        return accountRepository.findAll().stream()            .mapToInt(Account::getAccountBalance)
            .sum();
    }

    Account getAccount(int accountNumber) {
        return accountRepository.getByNumber(accountNumber);
    }
}
