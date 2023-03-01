package app.core;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendMessageTest {

  private Message message;
  @Mock
  private SmsSender smsSender;

  @InjectMocks
  private SendMessage sendMessage;

    @Captor
    ArgumentCaptor<String> messageContentCaptor;

  @BeforeEach
  void setup() {
    Contact contact = new Contact("Name", "name@email.com", "635383838", "Fake St. 123");
    message = new Message("Hello!", contact);
  }

  @Test
  public void itPrependsAWinterEmojiInWinter() {
    final LocalDate dateWinter = LocalDate.of(2023, 2, 1);
    final String emojiWinter = "\u2744\uFE0F";

    sendMessage.execute(message, dateWinter);

    verify(smsSender).send(any(), messageContentCaptor.capture());

    String messageContent = messageContentCaptor.getValue();
    Assertions.assertThat(messageContent).startsWith(emojiWinter);
  }

  @Test
  public void itPrependsASpringEmojiInSpring() {
    final LocalDate dateSpring = LocalDate.of(2023, 5, 1);
    final String emojiSpring = "\uD83C\uDF37";

    sendMessage.execute(message, dateSpring);

    verify(smsSender).send(any(), messageContentCaptor.capture());

    String messageContent = messageContentCaptor.getValue();
    Assertions.assertThat(messageContent).startsWith(emojiSpring);
  }

  @Test
  public void itPrependsASummerEmojiInSummer() {
    final LocalDate dateSummer = LocalDate.of(2023, 8, 1);
    final String emojiSummer = "\uD83C\uDF1E";

    sendMessage.execute(message, dateSummer);

    verify(smsSender).send(any(), messageContentCaptor.capture());

    String messageContent = messageContentCaptor.getValue();
    Assertions.assertThat(messageContent).startsWith(emojiSummer);
  }

  @Test
  public void itPrependsAnAutumnEmojiInAutumn() {
    final LocalDate dateAutumn = LocalDate.of(2023, 11, 1);
    final String emojiAutumn = "\uD83C\uDF42";

    sendMessage.execute(message, dateAutumn);

    verify(smsSender).send(any(), messageContentCaptor.capture());

    String messageContent = messageContentCaptor.getValue();
    Assertions.assertThat(messageContent).startsWith(emojiAutumn);
  }
}
