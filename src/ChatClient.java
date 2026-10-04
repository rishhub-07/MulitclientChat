import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080)){

            System.out.println("Connected to Chat Server");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner scanner = new  Scanner(System.in);
            while (true) {
                
                String message = scanner.nextLine();
                out.println(message);

            }
            

        }

        catch(IOException e){
            e.printStackTrace();
        }

    
    }
}
