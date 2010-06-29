package cybermoo.ChatCommands;

/**
 * Outputs a description of the player's current status
 * @author Shane
 */

import cybermoo.Player;
import cybermoo.TextAttributes;
import cybermoo.ThreadedClient;

@Name("status")
public class CommandStatus implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        Player player = source.getPlayer();
        String output = "\n";
        output += TextAttributes.BOLD + TextAttributes.FOREGROUND_MAGENTA + "Name:" + TextAttributes.ALL_ATTRIBUTES_OFF + player.getName() + "\n";
        output += TextAttributes.BOLD + TextAttributes.FOREGROUND_YELLOW + "Description:" + TextAttributes.ALL_ATTRIBUTES_OFF + source.getPlayer().getDescription() + "\n";
        output += generateBarGraph(TextAttributes.FOREGROUND_RED +  "Health", player.getHealth(), player.getMaxHealth());
        output += generateBarGraph(TextAttributes.FOREGROUND_CYAN + "Thirst", player.getThirst(), 100);
        output += generateBarGraph(TextAttributes.FOREGROUND_GREEN + "Hunger", player.getHunger(), 100);
        source.sendText(output);
    }

    private String generateBarGraph(String name, double min, double max) {
        String output = name + ": [";
        for(int i = 0; i < 50; i++) {
            if(((double)i / (double)50) < (min / max)) {
                output += "=";
            } else {
                output += "-";
            }
        }
        output += "] (" + min + "/" + max + ")" + TextAttributes.ALL_ATTRIBUTES_OFF + "\n";
        return output;
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "status\nDisplays detailed information regarding your character";
    }
}
