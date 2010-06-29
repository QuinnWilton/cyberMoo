package cybermoo.ChatCommands;

/**
 * Links the current scene to the specified scene in a specific direction
 * @author Shane
 */

import cybermoo.Handlers.DataHandler;
import cybermoo.Handlers.SceneHandler;
import cybermoo.ThreadedClient;
import java.util.regex.Pattern;

@Name("linkscene")
public class CommandLinkScene implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        String temp = "";
        for (int i = 0; i < arguments.length; i++) {
            temp += " " + arguments[i];
        }
        String message = temp.trim();
        Pattern p = Pattern.compile("\" \"");
        String[] arrArray = p.split(message);
        if (arrArray != null && arrArray.length == 2) {
            String name = arrArray[0].replace("\"", "");
            String direction = arrArray[1].replace("\"", "");
            source.getPlayer().getScene().setDirection(direction, name);
            SceneHandler.getInstance().getScenes().get(name).setDirection(SceneHandler.getInstance().getScenes().get(name).getInvertedDirection(direction), source.getPlayer().getScene().getName());
            DataHandler.getInstance().saveObject("data/scenes/" + source.getPlayer().getScene().getName().replaceAll("[^a-zA-Z0-9]", ""), source.getPlayer().getScene(), false);
            DataHandler.getInstance().saveObject("data/scenes/" + name.replaceAll("[^a-zA-Z0-9]", ""), SceneHandler.getInstance().getScenes().get(name), false);
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "CreateScene <Name> <Description> <Direction>\nCreates a scene with the specified parameters";
    }
}
