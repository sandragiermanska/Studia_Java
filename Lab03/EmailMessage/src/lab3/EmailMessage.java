package lab3;

import java.util.LinkedList;

public class EmailMessage {

    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional

    static class Builder {
        Builder addFrom(String a) {

            return this;
        }
        Builder addTo(String to) {
            return this;
        }
        Builder addSubject(String subject) {
            return this;
        }
        Builder addContent(String content) {
            return this;
        }
        Builder addMimeType(String mimeType) {
            return this;
        }
        Builder addCc(String cc) {
            return this;
        }
        Builder addBcc(String bcc) {
            return this;
        }
    }

    //Przykładowy konstruktor (można założyć, że pola opcjonalne mogą być null)
    protected EmailMessage(String from,
                           LinkedList<String> to,
                           String subject,
                           String content,
                           String mimeType ,
                           LinkedList<String> cc,
                           LinkedList<String> bcc){
        // wiele if, else, sprawdzania czy string jest e-mail, itd.
    }

    protected EmailMessage(String from,
                           LinkedList<String> to){}

    public static Builder builder() {
        return new EmailMessage.Builder();
    }
}


