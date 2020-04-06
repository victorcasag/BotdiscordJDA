package PackageCommands;

import java.util.HashMap;

public class CommandHandler {

    public static HashMap<String, InterfaceCommand> commands = new HashMap<>();
    public static final CommandParse parser = new CommandParse();

    public  static void handleCommand(CommandParse.commandContainer cmd){

        if (commands.containsKey(cmd.invoke)){
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }else{
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
}
