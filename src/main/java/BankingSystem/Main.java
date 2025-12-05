package BankingSystem;
public class Main {
    public static void main(String[] args) {

        /**
         * Bank erstellen
         */
        Bank bank = new Bank();

        /**
         * UI erzeugen und starten
         */
        UI ui = new UI(bank);
        ui.start();
    }
}

