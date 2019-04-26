import java.util.List;
import java.util.Set;

public class Eletrico extends Veiculo {

    public Eletrico() {
        super();
        this.setPriceKm(0.018); // 1 kWh = 12 cents, 15 kWh / 100 km -> 1.8 € / 100 km
        this.setConsumoKm(0.04); //  100% bateria -> 400 km
        this.setVelocidade(70); // kinda pointless
    }

    public Eletrico(String id, String marca, Ponto posicao, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax){
        super(id,marca,posicao,historico, classificoes,depositoAtual,depositoMax);
        this.setPriceKm(0.018);
        this.setConsumoKm(0.04);
        this.setVelocidade(70);
    }

    public Eletrico(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getPosicao(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(0.018);
        this.setConsumoKm(0.04);
        this.setVelocidade(70);

    }

    public String toString(){
        return ("Tipo de Combustível: Elétrico" +
                "\nMatrícula do Veículo: " + this.getID() +
                "\nMarca: " + this.getMarca() +
                "\nPosição atual: " + this.getPosicao() +
                "Velocidade média: " + this.getVelocidade() +
                "\nPreço/km: " + this.getPriceKm() + " €/km" +
                "\nConsumo/km: " + this.getConsumoKm() + " kW/km" +
                "\nDepósito: " + this.getDepositoAtual() + "/" + this.getDepositoMax() + " kW" +
                "\n\nHistórico de Alugueres:\n\n " + this.getHistorico() +
                "\n\nHistórico de Classificações:\n\n " + this.getClassificacoes() + "\n"
                );
    }

    public Eletrico clone(){return new Eletrico(this);}
}
