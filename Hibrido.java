import java.util.List;
import java.util.Set;

public class Hibrido extends Veiculo {

    public Hibrido() {
        super();
        this.setPriceKm(0.0438); // 1,37 € / L --> 4.38 € / 100 km
        this.setConsumoKm(0.032); //  3.2L / 100 km
        this.setVelocidade(80); // kinda pointless
    }

    public Hibrido(String id, String marca, Ponto posicao, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax, double priceKm, double consumoKm, int velocidade){
        super(id,marca,posicao,historico, classificoes,depositoAtual,depositoMax);
        this.setPriceKm(priceKm);
        this.setConsumoKm(consumoKm);
        this.setVelocidade(velocidade);
    }

    public Hibrido(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getPosicao(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(0.0438);
        this.setConsumoKm(0.032);
        this.setVelocidade(80);

    }

    public String toString(){
        return ("Tipo de Combustível: Diesel" +
                "\nMatrícula do Veículo: " + this.getID() +
                "\nMarca: " + this.getMarca() +
                "\nPosição atual: " + this.getPosicao() +
                "Velocidade média: " + this.getVelocidade() +
                "\nPreço/km: " + this.getPriceKm() + " €/km" +
                "\nConsumo/km: " + this.getConsumoKm() + " L/km" +
                "\nDepósito: " + this.getDepositoAtual() + "/" + this.getDepositoMax() + " L" +
                "\n\nHistórico de Alugueres:\n\n " + this.getHistorico() +
                "\n\nHistórico de Classificações:\n\n " + this.getClassificacoes() + "\n"
        );
    }

    public Hibrido clone(){return new Hibrido(this);}
}
