import java.util.*;
import java.util.stream.Collectors;

public class Veiculo {
    private int ID;
    private int vezesUsado;
    private double velocidade;
    private double priceKm;
    private double consumoKm;
    private Set<Aluguer> historico;
    private List<Integer> classificoes;
    private double depositoMax;
    private double depositoAtual; //litros que tem o deposito de combustivel

    public Veiculo() {
        this.ID = -1;
        this.vezesUsado = 0;
        this.velocidade = 0;
        this.priceKm = 0;
        this.consumoKm = 0;
        this.historico = new TreeSet<>();
        this.classificoes = new ArrayList<>();
        this.depositoMax = 0;
        this.depositoAtual = 0;
    }

    public Veiculo(int id, int vezesUsado, double velocidade, double priceKm, double consumoKm, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax) {
        this.ID = id;
        this.vezesUsado = vezesUsado;
        this.velocidade = velocidade;
        this.priceKm = priceKm;
        this.consumoKm = consumoKm;
        setHistorico(historico);
        setClassificacoes(classificoes);
        this.depositoMax = depositoMax;
        this.depositoAtual = depositoAtual;
    }

    public Veiculo(Veiculo x) {
        this.ID = x.getID();
        this.vezesUsado = x.getVezesUsado();
        this.velocidade = x.getVelocidade();
        this.priceKm = x.getPriceKm();
        this.consumoKm = x.getConsumoKm();
        this.historico = x.getHistorico();
        this.classificoes = x.getClassificacoes();
        this.depositoMax = x.getDepositoMax();
        this.depositoAtual = x.getDepositoAtual();
    }

    public int getID() { return this.ID; }

    public int getVezesUsado() {return this.vezesUsado;}

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

    public void setID(int id) { this.ID = id; }

    public void setVezesUsado(int vezesUsado) {this.vezesUsado = vezesUsado;}

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

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("ID do Veículo: ").append(this.ID).
        append("\nNúmero de vezes que foi alugado: ").append(this.getVezesUsado()).
        append("\nVelocidade Média: ").append(this.velocidade).
        append("\nPreço/km: ").append(this.priceKm).
        append("\nConsumo/km: ").append(this.consumoKm).
        append("\nDepósito: ").append(this.depositoAtual).append(" / ").append(this.depositoMax).
        append("\nHistórico de Alugueres: ").append(this.getHistorico()).
        append("\nHistórico de Classificações:").append(this.getClassificacoes()).append("\n");

        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return (this.ID == veiculo.getID() &&
                this.vezesUsado == veiculo.getVezesUsado() &&
                this.consumoKm == veiculo.getConsumoKm() &&
                this.velocidade == veiculo.getVelocidade() &&
                this.priceKm == veiculo.getPriceKm() &&
                this.historico.equals(veiculo.getHistorico()) &&
                this.classificoes.equals(veiculo.getClassificacoes()) &&
                this.depositoAtual == veiculo.getDepositoAtual() &&
                this.depositoMax == veiculo.getDepositoMax());
    }

    public Veiculo clone(){
        return new Veiculo(this);
    }
}