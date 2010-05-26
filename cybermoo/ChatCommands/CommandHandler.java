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
        commands.put("help", new CommandHelp());
        commands.put("@quit", new CommandQuit());
    }

    public void parse(String text, ThreadedClient source) {
        String[] tokens = text.trim().split(" ");
        try {
            Command command = getCommands().get(tokens[0].toLowerCase());
            if (command != null) {
                String[] arguments;
                if (tokens.length > 1) {
                    arguments = text.substring(text.indexOf(" ")).trim().split(" ");
                } else {
                    arguments = null;
                }
                if (command.isCleared(source)) {
                    command.call(arguments, source);
                } else {
                    source.sendText("You do not have permission to use the requested command");
                }
            } else {
                source.sendText("I don't understand what you mean by \"" + tokens[0] + "\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the commands
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * @param commands the commands to set
     */
    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }
}
