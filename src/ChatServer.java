import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try( ServerSocket serverSocket = new ServerSocket(8080))
        {
            System.out.println("Chat server started");
            System.out.println("Waiting for a Client");

            ClientRegistry registry = new ClientRegistry();

            while (true){

                Socket socket  = serverSocket.accept();
                // System.out.println("Client Connected! ");

                ClientHandler handler = new ClientHandler(socket, registry);
                
                registry.addClient(handler);
                
                Thread clientthread = new Thread(handler);

                clientthread.start();   

            }
            
        }
        catch(IOException  e){
            e.printStackTrace();
        }
    }
}
