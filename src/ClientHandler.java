import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

public class ClientHandler implements Runnable {
    
    private final Socket socket;
    private final ClientRegistry registry;
    private PrintWriter out;
    private String username;


    ClientHandler(Socket socket, ClientRegistry registry){
        this.socket = socket;
        this.registry = registry;
    }

    public void sendMessage(String message){
        out.println(message);
    }

    @Override 
    public void run(){
        try{
             BufferedReader in  = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome to Chat server");


            String usernameMessage = in.readLine();
            if (usernameMessage != null && usernameMessage.startsWith("Username:")){
                username = usernameMessage.substring(9);
            }


            String message;

            while ((message = in.readLine()) != null){
                System.out.println("Client says: "+ message);
                registry.broadcast("["+username + "] " + message);
                
            }

            
            System.out.println("Client Disconnected!");

        }
        catch (IOException e){
            System.out.println("Connection error with client "+ e.getMessage());

        }
        finally{
            registry.removeClient(this);
            try{
                socket.close();
            }
            catch( IOException e){
                e.printStackTrace();
            }
        }

    }
}
