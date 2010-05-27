package cybermoo;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SceneHandler {

    private static SceneHandler instance;
    private final String sceneList = "data/Scenes.txt";
    private Scanner fileInput;
    private Gson gson;
    private Map<String, Scene> scenes;
    public static final String defaultStart = "Dark Room";

    public static SceneHandler getInstance() {
        if (instance == null) {
            instance = new SceneHandler();
        }
        return instance;
    }

    public SceneHandler() {
        try {
            fileInput = new Scanner(new FileReader(sceneList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson = new Gson();
        scenes = new HashMap<String, Scene>();
        loadMaps();
    }

    private void loadMaps() {
        while (fileInput.hasNextLine()) {
            Scene scene = gson.fromJson(fileInput.nextLine(), Scene.class);
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
