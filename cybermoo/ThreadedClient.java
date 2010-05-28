package cybermoo;

import cybermoo.ChatCommands.CommandHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadedClient extends Thread {

    private Server server;
    private Socket socket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    private Player player;

    public ThreadedClient(Socket socket) {
        super("ClientThread - " + socket.getInetAddress().getHostName());
        setServer(Server.getInstance());
        setSocket(socket);
        try {
            setOutputStream(new PrintWriter(socket.getOutputStream(), true));
            setInputStream(new BufferedReader(new InputStreamReader(getSocket().getInputStream(), "UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            sendText("Welcome to cyberMoo! Please LOGIN or REGISTER to continue!");
            String inputLine;
            while ((inputLine = getInputStream().readLine()) != null) {
                CommandHandler.getInstance().parse(inputLine, this);
            }
        } catch (IOException e) {
        } finally {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            //TODO save character
            getSocket().close();
            getServer().getClients().remove(this);
            getPlayer().getScene().getPlayers().remove(getPlayer());
        } catch (IOException e) {
            System.err.println("Something went horribly wrong.");
        }
    }

    public void sendText(String text) {
        getOutputStream().println(text);
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return the outputStream
     */
    public PrintWriter getOutputStream() {
        return outputStream;
    }

    /**
     * @param outputStream the outputStream to set
     */
    public void setOutputStream(PrintWriter outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * @return the inputStream
     */
    public BufferedReader getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream the inputStream to set
     */
    public void setInputStream(BufferedReader inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return the server
     */
    public Server getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(Server server) {
        this.server = server;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
