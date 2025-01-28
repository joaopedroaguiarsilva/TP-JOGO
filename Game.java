public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room mg05,anel,josecandido,trevo,br262,general,fatima,
        estradaifmg,ifmg,centro;
      
        mg05 = new Room("esá na MG-05 rua da casa de Levindo");
        anel = new Room("caiu no anel");
        josecandido = new Room("está na José Cândido");
        trevo = new Room("está no Trevo de Sabará");
        br262 = new Room("está na BR 262");
        general = new Room("está indo na direção de General Carneiro");
        fatima = new Room("está indo em direção ao bairro Fátima ");
        estradaifmg = new Room("está na estrada que leva ao IFMG");
        ifmg = new Room(" chegou ao IFMG");
        centro = new Room(", Meu deus está indo para o centro de Sabará");
    
        mg05.setExit("east", anel);
        mg05.setExit("west", josecandido);
        mg05.setExit("south", trevo);

        anel.setExit("west", mg05);

        josecandido.setExit("east", mg05);

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
        System.out.println("Bem vindo ao Augusto Levindo World");
        System.out.println("Nesse jogo você deve mover nosso grande amigo Levindo pelo mapa em uma díficil missão.");
        System.out.println("-------------------------------");
        System.out.println("Levindo é um morador do Bairro Goiânia em BH e um estudante de inteligências artificiais no IFMG Sabará. Levindo sai de casa todos os dias às 6:00 da manhã para chegar a sua aula às 7:00. Mas Levindo precisa tomar muito cuidado para qual caminho irá seguir para chegar a sua faculdade, pois se ele pegar o caminho errado o tempo para ele chegar ao IF aumentará e assim ele pode acabar chegando atrasado para sua aula. O objetivo do jogo é dar as direções que Levindo deve seguir e prestar atenção ao tempo para que Levindo chegue a tempo para sua aula, caso ele chegue atrasado o chefão professor irá penaliza-lo com uma suspensão causando a sua derrota no jogo. Outro ponto importante é que Levindo carrega uma garrafinha da água e a cada movimento que ele faz perde um pouco do seu nível de água, você deve prestar atenção para que Levindo não se esqueça de beber água e acaba se desidratando assim perdendo o jogo. As direções que você pode utilizar em nosso mapa são (south, west, east, north).");
        System.out.println("-------------------------------");
        System.out.println("O relógio começa exatamente as 6:00 e a cada movimento realizado alguns minutos são adicionados ao relógio. Se precisar saber as horas digite 'relógio'.");
        System.out.println("-------------------------------");
        System.out.println("Seu nível de água começa em 100%, lembre-se de usar a sua garrafinha, pois a cada movimento seu nível de água diminui e se seu nível de água chegar ao 0% você está automaticamente derrotado. Digite 'água' para saber o seu nível de água.");
        System.out.println("-------------------------------");
        System.out.println("Caso precise de alguma ajuda digite 'help'.");
        System.out.println();
        printLocationInfo();
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Eu não sei o que você quer dizer");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")) {
            look();
        }

        return wantToQuit;
    }

    
    private void printHelp() 
    {
        System.out.println("Você está perdido em nosso jogo.");
        System.out.println("Dentro do mapa.");
        System.out.println();
        System.out.println("Os comandos que você pode utilizar são:");
        System.out.println(parser.showCommands());
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {

            System.out.println("Vai há onde?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há um caminho!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit o que?");
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
}