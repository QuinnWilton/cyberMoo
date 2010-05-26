package cybermoo;

public class Player {

    private String name;
    private String hash;

    public ThreadedClient getClient() {
        for(int i = 0; i < Server.getInstance().getClients().size(); i++) {
            if(Server.getInstance().getClients().get(i).getPlayer() == this) {
                return Server.getInstance().getClients().get(i);
            }
        }
        return null;
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
}
