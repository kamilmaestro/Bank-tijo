package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class CreateAccountSpec extends Specification {

    Bank bank

    def setup() {
        bank = new Bank()
    }

    @Unroll
    def "should create account number #accountNumber for #user"() {
        given: "initial data"
            def bank = new Bank()
        when: "the account is created"
            def number = bank.createAccount()
        then: "check account number"
            number == accountNumber
        where:
            user   | accountNumber
            'John' | 1
            'Tom'  | 2
            'Mike' | 3
            'Todd' | 4
    }

    def "should create new account with empty balance" () {
        when: "the account is created"
            int accountNumber = bank.createAccount()
        then: "account balance is empty"
            Account account = bank.getAccount(accountNumber)
            account.getAccountBalance() == 0
    }
}
