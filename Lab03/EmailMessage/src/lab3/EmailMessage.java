package lab3;

import com.sun.mail.smtp.SMTPTransport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailMessage {

    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional

    private EmailMessage() {}

    private EmailMessage(Builder builder) {
        if (builder == null) {
            return;
        }
        from = builder.from;
        to = builder.to;
        subject = builder.subject;
        content = builder.content;
        mimeType = builder.mimeType;
        cc = builder.cc;
        bcc = builder.bcc;
    }

    public static class Builder {
        private String from; //required (must be e-mail)
        private LinkedList<String> to = new LinkedList<>(); //required at least one (must be e-mail)
        private String subject; //optional
        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc = new LinkedList<>(); //optional
        private LinkedList<String> bcc = new LinkedList<>(); // optional

        Builder addFrom(String from) {
            this.from = from;
            return this;
        }
        Builder addTo(String to) {
            this.to.add(to);
            return this;
        }
        Builder addSubject(String subject) {
            this.subject = subject;
            return this;
        }
        Builder addContent(String content) {
            this.content = content;
            return this;
        }
        Builder addMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }
        Builder addCc(String cc) {
            this.cc.add(cc);
            return this;
        }
        Builder addBcc(String bcc) {
            this.bcc.add(bcc);
            return this;
        }


        EmailMessage build() {
            return new EmailMessage(this);
        }
    }

    public static Builder builder() {
        return new EmailMessage.Builder();
    }

    public void send() {

        Pattern patternHost = Pattern.compile("@(.+)");
        Pattern patternLogin = Pattern.compile("(.+)@");
        Matcher matcher = patternHost.matcher(from);
        String host = new String();
        String login = new String();
        if (matcher.find()) {
            host = matcher.group();
        }
        matcher = patternLogin.matcher(from);
        if (matcher.find()) {
            login = matcher.group();
        }

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "578");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, null);

        if (from != null && to != null) {

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));

                InternetAddress[] toInternetAddress = new InternetAddress[to.size()];
                for (int i = 0; i < to.size(); i++) {
                    toInternetAddress[i] = new InternetAddress(to.get(i));
                }
                message.addRecipients(Message.RecipientType.TO, toInternetAddress);

                if (cc != null) {
                    InternetAddress[] ccInternetAddress = new InternetAddress[cc.size()];
                    for (int i = 0; i < cc.size(); i++) {
                        toInternetAddress[i] = new InternetAddress(cc.get(i));
                    }
                    message.addRecipients(Message.RecipientType.CC, ccInternetAddress);
                }

                if (bcc != null) {
                    InternetAddress[] bccInternetAddress = new InternetAddress[bcc.size()];
                    for (int i = 0; i < bcc.size(); i++) {
                        toInternetAddress[i] = new InternetAddress(bcc.get(i));
                    }
                    message.addRecipients(Message.RecipientType.BCC, bccInternetAddress);
                }

                if (subject != null) {
                    // Set Subject: header field
                    message.setSubject(subject);
                }

                if (content != null) {
                    if (mimeType != null) {
                        // Now set the actual message
                        message.setContent(content, mimeType);
                    }
                    else {
                        message.setText(content);
                    }
                }

                // Send message
                SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Podaj haslo:");
                String password = bf.readLine();
                transport.connect(host, login, password);
                Transport.send(message);
                System.out.println("Sent message successfully....");

            } catch (MessagingException mex) {
                mex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


