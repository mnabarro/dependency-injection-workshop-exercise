package app;

import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.core.Contact;
import app.primary.CLI;
import app.primary.Controller;
import app.primary.Presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader  = new BufferedReader(inputStreamReader);
        CLI cli = new CLI(bufferedReader);
        Presenter presenter = new Presenter(cli);

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
