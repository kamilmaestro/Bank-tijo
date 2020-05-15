package pl.edu.pwsztar;

import java.util.List;

interface AccountRepository {

  Account getByNumber(int accountNumber);

  Account save(Account account);

  void delete(int accountNumber);

  List<Account> findAll();
}
