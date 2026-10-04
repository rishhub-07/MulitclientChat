import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    public static void main(String[] args) {
        try( ServerSocket serverSocket = new ServerSocket(8080))
        {
            System.out.println("Chat server started");
            System.out.println("Waiting for a Client");

            ArrayList<ClientHandler> clients = new ArrayList<>();
            
            while (true){

                Socket socket  = serverSocket.accept();
                System.out.println("Client Connected! ");

                ClientHandler handler = new ClientHandler(socket);
                
                clients.add(handler);
                
                Thread clientthread = new Thread(handler);

                clientthread.start();   

            }
            
        }
        catch(IOException  e){
            e.printStackTrace();
        }
    }
}
