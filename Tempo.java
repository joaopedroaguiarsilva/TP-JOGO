public class Tempo {
    private int tempoInicial;
    private int tempoAtual;
    private int tempoLimite;

    public Tempo(int tempoInicial, int tempoAtual, int tempoLimite) 
    {
        this.tempoInicial = tempoInicial;
        this.tempoAtual = tempoAtual;
        this.tempoLimite = tempoLimite;
    }

    public int getHoraAtual()
    {
        int horas, minutos;
        horas = this.tempoAtual / 60;
        minutos = this.tempoAtual - this.tempoInicial;
        System.out.println(" ");
        if(minutos < 10)
        {
            System.out.println("São "+ horas+ " horas e 0"+minutos+ " minutos");
        }
        else if(minutos >= 60)
        {
            minutos = 0;
            System.out.println("São "+ horas+ " horas e 0"+minutos+ " minutos");
        }
        else{
            System.out.println("São "+ horas+ " horas e "+minutos+ " minutos");
        }
        
        System.out.println(" ");
        return tempoAtual;
    }

    public void incleaseTempo()
    {
        tempoAtual += 6; 
    }

    public int getTempoLimite()
    {
        return tempoLimite;
    }
}