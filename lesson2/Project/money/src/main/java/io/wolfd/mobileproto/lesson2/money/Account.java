package io.wolfd.mobileproto.lesson2.money;

public abstract class Account {
    private long amount;

    public Account(long amount) {
        this.amount = amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
        if (acc1.amount > acc2.amount) {
            return acc1;
        } else {
            return acc2;
        }
    }

    public String toString() {
        return "Account Balance: $" + amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void deposit(long amount) {
        this.amount += amount;
    }
}
