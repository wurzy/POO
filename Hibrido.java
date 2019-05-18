import java.util.List;
import java.util.Set;

public class Hibrido extends Veiculo {

    public Hibrido() {
        super();
        //this.setPriceKm(0.0438); // 1,37 € / L --> 4.38 € / 100 km
        //this.setConsumoKm(0.032); //  3.2L / 100 km
        //this.setVelocidade(80); // kinda pointless
    }

    public Hibrido(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getProp(),x.getPosicao(),x.getTipo(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        this.setPriceKm(x.getPriceKm());
        this.setVelocidade(x.getVelocidade());
        this.setConsumoKm(x.getConsumoKm());
        //this.setPriceKm(0.0438);
        //this.setConsumoKm(0.032);
        //this.setVelocidade(80);

    }

    public String toString(){
        return ("Tipo de Combustível: " + this.getTipo()
                + super.toString());
    }

    public Hibrido clone(){return new Hibrido(this);}
}
