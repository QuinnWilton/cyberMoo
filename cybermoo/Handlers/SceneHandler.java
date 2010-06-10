package cybermoo.Handlers;

/**
 * Stores all currently loaded scnes, and
 * allows access to them through a hash map
 * @author Shane
 */

import cybermoo.Scene;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SceneHandler {

    private static SceneHandler instance;
    private final File[] sceneList = new File("data/scenes").listFiles();
    private Map<String, Scene> scenes;
    public static final String defaultStart = "Clone Arrangers";

    public static SceneHandler getInstance() {
        if (instance == null) {
            instance = new SceneHandler();
        }
        return instance;
    }

    public SceneHandler() {
        scenes = new HashMap<String, Scene>();
        loadScenes();
    }

    private void loadScenes() {
        for (int i = 0; i < sceneList.length; i++) {
            Scene scene = DataHandler.getInstance().loadObject(sceneList[i].getPath(), Scene.class);
            getScenes().put(scene.getName(), scene);
        }
    }

    /**
     * @return the scenes
     */
    public Map<String, Scene> getScenes() {
        return scenes;
    }

    /**
     * @param scenes the scenes to set
     */
    public void setScenes(Map<String, Scene> scenes) {
        this.scenes = scenes;
    }
}
