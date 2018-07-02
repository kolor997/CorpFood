package CorpFood.mail;

import javax.mail.MessagingException;

public interface EmailSender {
    void sendEmail(
            String to, String subject, String text) throws MessagingException;
}
