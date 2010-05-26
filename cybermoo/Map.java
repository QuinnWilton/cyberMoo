package cybermoo;

import java.util.LinkedList;

public class Map {
    private String name;
    private String description;
    private Map up;
    private Map down;
    private Map north;
    private Map east;
    private Map south;
    private Map west;
    private LinkedList<Item> items;
    private LinkedList<Player> players;

    public Map() {
        items = new LinkedList<Item>();
        players = new LinkedList<Player>();
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
     * @return the up
     */
    public Map getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(Map up) {
        this.up = up;
    }

    /**
     * @return the down
     */
    public Map getDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(Map down) {
        this.down = down;
    }

    /**
     * @return the north
     */
    public Map getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(Map north) {
        this.north = north;
    }

    /**
     * @return the east
     */
    public Map getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(Map east) {
        this.east = east;
    }

    /**
     * @return the south
     */
    public Map getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(Map south) {
        this.south = south;
    }

    /**
     * @return the west
     */
    public Map getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(Map west) {
        this.west = west;
    }

    /**
     * @return the items
     */
    public LinkedList<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    /**
     * @return the players
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }
}