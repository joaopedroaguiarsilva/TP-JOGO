import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private int hydration;
    private double maxweight;
    private double currentweight;
    private ArrayList<Item> inventario = new ArrayList<Item>();

    /**
     * Create the game and initialise its internal map.
     */
    public Player(Room currentRoom, int hydration, double maxweight) 
    {
        this.currentRoom = currentRoom;
        this.hydration = hydration;
        this.maxweight = maxweight;
        this.currentweight = 0;
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

    public void takeToInventory(Item item) {
        inventario.add(item);
        if((currentweight + item.getItemWeight()) > maxweight){
            System.out.println("Você não pode pegar esse item, porque vai exceder seu limite de peso.");
        } else {
            currentweight += item.getItemWeight();
        }
    }

    public boolean hasItem(String item) {
        for (Item it : inventario) {
            if (it.getDescription().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void dropItem(Item item) {
        int idx = 0;
        for (Item it : inventario) {
            if (it.getDescription().equals(item.getDescription())) {
                inventario.remove(idx);
                currentweight -= item.getItemWeight();
            }
            idx++;
        }
    }

    public String getStringInventario() {
        String itemsString = "Inventário: ";
        for (Item item : inventario) {
            itemsString += item.getDescription() + " ";
        }

        return itemsString;
    }

    public double getItemsTotalWeight() {
        return currentweight;
    }

    public void setMaxWeight(double peso) {
        this.maxweight += peso;
    }

}