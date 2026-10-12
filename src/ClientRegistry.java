
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientRegistry {

    private final CopyOnWriteArrayList<ClientHandler> clients =
            new CopyOnWriteArrayList<>();

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public String getUserList() {
        StringBuilder result = new StringBuilder();
        result.append("Online Users:\n");

        for (ClientHandler client : clients) {
            result.append("- ")
                  .append(client.getUsername())
                  .append("\n");
        }

        return result.toString();
    }

    public ClientHandler getClientByUsername(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equalsIgnoreCase(username)) {
                return client;
            }
        }

        return null;
    }
}
