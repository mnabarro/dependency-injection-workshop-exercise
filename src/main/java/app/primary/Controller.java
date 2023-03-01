package app.primary;

import app.core.Contact;
import app.core.GetContacts;
import app.core.Message;
import app.core.SendMessage;

import java.util.List;

public class Controller {
    private final GetContacts getContacts;
    private final SendMessage sendMessage;

    public Controller(GetContacts getContacts, SendMessage sendMessage) {
        this.getContacts = getContacts;
        this.sendMessage = sendMessage;
    }

    public List<Contact> getContacts() {
        return getContacts.execute();
    }

    public void sendMessage(Contact contact, String content) {
        Message message = new Message(content, contact);
        sendMessage.execute(message);
    }
}
