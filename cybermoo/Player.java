package cybermoo;

/**
 * Stores all of the fields required by a player character,
 * along with a pointer to the client which instantiated the player
 * @author Shane
 */

import cybermoo.Handlers.SceneHandler;

public class Player extends Creature{

    private int Thirst;
    private int Hunger;
    private int experience;
    private int totalExperience;
    private String hash;
    private String location;
    private transient ThreadedClient client;

    public Player() {
        setDescription("");
        setHealth(30);
        setMaxHealth(30);
        setThirst(100);
        setHunger(100);
        setExperience(0);
        setTotalExperience(0);
        setLocation(SceneHandler.defaultStart);
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
        if (getLocation() != null && getScene() != null) {
            getScene().getPlayers().remove(this);
        }
        if (SceneHandler.getInstance().getScenes().get(location) != null) {
            setLocation(location);
            getScene().getPlayers().add(this);
        }
    }

    public void sendLocationData() {
        if (getLocation() == null || SceneHandler.getInstance().getScenes().get(location) == null) {
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

    /**
     * @return the Thirst
     */
    public int getThirst() {
        return Thirst;
    }

    /**
     * @param Thirst the Thirst to set
     */
    public void setThirst(int Thirst) {
        this.Thirst = Thirst;
    }

    /**
     * @return the Hunger
     */
    public int getHunger() {
        return Hunger;
    }

    /**
     * @param Hunger the Hunger to set
     */
    public void setHunger(int Hunger) {
        this.Hunger = Hunger;
    }

    /**
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * @return the totalExperience
     */
    public int getTotalExperience() {
        return totalExperience;
    }

    /**
     * @param totalExperience the totalExperience to set
     */
    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }
}
