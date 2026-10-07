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

    public void setUsername(String new_username){
        this.username = new_username;
    }

    public void sendMessage(String message){
        out.println(message);
    }

    public String getUsername(){
        return username;
    }

    @Override 
    public void run(){
        try{

            CommandParser parser = new CommandParser();

             BufferedReader in  = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
            
            String usernameMessage = in.readLine();
            if (usernameMessage != null && usernameMessage.startsWith("Username:")){
                username = usernameMessage.substring(9);
            }
            System.out.println(username + " Connected");
            out.println("Welcome to Chat server " + username);


            String message;

            while ((message = in.readLine()) != null){

                // if (message.equals("/users")){
                //     sendMessage(registry.getUserList());
                // }
                
                // else if(message.contains("/nick ")){
                //     String new_username = message.substring(6);
                //     setUsername(new_username);
                //     sendMessage("Your username is now: "+new_username);
                // }
                // else{
                //     System.out.println(username + " says: "+ message);
                //     registry.broadcast("["+username + "] " + message, this);
                // }
                Command command = parser.parse(message);

                if (command.getName().equals("USERS")){

                    sendMessage(registry.getUserList());
                }
                else if ( command.getName().equals("NICK")){

                    String new_username = command.getArgument();
                    if (new_username.isBlank()){
                        sendMessage(message);
                    }
                    setUsername(new_username);
                    sendMessage("Your username is now: "+new_username);
                }
                else{
                    registry.broadcast("["+username + "] " + message, this);
                }
                
            }

             
            System.out.println( username+" Disconnected!");

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
