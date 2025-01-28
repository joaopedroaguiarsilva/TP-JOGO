public class Player {
    private Room currentRoom;
    private int hydration;

    /**
     * Create the game and initialise its internal map.
     */
    public Player(Room currentRoom, int hydration) 
    {
        this.currentRoom = currentRoom;
        this.hydration = hydration;
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

