package tcp_echo1client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCP_Echo1Client
{
    private static InetAddress host;
    private static final int PORT = 1234;
    

    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch (UnknownHostException e)
                {
                    System.out.println("Could not find ");
                    System.exit(1);
                }
        run();
    }
    
    private static void run()
    {
        Socket link = null;
        try
        {
            link = new Socket(host,PORT);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(
            link.getOutputStream(),true);
            BufferedReader userEntry = 
                    new BufferedReader(new InputStreamReader(System.in));
            String message,response;
            do
            {
                System.out.print("Please enter message: ");
                message = userEntry.readLine();
                out.println(message);
                response = in.readLine();
                System.out.println("SERVER> " + response);
            } while (!message.equals("***CLOSE***"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("* Closing connection... *");
                link.close();
            }
            catch (IOException e)
            {
                System.out.println("Unable to disconnect");
                System.exit(1);
            }
        }
    }
    
}
