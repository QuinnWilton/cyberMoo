package cybermoo.ChatCommands;

import cybermoo.ThreadedClient;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private Map<String, Command> commands;

    public CommandHandler() {
        commands = new HashMap<String, Command>();
        commands.put("who", new CommandWho());
    }

    public void parse(String text, ThreadedClient source) {
        String[] tokens = text.trim().split(" ");
        try {
            Command command = commands.get(tokens[0].toLowerCase());
            if(command != null) {
                command.call(tokens, source);
            } else {
                source.sendText("I don't understand what you mean by \"" + tokens[0] + "\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
