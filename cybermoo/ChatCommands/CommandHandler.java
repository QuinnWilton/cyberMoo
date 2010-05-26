package cybermoo.ChatCommands;

import cybermoo.ThreadedClient;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private Map<String, Command> commands;

    public CommandHandler() {
        commands = new HashMap<String, Command>();
        commands.put("who", new CommandWho());
        commands.put("say", new CommandSay());
        commands.put("register", new CommandRegister());
        commands.put("login", new CommandLogin());
    }

    public void parse(String text, ThreadedClient source) {
        String[] tokens = text.trim().split(" ");
        try {
            Command command = commands.get(tokens[0].toLowerCase());
            if (command.isCleared(source)) {
                String[] arguments;
                if (tokens.length > 1) {
                    arguments = text.substring(text.indexOf(" ")).trim().split(" ");
                } else {
                    arguments = tokens;
                }
                if (command != null) {
                    command.call(arguments, source);
                } else {
                    source.sendText("I don't understand what you mean by \"" + tokens[0] + "\"");
                }
            } else {
                source.sendText("You do not have permission to use the requested command");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
