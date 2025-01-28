public class Item {
    private String description;
    private double weight;

    /**
     * Create the game and initialise its internal map.
     */
    public Item(String description, double weight) 
    {
        this.description = description;
        this.weight = weight;
    }

    public String getDescription() {
        return this.description;
    }
}
