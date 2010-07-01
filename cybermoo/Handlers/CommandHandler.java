package cybermoo.Handlers;

/**
 * Parses a player's input, calling the specified command
 * with any arguments which have been included
 * @author Shane
 */

import cybermoo.ChatCommands.Command;
import cybermoo.ChatCommands.CommandCreateScene;
import cybermoo.ChatCommands.CommandHelp;
import cybermoo.ChatCommands.CommandLinkScene;
import cybermoo.ChatCommands.CommandLogin;
import cybermoo.ChatCommands.CommandLook;
import cybermoo.ChatCommands.CommandMe;
import cybermoo.ChatCommands.CommandMove;
import cybermoo.ChatCommands.CommandQuit;
import cybermoo.ChatCommands.CommandRegister;
import cybermoo.ChatCommands.CommandSay;
import cybermoo.ChatCommands.CommandStatus;
import cybermoo.ChatCommands.CommandWho;
import cybermoo.util.Annotations;
import cybermoo.ThreadedClient;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private static CommandHandler instance;
    private Map<String, Command> commands;

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    public CommandHandler() {
        this.commands = new HashMap<String, Command>();
        this.loadCommand("cybermoo.ChatCommands.CommandWho");
        commands.put("say", new CommandSay());
        commands.put("register", new CommandRegister());
        commands.put("login", new CommandLogin());
        commands.put("help", new CommandHelp());
        commands.put("@quit", new CommandQuit());
        commands.put("move", new CommandMove());
        commands.put("look", new CommandLook());
        commands.put("me", new CommandMe());
        commands.put("createscene", new CommandCreateScene());
        commands.put("linkscene", new CommandLinkScene());
        commands.put("status", new CommandStatus());
    }

    public void parse(String text, ThreadedClient source) {
        String[] tokens = text.trim().split(" ");
        try {
            Command command = getCommands().get(tokens[0].toLowerCase());
            if (command != null) {
                String[] arguments = tokens.length > 1 ? text.substring(text.indexOf(" ")).trim().split(" ") : null;
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
    
    protected void loadCommand(String className) {
        try {
            // try to load the class the user entered(via configuration or so)
            Class<?> cla = Class.forName(className);
            
            // now make sure it's a command
            if(!cla.isInstance(Command.class)) {
                // hmm.. no.. explode
                throw new IllegalArgumentException("Entered class '" + className + "' does not implement the Command interface");
            }
            
            // now let the other load command utility method handle that ;)
            this.loadCommand((Class<? extends Command>) cla);
        } catch(ClassNotFoundException exc) {
            // err.. user entered nothing useful
            throw new IllegalArgumentException("Entered class '" + className + "' does not exist");
        }
            
    }
    protected void loadCommand(Class<? extends Command> clas) {
        // load the aliases for that class
        String[] aliases = Annotations.extractAliases(clas);
        
        // create an instance of that command
        Command cmd;
        try {
            Constructor<? extends Command> ctor = clas.getConstructor();
            cmd = ctor.newInstance();
        } catch(InvocationTargetException exc) {
            throw new RuntimeException("Failed to load command '" + clas + "'", exc);
        } catch(InstantiationException exc) {
            throw new IllegalArgumentException("Failed to load command '" + clas + "'", exc);
        } catch(IllegalAccessException exc) {
            throw new RuntimeException("Failed to load command '" + clas + "'", exc);
        } catch(NoSuchMethodException exc) {
            throw new RuntimeException("Failed to load command '" + clas + "'", exc);
        }
        
        // now add it under each alias
        // TODO: don't just overwrite existing bindings
        for(int i=0; i < aliases.length; i++)
            this.commands.put(aliases[i], cmd);
    }
}
