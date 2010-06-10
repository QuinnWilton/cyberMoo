package cybermoo;

/**
 * Acts as an interface between the client (a generic telnet client),
 * and all of the server sided logic
 * @author Shane
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    private static Server instance = null;
    private Boolean listening;
    private int port;
    private ServerSocket socket;
    private ArrayList<ThreadedClient> clients = new ArrayList<ThreadedClient>();

    protected Server(int port) {
        setPort(port);
        setListening(true);
    }

    public void startListening() {
        try {
            setSocket(new ServerSocket(getPort()));
            while (getListening()) {
                ThreadedClient client = new ThreadedClient(getSocket().accept());
                getClients().add(client);
                client.start();
            }
            getSocket().close();
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + getPort());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void sendToClient(ThreadedClient client, String text) {
        client.sendText(text);
    }

    public void sendToAllClients(String text) {
        for(int i = 0; i < getClients().size(); i++) {
            getClients().get(i).sendText(text);
        }
    }

    /**
     * @Create a new server with a default port of 8080
     */
    public static Server getInstance() {
        return getInstance(8080);
    }

    /**
     * @param port the port with which the server is bound to
     */
    public static Server getInstance(int port) {
        if (instance == null) {
            setInstance(new Server(port));
        }
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    private static void setInstance(Server ins) {
        instance = ins;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the listening
     */
    public Boolean getListening() {
        return listening;
    }

    /**
     * @param listening the listening to set
     */
    public void setListening(Boolean listening) {
        this.listening = listening;
    }

    /**
     * @return the socket
     */
    public ServerSocket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    /**
     * @return the clients
     */
    public ArrayList<ThreadedClient> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(ArrayList<ThreadedClient> clients) {
        this.clients = clients;
    }
}
