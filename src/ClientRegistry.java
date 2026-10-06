import java.util.concurrent.CopyOnWriteArrayList;


public class ClientRegistry {
    private final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public  void addClient(ClientHandler client){
        clients.add(client);
    }

    public void broadcast(String message, ClientHandler sender){
        for (ClientHandler client : clients){
            if (client != sender){
                client.sendMessage(message);
            }   
        }
    }

    public void removeClient(ClientHandler client){
        clients.remove(client);
    }
}
