package cybermoo;

public class Player {

    private String name;
    private String hash;
    private String location;
    private transient ThreadedClient client;

    public Player() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public void move(String location) {
        if (getLocation() != null) {
            SceneHandler.getInstance().getScenes().get(getLocation()).getPlayers().remove(this);
        }
        setLocation(location);
        SceneHandler.getInstance().getScenes().get(getLocation()).getPlayers().add(this);
    }

    public void sendLocationData() {
        if (getLocation() == null) {
            move(SceneHandler.defaultStart);
        }
        if (getClient() != null) {
            getClient().sendText(SceneHandler.getInstance().getScenes().get(location).getSceneDetails());
        }
    }

    /**
     * @return the client
     */
    public ThreadedClient getClient() {
        if (client == null) {
            for (int i = 0; i < Server.getInstance().getClients().size(); i++) {
                if (Server.getInstance().getClients().get(i).getPlayer() == this) {
                    setClient(Server.getInstance().getClients().get(i));
                }
            }
        }
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(ThreadedClient client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return getName();
    }
}
