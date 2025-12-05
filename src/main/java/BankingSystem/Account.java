package BankingSystem;

/**
 * Klasse Account, welche ein Konto für einen Kunden erstellt und ihre Attribute beinhaltet
 */
public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Methode zum Einzahlen von bestimmten Beträgen
     * @param amount bestimmt die Höhe der Summe
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    /**
     * Methode zum Abheben von bestimmten Beträgen
     * @param amount bestimmt die Höhe der Summe
     * (falls sie auch über 0 und größer als der Kontostand sind
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

}
