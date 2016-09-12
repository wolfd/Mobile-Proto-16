package io.wolfd.mobileproto.lesson2.money;

public class MoneySaver {

    private long myMoney;
    private Account myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public static void main(String[] args) {
    }

    public String getName() {
        return name;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public String toString() {
        return name + ", " + "My balance is: " + myMoney;
    }

    public void deposit(long amount) {
        myAccount.deposit(amount);
        myMoney -= amount;
    }

    public void withdraw(long amount) {
        if (myAccount instanceof CheckingAccount) {
            ((CheckingAccount) myAccount).withdraw(amount);
            myMoney += amount;
        }
    }

    public void signUpForChecking(long amount) {
        myAccount = new CheckingAccount(amount);
        myMoney -= amount;
    }
}