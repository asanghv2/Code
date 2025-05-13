public class Player {
    private String name;
    private char disc;


    
    /**
     * Intializes Player
     * @param name
     * @param disc
     */
    public Player(String name, char disc) {
        this.name = name;
        this.disc = disc;
    }

    
    /**
     * Returns the Player Name
     * @return name
     */
    public String getName() {
        return name;
    }

    
    /**
     * Returns the player disc
     * @return disc
     */
    public char getDisc() {
        return disc;
    }
}
