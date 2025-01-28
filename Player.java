public class Player {
    private Room currentRoom;
    private int hydration;
    private double maxweight;

    /**
     * Create the game and initialise its internal map.
     */
    public Player(Room currentRoom, int hydration, double maxweight) 
    {
        this.currentRoom = currentRoom;
        this.hydration = hydration;
        this.maxweight = maxweight;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public int getHydration() {
        return this.hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public void sweat() {
        this.hydration--;
    }
}

