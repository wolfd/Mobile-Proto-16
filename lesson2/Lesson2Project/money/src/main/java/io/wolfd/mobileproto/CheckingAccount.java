package io.wolfd.mobileproto;

public class CheckingAccount extends Account {
    public CheckingAccount(long amount) {
        super(amount);
    }

    public void withdraw(long withdrawAmount) {
        // should probably check the amount in the account to see if possible
        this.setAmount(this.getAmount() - withdrawAmount);
    }

    public String toString() {
        return "Checking " + super.toString();
    }
}
