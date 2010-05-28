package cybermoo;

import cybermoo.Handlers.DataHandler;

public class Main {

    public static void main(String[] args) {
        Server.getInstance(8080).startListening();
    }
}
