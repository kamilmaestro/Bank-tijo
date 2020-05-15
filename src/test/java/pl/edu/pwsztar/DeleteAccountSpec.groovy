package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class DeleteAccountSpec extends Specification {

    Bank bank

    def setup() {
        bank = new Bank()
    }

    def "should delete account with empty balance" () {
        given: "there is an account"
            int accountNumber = bank.createAccount()
        when: "deletes account"
            int deletedAccountBalance = bank.deleteAccount(accountNumber)
        then: "gets empty balance of deleted account"
            deletedAccountBalance == 0
    }

    def "should not delete account if it does not exist" () {
        when: "deletes account"
            int deletedAccountBalance = bank.deleteAccount(1)
        then: "gets info that such account does not exist"
            deletedAccountBalance == BankOperation.ACCOUNT_NOT_EXISTS
    }

    @Unroll
    def "should get balance of deleted account" () {
        given: "there is an account with balance #deposited"
            int accountNumber = bank.createAccount()
            bank.deposit(accountNumber, deposited)
        when: "deletes account"
            int deletedAccountBalance = bank.deleteAccount(accountNumber)
        then: "gets balance equals to #balance of deleted account"
            deletedAccountBalance == balance
        where:
            deposited   ||  balance
            200         ||  200
            150         ||  150
    }
}
