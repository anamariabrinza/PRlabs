import org.apache.commons.mail.*;
import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by Amy on 5/24/2017.
 */
public class Main {

    public static void main( String[] args ) throws IOException {
        System.out.println("Menu");
        System.out.println("1. Send the message");
        System.out.println("2. Send a message with attachment");
        System.out.println("3. Read the message");

        Scanner scanner = new Scanner(System.in);



        switch (scanner.nextInt()) {

            case 1:

                System.out.println("Client simplu de posta – trimitere mesaj");
                try {
                    Email email = new SimpleEmail();
                    email.setHostName("127.0.0.1");
                    email.setSmtpPort(25);
                    email.setFrom("b@mail.md");
                    email.setSubject("Mesaj de la mine pentru tine");
                    email.setMsg("Mesaj frumos si fragut!");
                    email.addTo("ana@mail.md");
                    email.send();

                } catch (EmailException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                System.out.println("Client simplu de posta – trimitere mesaj cu atașament");
                try {

                    // Create the attachment
                    EmailAttachment attachment = new EmailAttachment();
                    attachment.setPath("D:\\UTM\\Anul 3\\PR\\Lab#4\\pr_lab4/UTM.png");
                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
                    attachment.setDescription("Picture of John");
                    attachment.setName("John");

                    // Create the email message
                    MultiPartEmail email = new MultiPartEmail();
                    email.setHostName("127.0.0.1");
                    email.setFrom("b@mail.md");
                    email.setSubject("The picture");
                    email.setMsg("Here is the picture you wanted");
                    email.addTo("ana@mail.md");


                    // add the attachment
                    email.attach(attachment);

                    // send the email
                    email.send();

                }  catch (EmailException e) {
                    e.printStackTrace();
                }
                break;




                case 3:
                System.out.println("Client simplu de posta – citire mesaj");
                try {

                    POP3Client client = new POP3Client();
                    client.connect( "127.0.0.1", 110 );
                    if( client.login( "ana", "ana" ) )

                    {

                        POP3MessageInfo[] messages = client.listMessages();
                        System.out.println( "Mesaje: " + messages.length );
                        System.out.println( "Textul mesajului");
                        Reader r = client.retrieveMessage( messages[ 8 ].number );
                        BufferedReader br = new BufferedReader( r );
                        String line;
                        while( ( line = br.readLine()) != null )
                        {
                            System.out.println(line);
                        }
                    }
                    else
                    {
                        System.out.println( "Logare fara succes..." );
                    }
                    client.logout();
                    client.disconnect();
                } catch (IOException ex){ }
                break;



            default:
                System.out.println("Default choice");
                break;
        }
        }
}
