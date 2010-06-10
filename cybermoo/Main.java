package cybermoo;
/**
 * Instantiates the server object, and begins listening for clients
 * @author Shane
 */

public class Main {

    public static void main(String[] args) {
        Server.getInstance(8080).startListening();
    }
}
