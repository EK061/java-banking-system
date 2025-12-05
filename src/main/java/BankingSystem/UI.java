package BankingSystem;
import java.util.Scanner;
public class UI {

    private Bank bank;
    private Scanner scanner = new Scanner(System.in);

    // Konstruktor – UI bekommt Zugriff auf das Bank-Objekt
    public UI (Bank bank) {
        this.bank = bank;
    }
    public String multipleChoice(){
        return "\n--- Banking System ---" +
                "\n1. Kunde anlegen" +
                "\n2. Konto für Kunde anlegen" +
                "\n3. Einzahlen" +
                "\n4. Abheben" +
                "\n5. Kontostand anzeigen" +
                "\n6. Beenden" +
                "\n Auswahl: ";
    }

    // Hauptmenü der Anwendung
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println(multipleChoice());
            int choice = scanner.nextInt();
            scanner.nextLine(); // Buffer leeren

            switch (choice) {
                case 1 -> createCustomer();
                case 2 -> createAccount();
                case 3 -> depositMoney();
                case 4 -> withdrawMoney();
                case 5 -> showBalance();
                case 6 -> running = false;
                default -> System.out.println("Ungültige Auswahl!");
            }
        }
    }

    // Kunde anlegen
    private void createCustomer() {
        System.out.print("Name des Kunden: ");
        String name = scanner.nextLine();

        Customer customer = new Customer(name);
        bank.addCustomer(customer);

        System.out.println("Kunde \"" + name + "\" wurde angelegt.");
    }

    // Konto anlegen
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

    // Geld einzahlen
    private void depositMoney() {
        Account account = findAccount();
        if (account == null) return;

        System.out.print("Betrag zum Einzahlen: ");
        double amount = scanner.nextDouble();

        account.deposit(amount);
        System.out.println("Neuer Kontostand: " + account.getBalance());
    }

    // Geld abheben
    private void withdrawMoney() {
        Account account = findAccount();
        if (account == null) return;

        System.out.print("Betrag zum Abheben: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Neuer Kontostand: " + account.getBalance());
        } else {
            System.out.println("Abhebung fehlgeschlagen (zu wenig Guthaben?)");
        }
    }

    // Kontostand anzeigen
    private void showBalance() {
        Account account = findAccount();
        if (account == null) return;

        System.out.println("Kontostand: " + account.getBalance());
    }

    // Helfermethode — findet ein Konto anhand von Kunde + Kontonummer
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

