final class BankAccount{
    private Balance bal;
    private double amt_min = 100;
    private double amt_all;

    BankAccount() {
        this.bal = new Balance();
        this.amt_all = 0;
    }

    Balance getBal() {
        return this.bal;
    }

    double getAmtAll() {
        return amt_all;
    }

    BankAccount(double amt) {
        this.bal = new Balance(amt);
        this.amt_all = 0;
    }

    BankAccount(Balance bal) {
        this.bal = bal;
        this.amt_all = 0;
    }

    BankAccount(Balance bal, double amt_all) {
        this.bal = bal;
        this.amt_all = amt_all;
    }

    void transfer(BankAccount b, double x) {
        if (x < amt_min) return;
        this.bal = this.bal.substract(x);
        Balance bal2 = b.getBal();
        Currency cur = bal2.getCur();
        bal2 = bal2.convert(this.bal.getCur());
        bal2 = bal2.add(x);
        b.bal = bal2.convert(cur);
        b.amt_all = b.getAmtAll() + x;
        this.amt_all += x;
    }

    void showAmtAll() {
        System.out.println("Amount of all operations: " + amt_all);
    }
}