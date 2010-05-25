package cybermoo.ChatCommands;

import cybermoo.ThreadedClient;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CommandRegister implements Command {

    private final String userList = "data/users.txt";
    private InputStreamReader fileInput;
    private OutputStreamWriter fileOutput;

    public CommandRegister() {
        try {
            fileInput = new InputStreamReader(new FileInputStream(userList), "UTF-8");
            fileOutput = new OutputStreamWriter(new FileOutputStream(userList), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void call(String[] arguments, ThreadedClient source) {
        if(fileInput.)
    }
}
