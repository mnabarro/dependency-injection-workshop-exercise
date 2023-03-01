package app.core;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SendMessageTest {
    private final LocalDate dateWinter = LocalDate.of(2023,2,1);
    private final String emojiWinter = "\u2744\uFE0F";
    private final LocalDate dateSpring = LocalDate.of(2023,5,1);
    private final String emojiSpring = "\uD83C\uDF37";
    private final LocalDate dateSummer = LocalDate.of(2023,8,1);
    private final String emojiSummer = "\uD83C\uDF1E";
    private final LocalDate dateAutumn = LocalDate.of(2023,11,1);
    private final String emojiAutumn = "\uD83C\uDF42";
    private  Message message;
    @Mock
    private  SmsSender smsSender;

    @InjectMocks
    private  SendMessage sendMessage;

    @BeforeAll
    void setup(){
        Contact contact = new Contact("Name", "name@email.com", "635383838", "Fake St. 123");
        message = new Message("Hello!", contact);
    }

    @Captor
    ArgumentCaptor<String> messageContentCaptor;

    @Test
    public void itPrependsAWinterEmojiInWinter() {
        sendMessage.execute(message, dateWinter);
        verify(smsSender).send(any(), messageContentCaptor.capture());
        String messageContent = messageContentCaptor.getValue();
        Assertions.assertThat(messageContent).startsWith(emojiWinter);
    }

    @Test
    public void itPrependsASpringEmojiInSpring() {
        sendMessage.execute(message, dateSpring);
        verify(smsSender).send(any(), messageContentCaptor.capture());
        String messageContent = messageContentCaptor.getValue();
        Assertions.assertThat(messageContent).startsWith(emojiSpring);
    }

    @Test
    public void itPrependsASummerEmojiInSummer() {
        // TODO()
    }

    @Test
    public void itPrependsAnAutumnEmojiInAutumn() {
        // TODO()
    }
}
