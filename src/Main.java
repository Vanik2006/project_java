public enum Currency {
    RUB("RUB", "₽", 100),
    USD("USD", "$", 9_600),
    EUR("EUR", "€", 10_300),
    CNY("CNY", "¥", 1_320);

    private final String code;
    private final String symbol;
    private final long priceInRublesKopecks;

    Currency(
            String code,
            String symbol,
            long priceInRublesKopecks
    ) {
        this.code = code;
        this.symbol = symbol;
        this.priceInRublesKopecks =
                priceInRublesKopecks;
    }
    public String getCode() {
        return code;
    }
    public String getSymbol() {
        return symbol;
    }
    public long getPriceInRublesKopecks() {
        return priceInRublesKopecks;
    }
}

final class Balance{
    private final double amt;
    private final Currency cur;

    Balance() {
        this.amt = 0;
        this.cur = Currency.RUB;
    }
    Balance(double amt) {
        this.amt = amt;
        this.cur = Currency.RUB;
    }
    Balance(double amt, Currency cur) {
        this.amt = amt;
        this.cur = cur;
    }
    double getAmt() {
        return this.amt;
    }
    Currency getCur() {
        return this.cur;
    }
    Balance add(double x) {
        if (x < 0) System.exit(-1);
        return new Balance(this.amt + x);
    }
    Balance substract(double x) {
        if (x < 0) System.exit(-1);
        return new Balance(this.amt - x);
    }
    Balance convert(Currency new_cur) {
        double new_amt = this.amt * this.cur.getPriceInRublesKopecks() / new_cur.getPriceInRublesKopecks();
        return new Balance(new_amt, new_cur);
    }
}

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
    double getAmt_all() {
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
        bal2.convert(this.bal.getCur());
        bal2 = bal2.add(x);
        b = new BankAccount(bal2, b.getAmt_all() + x);
        this.amt_all += x;
    }
    void show_amt_all() {
        System.out.println("Amount of all operations: " + amt_all);
    }
}

void main() {
    BankAccount t1 = new BankAccount(5000);
    BankAccount t2 = new BankAccount(10000);

    t1.show_amt_all();
    t1.transfer(t2,500);
    t1.show_amt_all();
    t2.show_amt_all();
}