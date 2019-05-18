import java.util.List;
import java.util.Set;

public class Gasolina extends Veiculo{

    public Gasolina() {
        super();
        //this.setPriceKm(0.0729); // 1.55 € / L -> 7.29 € / 100KM
        //this.setConsumoKm(0.047); // 4.7L / 100 km
        //this.setVelocidade(90); // kinda pointless
    }

    public Gasolina(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getProp(),x.getPosicao(),x.getTipo(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(x.getPriceKm());
        this.setVelocidade(x.getVelocidade());
        this.setConsumoKm(x.getConsumoKm());
        //this.setPriceKm(0.0729);
        //this.setConsumoKm(0.047);
        //this.setVelocidade(90);

    }

    public String toString(){
        return ("Tipo de Combustível: " + this.getTipo()
                + super.toString());
    }

    public Gasolina clone(){return new Gasolina(this);}
}
