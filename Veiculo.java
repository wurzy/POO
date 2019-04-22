import java.util.*;

public class Veiculo {
    private int ID;
    private double velocidade;
    private double priceKm;
    private double consumoKm;
    private double depositoMax, depositoAtual; //litros que tem o deposito de combustivel

    public Veiculo() {
        this.ID = 0;
        this.velocidade = 0;
        this.priceKm = 0;
        this.consumoKm = 0;
        this.depositoMax = 0;
        this.depositoAtual = 0;
    }

    public Veiculo( int id, double velocidade, double priceKm, double consumoKm, double depositoMax, double depositoAtual) {
        this.ID = id;
        this.velocidade = velocidade;
        this.priceKm = priceKm;
        this.consumoKm = consumoKm;
        this.depositoMax = depositoMax;
        this.depositoAtual = depositoAtual;
    }

    public Veiculo(Veiculo x) {
        this.ID = x.getID();
        this.velocidade = x.getVelocidade();
        this.priceKm = x.getPriceKm();
        this.consumoKm = x.getConsumoKm();
        this.depositoMax = x.getDepositoMax();
        this.depositoAtual = x.getDepositoAtual();
    }

    public int getID() { return this.ID; }

    public double getVelocidade() {
        return this.velocidade;
    }

    public double getPriceKm() {
        return this.priceKm;
    }

    public double getConsumoKm() {
        return this.consumoKm;
    }

    public double getDepositoMax() {
        return this.depositoMax;
    }

    public double getDepositoAtual() {
        return this.depositoAtual;
    }

    public void setID(int id) { this.ID = id; }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public void setPriceKm(double priceKm) {
        this.priceKm = priceKm;
    }

    public void setConsumoKm(double consumoKm) {
        this.consumoKm = consumoKm;
    }

    public void setDepositoMax(double depositoMax) {
        this.depositoMax = depositoMax;
    }

    public void setDepositoAtual(double depositoAtual) {
        this.depositoAtual = depositoAtual;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(this.ID).
        append("\nVelocidade Média: ").append(this.velocidade).
        append("\nPreço/km: ").append(this.priceKm).
        append("\nConsumo/km: ").append(this.consumoKm).
        append("\nDepósito: ").append(this.depositoAtual).append(" / ").append(this.depositoMax).append("\n");

        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return (this.ID == veiculo.getID() &&
                this.consumoKm == veiculo.getConsumoKm() &&
                this.velocidade == veiculo.getVelocidade() &&
                this.priceKm == veiculo.getPriceKm() &&
                this.depositoAtual == veiculo.getDepositoAtual() &&
                this.depositoMax == veiculo.getDepositoMax());
    }

    public Veiculo clone(){
        return new Veiculo(this);
    }
}