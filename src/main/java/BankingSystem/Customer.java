package BankingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Customer beinhlatet die wichtigsten Attribute eines Kunden
 */
public class Customer {
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
