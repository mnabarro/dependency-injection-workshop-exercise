package app.primary;

import app.core.Contact;

import java.io.IOException;
import java.util.List;

public class Presenter {
    private final CLI cli;

    public Presenter(CLI cli) {
        this.cli = cli;
    }

    public Contact requestContactChoice(List<Contact> contacts) {
        cli.printWhiteSpace();
        cli.display("CONTACTS LIST");
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            cli.display(i + ". " + contact.name());
        }
        cli.printWhiteSpace();
        cli.display("Select a contact to send a message (write contact number and press Enter):");

        Contact contact = null;

        do {
            try {
                int contactNumber = Integer.parseInt(cli.read());
                contact = contacts.get(contactNumber);
            } catch (Exception error) {
                cli.display("Entry does not correspond to a contact number. Please enter number again.");
            }
        } while (contact == null);


        return contact;
    }

    public String requestMessage(String contactName) throws IOException {
        cli.printWhiteSpace();
        cli.display("Write a message for " + contactName + ". It will be sent when you press Enter.");
        String message = cli.read();
        cli.printWhiteSpace();

        return message;
    }
}
