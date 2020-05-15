package pl.edu.pwsztar

import spock.lang.Specification

class SumAccountsBalance extends Specification {

    Bank bank

    def setup() {
        bank = new Bank()
    }


    def "should get balance of all accounts" () {
        given: "there are two accounts"
            int accountNumber = bank.createAccount()
            int sndAccountNumber = bank.createAccount()
        and: "those account have balances"
            bank.deposit(accountNumber, 100)
            bank.deposit(sndAccountNumber, 15)
        when: "wants to get balance of all accounts"
            int accountBalance = bank.sumAccountsBalance()
        then: "get sum of all balances"
            accountBalance == 115
    }
}
