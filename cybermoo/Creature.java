package cybermoo;

/**
 * Acts as a base class for all living entities.
 * Encapsulates common logic between player characters and non-player character
 * @author Shane
 */

public class Creature {
    private String name;
    private String description;
    private int health;
    private int maxHealth;
    private Creature target;

    @Override
    public String toString() {
        return getName();
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @param maxHealth the maxHealth to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * @return the target
     */
    public Creature getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Creature target) {
        this.target = target;
    }
}
