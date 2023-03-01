package app;

import app.core.Contact;
import app.core.GetContacts;
import app.core.SendMessage;
import app.core.SmsSender;
import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.primary.CLI;
import app.primary.Controller;
import app.primary.Presenter;
import app.secondary.ContactRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class App {

  public static void main(String[] args)
    throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    RegistrationsReader registrationsReader = new RegistrationsReader();

    List<Registration> registrations = registrationsReader.getRegistrations();

    Registration smsSenderImpl = registrations.stream().filter(registration ->
        registration.type().getName().equals("app.core.SmsSender"))
      .findFirst().orElseThrow();

    String className = smsSenderImpl.implementation().getName();
    SmsSender smsSender = (SmsSender) Class.forName(className).getDeclaredConstructor().newInstance();

    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    CLI cli = new CLI(bufferedReader);

    Presenter presenter = new Presenter(cli);

    ContactRepository contactRepository = new ContactRepository();
    GetContacts getContacts = new GetContacts(contactRepository);

    SendMessage sendMessage = new SendMessage(smsSender);

    Controller controller = new Controller(getContacts, sendMessage);

    while (true) {
      List<Contact> contacts = controller.getContacts();
      Contact chosenContact = presenter.requestContactChoice(contacts);
      String message = presenter.requestMessage(chosenContact.name());
      controller.sendMessage(chosenContact, message);
    }
  }
}
