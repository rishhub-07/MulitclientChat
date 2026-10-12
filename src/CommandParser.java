public class CommandParser {
    public Command parse(String message){
        if (message.equals("/users")){

            return new Command("USERS", "");

        }

        if (message.startsWith("/nick ")){

            String username = message.substring(6);

            return new Command("NICK", username);
        }
        if (message.startsWith("/w ")){
            return new Command("WHISPER", message.substring(3));
        }
        if (message.startsWith("/")){
            return new Command("UNKNOWN", message);
        }
        

        return new Command("MESSAGE", message);
    }
}
