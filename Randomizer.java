import java.io.Serializable;
import java.util.*;

public class Randomizer implements Serializable {

    private String[] tempo = {"Sol","Vento","Chuva"};
    private String[] condicoes = {"Completamente danificado", "Relativamente danificado", "Relativamente bem cuidado", "Bem cuidado"};
    private String clima;
    private String veiculo;
    public Randomizer(){
        Random generator = new Random();
        this.clima = this.tempo[generator.nextInt(3)];
        this.veiculo = this.condicoes[generator.nextInt(4)];
    }

    public String getClima(){
        return this.clima;
    }

    public String getVeiculo(){
        return this.veiculo;
    }

    public void setVeiculo(String veiculo){
        this.veiculo = veiculo;
    }

    public void setClima(String clima){
        this.clima = clima;
    }

    public Randomizer(int x) {
        this.clima = "Sol";
        this.veiculo = "Bem cuidado";
    }

    public double novaVelocidade(double velAtual){
        double percentage = 0;
        double traffic;
        Random generator = new Random();
        do{
            traffic=generator.nextDouble();
        }while(traffic>0.1);
        traffic = traffic*velAtual;

        if(this.clima.equals("Chuva")) {
            percentage = velAtual*0.25;
            traffic*=2;
        }
        else if (this.clima.equals("Vento")) {
            percentage = velAtual*0.1;
        }
        else {
            percentage = 0;
        }
        double ret = (double) Math.round((velAtual - percentage - traffic)*100)/100;
        return ret>1?ret:1.0;
    }

    public static double novoConsumo(double tempo, double tempoAtual, double deposito) {
        double dif = (tempoAtual-tempo)*0.2;
        return (double) Math.round(dif*deposito*100)/100;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Randomizer that = (Randomizer) o;
        return this.clima.equals(that.clima) &&
                this.veiculo.equals(that.veiculo);
    }

    public Randomizer clone(){
        Randomizer novo = new Randomizer();
        novo.setClima(this.getClima());
        novo.setVeiculo(this.getVeiculo());
        return novo;
    }

    public String toString(){
        return this.getVeiculo() + " " + this.getClima();
    }
}
