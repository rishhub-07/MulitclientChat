import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {
    
    private final Socket socket;

    ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override 
    public void run(){
        try{
             BufferedReader in  = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

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
