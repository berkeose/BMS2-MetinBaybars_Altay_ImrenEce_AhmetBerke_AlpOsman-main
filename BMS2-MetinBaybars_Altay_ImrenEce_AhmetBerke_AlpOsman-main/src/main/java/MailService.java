import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailService {
    //Method that send an email to receiver who books the ticket
    public void sendMail(String recipient) throws Exception {

        //This will be used for the configuring the email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        //Email address and the password for the sender account
        String myAccountEmail = "busmanagementsystem.ieu";
        String password = "busekonomi22";

        //Logs in to sender account
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Creates the message that will be send
        Message message = prepareMessage(session, myAccountEmail, recipient);

        //Sends email
        Transport.send(message);
    }

    //Method that prepares mail content, sets the sender and receiver account
    private Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Confirmation Email");
            message.setText("Your Booking is Confirmed!");
           // message.setText(busTrip.getInfoDisplay());
           /* String htmlcode="<hi></hi> <br/> <h2><b>Next Line</b></h2>";
            message.setContent(htmlcode,"text/html");*/
             return message;
        } catch (Exception ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
/*public class JavaMail{
    public static void  main(String[]args) throws Exception{
        JavaMailUtil.sendMail("busmanagementsystem.ieu");
    }
}*/
