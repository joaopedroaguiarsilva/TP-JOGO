import java.util.*;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    // public void setExits(Room north, Room east, Room south, Room west) 
    // {
    //     if(north != null) {
    //         northExit = north;
    //     }
    //     if(east != null) {
    //         eastExit = east;
    //     }
    //     if(south != null) {
    //         southExit = south;
    //     }
    //     if(west != null) {
    //         westExit = west;
    //     }
    // }

    public void setExit(String direction, Room exit) {
        exits.put(direction, exit);
    }

    public String getDescription()
    {
        return description;
    }

    public Room getExit(String exit) {
        return exits.get(exit);
    }

    public String getExitString()
    {
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            exitString += " " + exit;
        }
        return exitString;
    }

    public String getLongDescription()
    {
	    return "You are " + description + ".\n" + getExitString();
    }
}
