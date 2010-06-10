package cybermoo;

/**
 * Acts as a template for all scenes, with pointers to any scenes it may be connected to,
 * also contains linkedlists of all players and items currently found within the scene
 * @author Shane
 */

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
    private transient LinkedList<Item> items;
    private transient LinkedList<Player> players;

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
        String totalDetails = "\n" + TextAttributes.BOLD + TextAttributes.UNDERSCORE + TextAttributes.FOREGROUND_RED + getName() + TextAttributes.ALL_ATTRIBUTES_OFF + "\n" + TextAttributes.FOREGROUND_CYAN + getDescription() + TextAttributes.ALL_ATTRIBUTES_OFF + "\n";

        String itemDetails = getDetail(getItems(), "lying on the ground here.");
        String playerDetails = getDetail(getPlayers(), "standing here.");

        String directionDetails = "\n";
        if (!getUp().equals("")) {
            directionDetails += "Up - " + TextAttributes.BOLD + getUp() + TextAttributes.ALL_ATTRIBUTES_OFF + "   ";
        }
        if (!getDown().equals("")) {
            directionDetails += "Down - " + TextAttributes.BOLD + getDown() + TextAttributes.ALL_ATTRIBUTES_OFF + "   ";
        }
        if (!getNorth().equals("")) {
            directionDetails += "North - " + TextAttributes.BOLD + getNorth() + TextAttributes.ALL_ATTRIBUTES_OFF + "   ";
        }
        if (!getEast().equals("")) {
            directionDetails += "East - " + TextAttributes.BOLD + getEast() + TextAttributes.ALL_ATTRIBUTES_OFF + "   ";
        }
        if (!getSouth().equals("")) {
            directionDetails += "South - " + TextAttributes.BOLD + getSouth() + TextAttributes.ALL_ATTRIBUTES_OFF + "   ";
        }
        if (!getWest().equals("")) {
            directionDetails += "West - " + TextAttributes.BOLD + getWest() + TextAttributes.ALL_ATTRIBUTES_OFF + "   ";
        }

        totalDetails += itemDetails + playerDetails + directionDetails;
        return totalDetails;
    }

    public String getDetail(LinkedList detailList, String ending) {
        String detailString = "";
        if (detailList.size() > 1) {
            for (int i = 0; i < detailList.size() - 1; i++) {
                detailString += TextAttributes.BOLD + detailList.get(i).toString() + TextAttributes.ALL_ATTRIBUTES_OFF + ", ";
            }
            detailString += "and " + TextAttributes.BOLD + detailList.get(detailList.size() - 1).toString() + TextAttributes.ALL_ATTRIBUTES_OFF + " are " + ending;
        } else if (detailList.size() == 1) {
            detailString += TextAttributes.BOLD + detailList.get(detailList.size() - 1).toString() + TextAttributes.ALL_ATTRIBUTES_OFF + " is " + ending;
        }
        return detailString;
    }

    public String getInvertedDirection(String direction) {
        String dir = direction.toLowerCase();
        if (dir.equals("north")) {
            dir = "south";
        } else if (dir.equals("east")) {
            dir = "weast";
        } else if (dir.equals("south")) {
            dir = "north";
        } else if (dir.equals("west")) {
            dir = "east";
        } else if (dir.equals("up")) {
            dir = "down";
        } else if (dir.equals("down")) {
            dir = "up";
        }
        return dir;
    }

    public String getDirection(String direction) {
        String dir = direction.toLowerCase();
        if (dir.equals("north")) {
            return getNorth();
        } else if (dir.equals("east")) {
            return getEast();
        } else if (dir.equals("south")) {
            return getSouth();
        } else if (dir.equals("west")) {
            return getWest();
        } else if (dir.equals("up")) {
            return getUp();
        } else if (dir.equals("down")) {
            return getDown();
        } else {
            return null;
        }
    }

    public void setDirection(String direction, String name) {
        String dir = direction.toLowerCase();
        if (dir.equals("north")) {
            setNorth(name);
        } else if (dir.equals("east")) {
            setEast(name);
        } else if (dir.equals("south")) {
            setSouth(name);
        } else if (dir.equals("west")) {
            setWest(name);
        } else if (dir.equals("up")) {
            setUp(name);
        } else if (dir.equals("down")) {
            setDown(name);
        }
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
