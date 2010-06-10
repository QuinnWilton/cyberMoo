package cybermoo.ChatCommands;

/**
 * Allows a player to create a scene, connected to their
 * current location, through specifying the required arguments
 * @author Shane
 */

import cybermoo.Handlers.DataHandler;
import cybermoo.Handlers.SceneHandler;
import cybermoo.Scene;
import cybermoo.ThreadedClient;
import java.util.regex.Pattern;

public class CommandCreateScene implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        String temp = "";
        for (int i = 0; i < arguments.length; i++) {
            temp += " " + arguments[i];
        }
        String message = temp.trim();
        Pattern p = Pattern.compile("\" \"");
        String[] arrArray = p.split(message);
        if (arrArray != null && arrArray.length == 3) {
            String name = arrArray[0].replace("\"", "");
            String description = arrArray[1].replace("\"", "");
            String direction = arrArray[2].replace("\"", "");
            Scene scene = new Scene();
            scene.setName(name);
            scene.setDescription(description);
            SceneHandler.getInstance().getScenes().put(name, scene);
            source.getPlayer().getScene().setDirection(direction, name);
            scene.setDirection(scene.getInvertedDirection(direction), source.getPlayer().getScene().getName());
            DataHandler.getInstance().saveObject("data/scenes/" + source.getPlayer().getScene().getName().replaceAll("[^a-zA-Z0-9]", ""), source.getPlayer().getScene(), false);
            DataHandler.getInstance().saveObject("data/scenes/" + name.replaceAll("[^a-zA-Z0-9]", ""), scene, false);
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "LinkScene <Name> <Direction>\nLinks the current scene to the specified scene in the specified direction";
    }
}
