import java.util.*;
import java.util.stream.Collectors;

public abstract class Veiculo {
    private String ID; //matricula
    private String marca;
    private Ponto posicao;
   // private double reliable; // grau de fiabilidade
    private double velocidade; // Vel media
    private double priceKm; // €/km
    private double consumoKm; // Combust/KM
    private Set<Aluguer> historico; // alugueres
    private List<Integer> classificoes; // ratings
    private double depositoMax; // max do deposito
    private double depositoAtual; //litros que tem o deposito de combustivel

    public Veiculo() {
        this.ID = "N/A";
        this.marca = "N/A";
        this.posicao = new Ponto();
      //  this.velocidade = 0;
       // this.priceKm = 0; //gerimos isto nas extended
       // this.consumoKm = 0;
        this.historico = new TreeSet<>();
        this.classificoes = new ArrayList<>();
        this.depositoMax = 0;
        this.depositoAtual = 0;
    }

    public Veiculo(String id, String marca, Ponto posicao, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax) {
        this.ID = id;
        this.marca = marca;
        this.posicao = posicao.clone();
        //this.velocidade = velocidade;
        //this.priceKm = priceKm;
        //this.consumoKm = consumoKm;
        setHistorico(historico);
        setClassificacoes(classificoes);
        this.depositoMax = depositoMax;
        this.depositoAtual = depositoAtual;
    }

    public Veiculo(Veiculo x) {
        this.ID = x.getID();
        this.marca = x.getMarca();
        this.posicao = x.getPosicao();
       // this.velocidade = x.getVelocidade();
        //this.priceKm = x.getPriceKm();
        //this.consumoKm = x.getConsumoKm();
        this.historico = x.getHistorico();
        this.classificoes = x.getClassificacoes();
        this.depositoMax = x.getDepositoMax();
        this.depositoAtual = x.getDepositoAtual();
    }

    public String getID() { return this.ID; }

    public String getMarca() {return this.marca;}

    public Ponto getPosicao() {return this.posicao.clone();}

    public double getVelocidade() {
        return this.velocidade;
    }

    public double getPriceKm() {
        return this.priceKm;
    }

    public double getConsumoKm() {
        return this.consumoKm;
    }

    public Set<Aluguer> getHistorico() {
        Set<Aluguer> getter = new TreeSet<>();
        Iterator<Aluguer> it = this.historico.iterator();
        while(it.hasNext()) {
            getter.add(it.next().clone());
        }
        return getter;
    }

    public List<Integer> getClassificacoes() {
        return this.classificoes.stream().collect(Collectors.toList());
    }

    public double getDepositoMax() {
        return this.depositoMax;
    }

    public double getDepositoAtual() {
        return this.depositoAtual;
    }

    public void setID(String id) { this.ID = id; }

    public void setMarca(String marca) {this.marca = marca;}

    public void setPosicao(Ponto p) {this.posicao = p.clone();}

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public void setPriceKm(double priceKm) {
        this.priceKm = priceKm;
    }

    public void setConsumoKm(double consumoKm) {
        this.consumoKm = consumoKm;
    }

    public void setHistorico(Set<Aluguer> al) {
        this.historico = new TreeSet<>();
        Iterator<Aluguer> it = al.iterator();
        while(it.hasNext()) {
            this.historico.add(it.next().clone());
        }
    }

    public void setClassificacoes(List<Integer> classificoes) {
        this.classificoes = classificoes.stream().collect(Collectors.toList());
    }

    public void setDepositoMax(double depositoMax) {
        this.depositoMax = depositoMax;
    }

    public void setDepositoAtual(double depositoAtual) {
        this.depositoAtual = depositoAtual;
    }

    public abstract String toString();
    /*
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Matrícula do Veículo: ").append(this.ID).
        append("\nMarca: ").append(this.marca).
        append("\nPosição atual: ").append(this.getPosicao()).
        append("\nVelocidade Média: ").append(this.velocidade).
        append("\nPreço/km: ").append(this.priceKm).
        append("\nConsumo/km: ").append(this.consumoKm).
        append("\nDepósito: ").append(this.depositoAtual).append(" / ").append(this.depositoMax).
        append("\nHistórico de Alugueres: ").append(this.getHistorico()).
        append("\nHistórico de Classificações:").append(this.getClassificacoes()).append("\n");

        return sb.toString();
    }
*/
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return (this.ID.equals(veiculo.getID()) &&
                this.marca.equals(veiculo.getMarca()) &&
                this.posicao.equals(veiculo.getPosicao()) &&
                this.consumoKm == veiculo.getConsumoKm() &&
                this.velocidade == veiculo.getVelocidade() &&
                this.priceKm == veiculo.getPriceKm() &&
                this.historico.equals(veiculo.getHistorico()) &&
                this.classificoes.equals(veiculo.getClassificacoes()) &&
                this.depositoAtual == veiculo.getDepositoAtual() &&
                this.depositoMax == veiculo.getDepositoMax());
    }

    public abstract Veiculo clone();

}