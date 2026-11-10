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
        if (x < 0)
            throw new IllegalArgumentException("Аргумент должен быть положительным");
        return new Balance(this.amt + x, this.cur);
    }

    Balance substract(double x) {
        if (x < 0)
            throw new IllegalArgumentException("Аргумент должен быть положительным");
        return new Balance(this.amt - x, this.cur);
    }

    Balance convert(Currency new_cur) {
        double new_amt = this.amt * this.cur.getPriceInRublesKopecks() / new_cur.getPriceInRublesKopecks();
        return new Balance(new_amt, new_cur);
    }
}
