import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Eletrico extends Veiculo implements Serializable {

    public Eletrico() {
        super();
    }

    public Eletrico(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getProp(),x.getPosicao(),x.getTipo(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(x.getPriceKm());
        this.setVelocidade(x.getVelocidade());
        this.setConsumoKm(x.getConsumoKm());
    }

    public String toString(){
        return ("Tipo de Combustível: " + this.getTipo()
                + super.toString());
    }

    public Eletrico clone(){return new Eletrico(this);}
}
