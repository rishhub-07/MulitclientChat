public class Command {
    private final String name;
    private final String argument;

    public Command(String name , String argument){
        this.name = name;
        this.argument = argument;
    }

    public String getName(){
        return this.name;
    }

    public String getArgument(){
        return this.argument;
    }
}
