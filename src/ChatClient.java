import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        Scanner scanner = new  Scanner(System.in);
        try (Socket socket = new Socket("127.0.0.1", 8080)){

            
            System.out.println("Connected to Chat Server");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in  = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            ServerListenser listenser = new ServerListenser(in);
            
            Thread listenerThread = new Thread(listenser);
            listenerThread.start();
            
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            out.println("Username:"+ username);
            
            
            
            while (true) {
                
                String message = scanner.nextLine();
                out.println(message);

                

            }
            
        }

        catch(IOException e){
            e.printStackTrace();
        }
        scanner.close();
        
    }
}
