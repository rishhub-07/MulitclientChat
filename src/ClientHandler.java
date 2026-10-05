import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

public class ClientHandler implements Runnable {
    
    private final Socket socket;
    private PrintWriter out;

    ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override 
    public void run(){
        try{
             BufferedReader in  = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Welcome to Chat server");
            String message;

            while ((message = in.readLine()) != null){
                System.out.println("Client says: "+ message);
                
            }

            System.out.println("Client Disconnected!");
        }
        catch (IOException e){
            System.out.println("Connection error with client "+ e.getMessage());

        }
        finally{
            try{
                socket.close();
            }
            catch( IOException e){
                e.printStackTrace();
            }
        }

    }
}
