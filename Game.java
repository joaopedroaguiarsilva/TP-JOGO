import java.util.*;

public class Game 
{
    private Parser parser;
    private Stack<Room> pilha = new Stack<>();
    private Player player;
        
    public Game() 
    {
        parser = new Parser();
        player = new Player(null, 5, 10);
        createRooms();
        createItens();
    }

    private void createItens()
    {
        Room.setItem("garrafinha", 5);

        /*
         
          eu tenho dois itens uma garrafinha e um limpador de janela.
        a garrafinha já vem junto com o jogador ent vc n pode take ela.
        Ja o limpador de janela estará na MG05, porém para pegar o limpador de janela
        levindo deve dropar a garrafinha porque ter os dois intens vai exceder o peso
        máximo, mas para concluir o jogo ele precisa do limpador 
        de janela pois no room estradaifmg começa a chover e se ele não usar o 
        limpador de janela automaticamente ele chegará atrasado.
          
         
         */
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

        this.player.setCurrentRoom(mg05);  // Jogo começa na MG 05
    }
    
    public void play() 
    {            
        printWelcome();
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar! Até.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem vindo ao Augusto Levindo World");
        System.out.println("Nesse jogo você deve mover nosso grande amigo Levindo \n pelo mapa em uma díficil missão.");
        System.out.println("-------------------------------");
        System.out.println("Levindo é um morador do Bairro Goiânia em BH e um estudante \n de inteligências artificiais no IFMG Sabará. Levindo sai de casa todos os dias às 6:00 da manhã para chegar \n a sua aula às 7:00. Mas Levindo precisa tomar muito cuidado para qual caminho irá seguir para chegar a sua faculdade, pois se ele pegar o caminho errado o tempo para ele chegar ao IF aumentará e assim ele pode acabar \n chegando atrasado para sua aula. O objetivo do jogo é dar as direções que Levindo deve seguir e prestar atenção ao tempo para que Levindo chegue \n a tempo para sua aula, caso ele chegue atrasado o chefão professor irá penaliza-lo com uma suspensão causando a sua derrota no jogo. \n Outro ponto importante é que Levindo carrega uma garrafinha da água e a cada movimento que ele faz perde um pouco do seu nível de água, você deve prestar atenção para que Levindo não se esqueça de beber água e acaba se \n desidratando assim perdendo o jogo. As direções que você pode utilizar em nosso mapa são (south, west, east, north).");
        System.out.println("-------------------------------");
        System.out.println("O relógio começa exatamente as 6:00 e a cada movimento realizado \n alguns minutos são adicionados ao relógio. Se precisar saber as horas digite 'relógio'.");
        System.out.println("-------------------------------");
        System.out.println("Seu nível de água começa em 100%, lembre-se de usar a sua \n garrafinha, pois a cada movimento seu nível de água diminui e se seu nível de água chegar \n ao 0% você está automaticamente derrotado. Digite 'água' para saber o seu nível de água.");
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
        } else if (commandWord.equals("drink")) {
            drink();
        } else if (commandWord.equals("back")) {
            back(command);
        } else if (commandWord.equals("take")) {
            take();
        } else if (commandWord.equals("drop")) {
            drop();
        }

        System.out.println("Hidratação: " + this.player.getHydration());
        if (this.player.getHydration() == 0) {
            System.out.println("Você ficou desidratado! Não pode mais continuar.");
            wantToQuit = true;
        }

        return wantToQuit;
    }

    
    private void printHelp() 
    {
        System.out.println("Você está perdido em nosso jogo.");
        System.out.println("Dentro do mapa.");
        System.out.println();
        System.out.println("Os comandos que você pode utilizar são::");
        System.out.println(parser.showCommands());
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {

            System.out.println("Vai há onde?");
            return;
        }

        String direction = command.getSecondWord();

        this.pilha.push(this.player.getCurrentRoom());

        Room nextRoom = null;
        nextRoom = this.player.getCurrentRoom().getExit(direction);

        goNextRoom(nextRoom);
    }

    private void goNextRoom(Room nextRoom) {
        if (nextRoom == null) {
            System.out.println("Não há um caminho!");
        }
        else {
            this.player.setCurrentRoom(nextRoom);
            this.player.sweat();
            
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
        System.out.println(this.player.getCurrentRoom().getLongDescription());
        System.out.println();
    }

    private void look() {
        printLocationInfo();
    }

    private void drink() {
        if (this.player.getCurrentRoom().hasItem("garrafinha")) {
            this.player.setHydration(5);
            System.out.println("Você se hidratou ao máximo!");
        } else {
            System.out.println("Nenhuma garrafa encontrada.");
        }
    }

    private void back(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Erro no comando.");
            return;
        }

        if (this.pilha.empty()) {
            System.out.println("Você está no início.");
            return;
        }

        Room nextRoom = this.pilha.pop();

        goNextRoom(nextRoom);

        private void take(){
            return;
        }
    }
}