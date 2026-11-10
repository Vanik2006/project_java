public class Main {
    public static void main(String[] args) {
        BankAccount t1 = new BankAccount(5000);
        BankAccount t2 = new BankAccount(10000);
        t1.showAmtAll();
        t1.transfer(t2, 500);
        t1.showAmtAll();
        t2.showAmtAll();
    }
}
