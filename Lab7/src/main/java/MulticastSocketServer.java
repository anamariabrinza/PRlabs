import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.*;

public class MulticastSocketServer {

    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;

    public static String msg;



    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        // Get the address that we are going to connect to.
        InetAddress addr = InetAddress.getByName(INET_ADDR);

        System.out.println("Enter the message you want to be sent to the client:");
        System.out.println("Enter EXIT in case you want to exit...");
        // Open a new DatagramSocket, which will be used to send the data.
        try (DatagramSocket serverSocket = new DatagramSocket()) {
            do {

                 msg = (new BufferedReader(new InputStreamReader(System.in))).readLine();

                // Create a packet that will contain the data
                // (in the form of bytes) and send it.
                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
                serverSocket.send(msgPacket);

               // System.out.println("Server sent packet with msg: " + msg);

            }while(!msg.equals("EXIT"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}