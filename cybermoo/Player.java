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

    public Scene getScene() {
        return SceneHandler.getInstance().getScenes().get(getLocation());
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
            getScene().getPlayers().remove(this);
        }
        setLocation(location);
        getScene().getPlayers().add(this);
    }

    public void sendLocationData() {
        if (getLocation() == null) {
            move(SceneHandler.defaultStart);
        }
        if (getClient() != null) {
            getClient().sendText(getScene().getSceneDetails());
        }
    }

    /**
     * @return the client
     */
    public ThreadedClient getClient() {
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
