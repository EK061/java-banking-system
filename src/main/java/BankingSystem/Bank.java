package BankingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Bank steuert die Logik und Prozesse für das
 * Finden oder Hinzufügen eines Kunden,
 * sowie überweisen von Beträgen zwischen zwei Konten
 */
public class Bank {
    private List<Customer> customers = new ArrayList<>();
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Durchsucht die Liste der Kunden, vergleichend mit der Eingabe in der Konsole
     * @param name sollte dem Attribut name des Customers ähneln
     * @return gibt bei zutreffendem namen den entsprechenden Kunden zurück
     */
    public Customer findCustomer(String name) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Genau wie beim Kunden werden hier die Konten durchsucht
     * @param accountNumber wobei es beim Konto auf die Kontennummer bezogen ist
     * @return gibt das gewünschte Konto zurück
     */
    public Account findAccount(String accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Führt eine Überweisung von einem Konto zu einem anderen durch.
     * Prüft:
     *  - existieren beide Konten?
     *  - Betrag gültig?
     *  - hat Konto A genug Guthaben?
     */
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account from = findAccount(fromAccountNumber);
        Account to = findAccount(toAccountNumber);

        if (from == null || to == null) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        if (!from.withdraw(amount)) {
            return false; // Guthaben nicht ausreichend
        }

        to.deposit(amount);
        return true;
    }
}