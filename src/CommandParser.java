public class CommandParser {
    public Command parse(String message){
        if (message.equals("/users")){

            return new Command("USERS", "");

        }

        if (message.startsWith("/nick ")){

            String username = message.substring(6);

            return new Command("NICK", username);
        }

        return new Command("MESSAGE", message);
    }
}
