package com.github.frankkwok.corejava.v2ch04.mail;

import com.github.frankkwok.corejava.util.ResourceUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author Frank Kwok on 2017/4/28.
 */
public class MailTest {
    public static void main(String[] args) throws IOException, MessagingException {
        Properties properties = new Properties();
        try (InputStream in = ResourceUtils.newInputStream("mail.properties")) {
            properties.load(in);
        }

        String messageFilename = args.length > 0 ? args[0] : "message.txt";
        List<String> lines = ResourceUtils.readAllLines(messageFilename);

        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        StringBuilder builder = new StringBuilder();
        for (int i = 3; i < lines.size(); i++) {
            builder.append(lines.get(i));
            builder.append("\n");
        }

        Console console = System.console();
        String password = new String(console.readPassword());

        Session mailSession = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(builder.toString());

        Transport transport = mailSession.getTransport();
        try {
            transport.connect(null, password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }
}
