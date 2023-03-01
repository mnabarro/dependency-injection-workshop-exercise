package app.core;

import java.time.LocalDate;

public class SendMessage {
    private final SmsSender smsSender;

    public SendMessage(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void execute(Message message) {
        String content = getSeasonEmoji() + message.content();

        smsSender.send(message.destination().telephone(), content);
    }

    private String getSeasonEmoji() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        String winter = "\u2744\uFE0F";
        String spring = "\uD83C\uDF37";
        String summer = "\uD83C\uDF1E";
        String autumn = "\uD83C\uDF42";

        return switch (month) {
            case 1, 2 -> winter;
            case 3 -> day < 20 ? winter : spring;
            case 4, 5 -> spring;
            case 6 -> day < 21 ? spring : summer;
            case 7, 8 -> summer;
            case 9 -> day < 22 ? summer : autumn;
            case 10, 11 -> autumn;
            case 12 -> day < 21 ? autumn : winter;
            default -> "";
        };
    }
}
