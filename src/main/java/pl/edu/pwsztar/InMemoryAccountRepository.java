package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryAccountRepository implements AccountRepository {

  private final Map<Integer, Account> values = new HashMap<>();

  @Override
  public Account getByNumber(int accountNumber) {
    return values.get(accountNumber);
  }

  @Override
  public Account save(Account account) {
    return values.put(account.getAccountNumber(), account);
  }

  @Override
  public void delete(int accountNumber) {
    values.remove(accountNumber);
  }

  @Override
  public List<Account> findAll() {
    return new ArrayList<>(values.values());
  }
}
