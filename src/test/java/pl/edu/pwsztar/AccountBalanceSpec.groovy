package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class AccountBalanceSpec extends Specification {

    Bank bank

    def setup() {
        bank = new Bank()
    }

    def cleanup () {
        Bank.accountNumber = 0
    }

    @Unroll
    def "should get balance of an existing account" () {
        given: "there is an account"
            int accountNumber = bank.createAccount()
        and: "this account has balance equals to #balance"
            bank.deposit(accountNumber, balance)
        when: "wants to get balance of the account"
            int accountBalance = bank.accountBalance(accountNumber)
        then: "balance equals to #expected"
            accountBalance == expected
        where:
            balance     ||  expected
            200         ||  200
            1           ||  1
            2           ||  2
    }

    def "should not get balance of a not existing account" () {
        when: "wants to get balance of the account"
        int accountBalance = bank.accountBalance(1)
        then: "there is no such account"
        accountBalance == BankOperation.ACCOUNT_NOT_EXISTS
    }

}
