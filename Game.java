public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private int hydration;
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
        hydration = 3;
    }

    private void createRooms()
    {
        Room mg05,anel,josecandido,trevo,br262,general,fatima,
        estradaifmg,ifmg,centro;
      
        mg05 = new Room("na MG-05 rua da casa de Levindo");
        anel = new Room("caiu no anel");
        josecandido = new Room("está na José Cândido");
        trevo = new Room("está no Trevo de Sabará");
        br262 = new Room("entrou na BR 262");
        general = new Room("está indo na direção de General Carneiro");
        fatima = new Room("está indo para direção do bairro Fátima ");
        estradaifmg = new Room("está na estrada que leva ao IFMG");
        ifmg = new Room(" chegou ao IFMG");
        centro = new Room("Meu deus está indo para o centro de Sabará");
    
        mg05.setExit("east", anel);
        mg05.setExit("west", josecandido);
        mg05.setExit("south", trevo);

        anel.setExit("west", mg05);

        josecandido.setExit("east", mg05);
        josecandido.setItem("bottle", 1.0);

        trevo.setExit("north", mg05);
        trevo.setExit("south", br262);

        br262.setExit("south", fatima);
        br262.setExit("north", general);
        br262.setExit("east", trevo);
        br262.setExit("west", estradaifmg);
        
        fatima.setExit("north", br262);
        
        general.setExit("south", br262);
        
        estradaifmg.setExit("west", centro);
        estradaifmg.setExit("east", br262);
        estradaifmg.setExit("north", ifmg);
        
        centro.setExit("east", estradaifmg);

        currentRoom = mg05;  // Jogo começa na MG 05
    }
    
    public void play() 
    {            
        printWelcome();
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
            hydration--;
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("drink")) {
            drink();
        }

        return wantToQuit;
    }

    
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {

            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  
        }
    }

    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    private void look() {
        printLocationInfo();
    }

    private void drink() {
        if (currentRoom.hasItem("bottle")) {
            hydration = 5;
            System.out.println("Você se hidratou ao máximo!");
        } else {
            System.out.println("Nenhuma garrafa encontrada.");
        }
    }
}