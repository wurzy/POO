import java.util.List;
import java.util.Set;

public class Gasolina extends Veiculo{

    public Gasolina() {
        super();
        this.setPriceKm(0.0729); // 1.55 € / L -> 7.29 € / 100KM
        this.setConsumoKm(0.047); // 4.7L / 100 km
        this.setVelocidade(90); // kinda pointless
    }

    public Gasolina(String id, String marca, Ponto posicao, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax){
        super(id,marca,posicao,historico, classificoes,depositoAtual,depositoMax);
        this.setPriceKm(0.0729);
        this.setConsumoKm(0.047);
        this.setVelocidade(90);
    }

    public Gasolina(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getPosicao(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(0.0729);
        this.setConsumoKm(0.047);
        this.setVelocidade(90);

    }

    public String toString(){
        return ("Tipo de Combustível: Gasolina"
                + super.toString());
    }

    public Gasolina clone(){return new Gasolina(this);}
}
