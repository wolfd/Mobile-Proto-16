package io.wolfd.mobileproto.lesson2.money;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyTester {
    @Test
    public void testSetAmount() {
        Account a = new CheckingAccount(100);
        a.setAmount(20);
        assertEquals(20, a.getAmount());
    }

    @Test
    public void testWithdraw() {
        Account a = new CheckingAccount(100);
        a.setAmount(20);
        a.deposit(10);
        assertEquals(30, a.getAmount());
    }

    @Test
    public void testLargerAccount() {
        Account small = new CheckingAccount(20);
        Account big = new CheckingAccount(30);
        assertEquals(big, Account.largerAccount(small, big));
        assertEquals(big, Account.largerAccount(big, small));
    }

    @Test
    public void testSignUpForChecking() {
        MoneySaver david = new MoneySaver("David", 100);
        david.signUpForChecking(50);  // put 50 dollars in a checking account

        assertEquals("David, My balance is: 50", david.toString());
        assertEquals("Checking Account Balance: $50", david.getMyAccount().toString());
    }

    @Test
    public void testJimBalance() {
        MoneySaver jim = new MoneySaver("Jim", 100);
        jim.signUpForChecking(30);

        assertEquals("Jim, My balance is: 70", jim.toString());
        assertEquals(30, jim.getMyAccount().getAmount());

        jim.deposit(40);

        assertEquals("Jim, My balance is: 30", jim.toString());
        assertEquals(70, jim.getMyAccount().getAmount());

        jim.withdraw(60);

        assertEquals("Jim, My balance is: 90", jim.toString());
        assertEquals(10, jim.getMyAccount().getAmount());
    }
}