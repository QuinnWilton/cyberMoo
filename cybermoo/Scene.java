package cybermoo;

import java.util.Date;
import java.util.LinkedList;

public class Scene {

    private String name;
    private String description;
    private String symbol;
    private String up;
    private String down;
    private String north;
    private String east;
    private String south;
    private String west;
    private LinkedList<Item> items;
    private LinkedList<Player> players;

    public Scene() {
        setName("");
        setDescription("");
        setSymbol("");
        setUp("");
        setDown("");
        setNorth("");
        setEast("");
        setSouth("");
        setWest("");
        items = new LinkedList<Item>();
        players = new LinkedList<Player>();
    }

    public String getSceneDetails() {
        String totalDetails = getName() + "\n"
                + getDescription() + "\n";

        String itemDetails = "";
        if (getItems().size() > 1) {
            for (int i = 0; i < getItems().size() - 1; i++) {
                itemDetails += getItems().get(i) + ", ";
            }
            itemDetails += "and a " + getItems().get(getItems().size() - 1) + " are lying on the ground here.";
        } else if(getItems().size() == 1) {
            itemDetails += "A " + getItems().get(getItems().size() - 1) + " is lying on the ground here.";
        }

        String playerDetails = "";
        if (getPlayers().size() > 1) {
            for (int i = 0; i < getPlayers().size() - 1; i++) {
                playerDetails += getPlayers().get(i).getName() + ", ";
            }
            playerDetails += "and " + getPlayers().get(getPlayers().size() - 1).getName() + " are standing here.";
        } else if(getPlayers().size() == 1) {
            playerDetails += getPlayers().get(getPlayers().size() - 1).getName() + " is standing here.";
        }

        String directionDetails = "\n";
        if(!getUp().equals("")) {
            directionDetails += "Up - " + getUp() + "   ";
        }
        if(!getDown().equals("")) {
            directionDetails += "Down - " + getDown() + "   ";
        }
        if(!getNorth().equals("")) {
            directionDetails += "North - " + getNorth() + "   ";
        }
        if(!getEast().equals("")) {
            directionDetails += "East - " + getEast() + "   ";
        }
        if(!getSouth().equals("")) {
            directionDetails += "South - " + getSouth() + "   ";
        }
        if(!getWest().equals("")) {
            directionDetails += "West - " + getWest() + "   ";
        }

        totalDetails += itemDetails + playerDetails + directionDetails;
        return totalDetails;
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
    public String getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(String up) {
        this.up = up;
    }

    /**
     * @return the down
     */
    public String getDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(String down) {
        this.down = down;
    }

    /**
     * @return the north
     */
    public String getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(String north) {
        this.north = north;
    }

    /**
     * @return the east
     */
    public String getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(String east) {
        this.east = east;
    }

    /**
     * @return the south
     */
    public String getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(String south) {
        this.south = south;
    }

    /**
     * @return the west
     */
    public String getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(String west) {
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

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
