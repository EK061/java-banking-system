package BankingSystem;

import java.util.Scanner;

/**
 * eine von der Logik getrennte Benutzeroberfläche auf der Konsole
 */
public class UI {

    private Bank bank;
    private Scanner scanner = new Scanner(System.in);


    public UI (Bank bank) {
        this.bank = bank;
    }

    /**
     * einfacher String, welcher die verschiedenen Optionen auf der Konsole anzeigt
     */
    public void multipleChoice(){
        System.out.println("\n--- Banking System ---" +
                "\n1. Kunde anlegen" +
                "\n2. Konto für Kunde anlegen" +
                "\n3. Einzahlen" +
                "\n4. Abheben" +
                "\n5. Kontostand anzeigen" +
                "\n6. Überweisen" +
                "\n7. Beenden" +
                "\nAuswahl: ");
    }

    /**
     * Methode zum Starten, um die main Methode so einfach wie möglich zu halten
     */
    public void start() {
        boolean running = true;

        while (running) {
            multipleChoice();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createCustomer();
                case 2 -> createAccount();
                case 3 -> depositMoney();
                case 4 -> withdrawMoney();
                case 5 -> showBalance();
                case 6 -> transferMoney();
                case 7 -> running = false;
                default -> System.out.println("Ungültige Auswahl!");
            }
        }
    }

    /**
     * Kunden anlegen
     */
    private void createCustomer() {
        System.out.print("Name des Kunden: ");
        String name = scanner.nextLine();

        Customer customer = new Customer(name);
        bank.addCustomer(customer);

        System.out.println("Kunde \"" + name + "\" wurde angelegt.");
    }

    /**
     * Konto anlegen
     */
    private void createAccount() {
        System.out.print("Name des Kunden: ");
        String name = scanner.nextLine();

        Customer customer = bank.findCustomer(name);

        if (customer == null) {
            System.out.println("Kunde nicht gefunden!");
            return;
        }

        System.out.print("Kontonummer eingeben: ");
        String number = scanner.nextLine();

        Account account = new Account(number);
        customer.addAccount(account);

        System.out.println("Konto erstellt für " + customer.getName());
    }

    /**
     * Geld einzahlen
     */
    private void depositMoney() {
        Account account = findAccount();
        if (account == null) return;

        System.out.print("Betrag zum Einzahlen: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        account.deposit(amount);
        System.out.println("Neuer Kontostand: " + account.getBalance());
    }

    /**
     * Geld abheben
     */
    private void withdrawMoney() {
        Account account = findAccount();
        if (account == null) return;

        System.out.print("Betrag zum Abheben: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (account.withdraw(amount)) {
            System.out.println("Neuer Kontostand: " + account.getBalance());
        } else {
            System.out.println("Abhebung fehlgeschlagen (zu wenig Guthaben?)");
        }
    }

    /**
     * Kontostand anzeigen
     */
    private void showBalance() {
        Account account = findAccount();
        if (account == null) return;

        System.out.println("Kontostand: " + account.getBalance());
    }

    /**
     * Geld überweisen
     */
    private void transferMoney() {
        System.out.print("Von Kontonummer: ");
        String from = scanner.nextLine();

        System.out.print("Zu Kontonummer: ");
        String to = scanner.nextLine();

        System.out.print("Betrag: ");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Ungültiger Betrag!");
            return;
        }

        boolean success = bank.transfer(from, to, amount);

        if (success) {
            System.out.println("Überweisung erfolgreich!");
        } else {
            System.out.println("Überweisung fehlgeschlagen!");
        }
    }

    /**
     * Konto anhand von Kunde + Kontonummer finden
     * @return ist das zutreffende Konto
     */
    private Account findAccount() {
        System.out.print("Name des Kunden: ");
        String name = scanner.nextLine();

        Customer customer = bank.findCustomer(name);

        if (customer == null) {
            System.out.println("Kunde nicht gefunden!");
            return null;
        }

        System.out.print("Kontonummer: ");
        String number = scanner.nextLine();

        for (Account acc : customer.getAccounts()) {
            if (acc.getAccountNumber().equalsIgnoreCase(number)) {
                return acc;
            }
        }

        System.out.println("Konto nicht gefunden!");
        return null;
    }
}


