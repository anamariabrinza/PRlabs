
import java.io.*;
import java.net.*;

    public class Client {
        public static void main(String[] args)
        {
            try {
                Socket sock = new Socket("localhost",3002);
                SendThread sendThread = new SendThread(sock);
                Thread thread1 = new Thread(sendThread);
                thread1.start();
                RecieveThread recieveThread = new RecieveThread(sock);
                Thread thread2 =new Thread(recieveThread);
                thread2.start();
            } catch (Exception e) {System.out.println(e.getMessage());}
        }
    }

class SendThread implements Runnable
{
    Socket sock=null;
    PrintWriter print=null;
    BufferedReader brinput=null;

    public SendThread(Socket sock)
    {
        this.sock = sock;
    }//end constructor
    public void run(){
        try{
            if(sock.isConnected())
            {
                System.out.println("Client connected... ");
                this.print = new PrintWriter(sock.getOutputStream(), true);
                System.out.println("message to send to server..type 'EXIT' to exit");
                while(true){

                    brinput = new BufferedReader(new InputStreamReader(System.in));
                    String msgtoServerString=null;
                    msgtoServerString = brinput.readLine();
                    this.print.println(msgtoServerString);
                    this.print.flush();

                    if(msgtoServerString.equals("EXIT"))
                        break;
                }
                sock.close();}}
        catch(Exception e){System.out.println(e.getMessage());}
    }
}

    class RecieveThread implements Runnable
    {
        Socket sock=null;
        BufferedReader recieve=null;
        public RecieveThread(Socket sock) {
            this.sock = sock;
        }
        public void run() {
            try{
                recieve = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
                String msgRecieved = null;
                while((msgRecieved = recieve.readLine())!= null)
                {
                    System.out.println("Server: " + msgRecieved);

                }
            }catch(Exception e){System.out.println(e.getMessage());}
        }
    }


