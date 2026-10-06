import java.io.BufferedReader;
import java.io.IOException;

public class ServerListenser implements Runnable {
    private final BufferedReader in ; 
    public ServerListenser(BufferedReader in){
        this.in = in;
    }
    
    @Override 
    public void run(){
        try{
            String message;
            
            while ((message = in.readLine())!= null) {
                System.out.println(message);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
