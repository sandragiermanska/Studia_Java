package lab3;

public class Main {

    public static void main(String[] args) {
        EmailMessage message = EmailMessage.builder()
                .addFrom("sgiermanska@student.agh.edu.pl")
                .addTo("sandra.giermanska@gmail.com")
                .build();

        message.send();
    }
}
