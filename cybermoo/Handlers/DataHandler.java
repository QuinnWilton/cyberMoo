package cybermoo.Handlers;

/**
 * Acts as an interface between the logic, and the data,
 * loading and saving all objects to an external database
 * @author Shane
 */

import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DataHandler {

    private static DataHandler instance;
    private Gson gson;

    public static DataHandler getInstance() {
        if (instance == null) {
            instance = new DataHandler();
        }
        return instance;
    }

    public DataHandler() {
        gson = new Gson();
    }

    public <T>T loadObject(String path, Class<T> cls) {
        try {
            FileReader fileInput = new FileReader(path);
            T obj = gson.fromJson(fileInput, cls);
            fileInput.close();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveObject(String path, Object obj, boolean append) {
        try {
            PrintWriter fileOutput = new PrintWriter(new FileOutputStream(path, append));
            fileOutput.print(gson.toJson(obj));
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
