package cybermoo;

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
    private String nickname;

    public ThreadedClient(Socket socket) {
        super("ServerThread");
        setServer(Server.getInstance());
        setSocket(socket);
        setNickname(getSocket().getInetAddress().getHostName());
        try {
            setOutputStream(new PrintWriter(socket.getOutputStream(), true));
            setInputStream(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        try {
            getServer().sendAll(getNickname() + " has connected!");
            String inputLine;
            while ((inputLine = getInputStream().readLine()) != null) {
                getServer().getCommandHandler().parse(inputLine, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            getServer().sendAll(getNickname() + " has disconnected!");
            getOutputStream().close();
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
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
