import java.io.*;
import java.net.*;
import java.lang.*;

public class Server{
    public static void main(String[] args) throws IOException {

        System.out.println("Server waiting for connection...");
        ServerSocket ss = new ServerSocket(3002);
        Socket clientSocket = ss.accept();
        System.out.println("Server connected ");
        //create two threads to send and recieve data from client
        RecieveFromClientThread recieve = new RecieveFromClientThread(clientSocket);
        Thread thread1 = new Thread(recieve);
        thread1.start();
        SendToClientThread send = new SendToClientThread(clientSocket);
        Thread thread2 = new Thread(send);
        thread2.start();
    }}

class RecieveFromClientThread implements Runnable
{
    Socket clientSocket=null;
    BufferedReader brBufferedReader = null;

    public RecieveFromClientThread(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }
    public void run() {
        try{
            brBufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            String messageString;
            while(true){
                while((messageString = brBufferedReader.readLine())!= null){
                    if(messageString.equals("EXIT"))
                    {
                        break;
                    }
                    System.out.println("Client: " + messageString);

                }
                this.clientSocket.close();
                System.exit(0);
            }
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
}
class SendToClientThread implements Runnable
{
    PrintWriter pwPrintWriter;
    Socket clientSock = null;

    public SendToClientThread(Socket clientSock)
    {
        this.clientSock = clientSock;
    }
    public void run() {
        try{
            pwPrintWriter =new PrintWriter(new OutputStreamWriter(this.clientSock.getOutputStream()));//get outputstream
            while(true)
            {
                String msgToClientString = null;
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//get userinput
                msgToClientString = input.readLine();//get message to send to client

                pwPrintWriter.println(msgToClientString);//send message to client with PrintWriter
                pwPrintWriter.flush();

            }
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
}

