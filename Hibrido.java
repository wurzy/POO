import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Hibrido extends Veiculo implements Serializable {

    public Hibrido() {
        super();
    }

    public Hibrido(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getProp(),x.getPosicao(),x.getTipo(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(x.getPriceKm());
        this.setVelocidade(x.getVelocidade());
        this.setConsumoKm(x.getConsumoKm());
    }

    public String toString(){
        return ("Tipo de Combustível: " + this.getTipo()
                + super.toString());
    }

    public Hibrido clone(){return new Hibrido(this);}
}
