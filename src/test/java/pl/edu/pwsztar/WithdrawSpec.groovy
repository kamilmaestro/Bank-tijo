package pl.edu.pwsztar

import spock.lang.Specification

class WithdrawSpec extends Specification {

    Bank bank

    def setup() {
        bank = new Bank()
    }

    def cleanup () {
        Bank.accountNumber = 0
    }

    def "should withdraw money from an existing account if its balance is bigger than amount to withdraw" () {
        given: "there is an account"
            int accountNumber = bank.createAccount()
        and: "this account has some amount of money"
            bank.deposit(accountNumber, 200)
        when: "wants to withdraw from the account smaller amount of money than balance of this account"
            boolean isWithdrawn = bank.withdraw(accountNumber, 150)
        then: "money is withdrawn"
            isWithdrawn
    }

    def "should not withdraw money from an account if its balance is smaller than amount of money to withdraw" () {
        given: "there is an account"
            int accountNumber = bank.createAccount()
        and: "this account has some amount of money"
            bank.deposit(accountNumber, 200)
        when: "wants to withdraw from the account bigger amount of money than balance of this account"
        boolean isWithdrawn = bank.withdraw(accountNumber, 250)
        then: "money is not withdrawn"
            !isWithdrawn
    }

    def "should not withdraw money from a not existing account" () {
        when: "wants to withdraw from the not existing account"
            boolean isWithdrawn = bank.withdraw(1, 100)
        then: "money is not withdrawn"
            !isWithdrawn
    }
}
