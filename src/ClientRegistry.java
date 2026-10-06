import java.util.concurrent.CopyOnWriteArrayList;


public class ClientRegistry {
    private final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public  void addClient(ClientHandler client){
        clients.add(client);
    }

    public void broadcast(String message){
        for (ClientHandler client : clients){
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client){
        clients.remove(client);
    }
}
