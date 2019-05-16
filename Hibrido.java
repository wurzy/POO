import java.util.List;
import java.util.Set;

public class Hibrido extends Veiculo {

    public Hibrido() {
        super();
        //this.setPriceKm(0.0438); // 1,37 € / L --> 4.38 € / 100 km
        //this.setConsumoKm(0.032); //  3.2L / 100 km
        //this.setVelocidade(80); // kinda pointless
    }

    public Hibrido(String id, String marca, Ponto posicao, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax){
        super(id,marca,posicao,historico, classificoes,depositoAtual,depositoMax);
        //this.setPriceKm(0.0438);
        //this.setConsumoKm(0.32);
        //this.setVelocidade(80);
    }

    public Hibrido(Veiculo x) {
        super(x.getID(),x.getMarca(),x.getPosicao(),x.getHistorico(),x.getClassificacoes(),x.getDepositoAtual(),x.getDepositoMax());
        //this.setPriceKm(0.0438);
        //this.setConsumoKm(0.032);
        //this.setVelocidade(80);

    }

    public String toString(){
        return ("Tipo de Combustível: Híbrido"
                + super.toString());
    }

    public Hibrido clone(){return new Hibrido(this);}
}
