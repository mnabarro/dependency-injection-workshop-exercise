package app;

import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.core.Contact;
import app.primary.CLI;
import app.primary.Controller;
import app.primary.Presenter;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Presenter presenter = new Presenter(new CLI());
        Controller controller = new Controller();
        List<Registration> registrations = new RegistrationsReader().getRegistrations();

        System.out.println(registrations);

        while (true) {
            List<Contact> contacts = controller.getContacts();
            Contact chosenContact = presenter.requestContactChoice(contacts);
            String message = presenter.requestMessage(chosenContact.name());
            controller.sendMessage(chosenContact, message);
        }
    }
}
