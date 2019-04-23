import java.util.*;

public class Aluguer implements Comparable<Aluguer>{
    private int aluguerID; //so para distinguir
    private int veiculoID; //id do veiculo
    private String clienteID; //email do cliente
    private String propID; //email do proprietario
    private Ponto inicioPercurso; //onde estava cliente
    private Ponto fimPercurso; //onde quer ir cliente
    private Ponto posInicialVeiculo; //onde veiculo estava
    private int rating; // rating
    private double preco; // inicio->fim preco
    private double tempo; // inicio->fim tempo

    public Aluguer() {
        this.aluguerID = -1;
        this.veiculoID = -1;
        this.clienteID = "N/A";
        this.propID = "N/A";
        this.inicioPercurso = new Ponto();
        this.fimPercurso = new Ponto();
        this.posInicialVeiculo = new Ponto();
        this.rating = 0;
        this.preco = 0;
        this.tempo = 0;
    }

    public Aluguer(int aluguerID, int veiculoID, String clienteID, String propID, Ponto inicioPercurso, Ponto fimPercurso, Ponto posInicialVeiculo, int rating, double preco, double tempo) {
        this.aluguerID = aluguerID;
        this.veiculoID = veiculoID;
        this.clienteID = clienteID;
        this.propID = propID;
        this.inicioPercurso = inicioPercurso.clone();
        this.fimPercurso = fimPercurso.clone();
        this.posInicialVeiculo = posInicialVeiculo.clone();
        this.rating = rating;
        this.preco = preco;
        this.tempo = tempo;
    }

    public Aluguer(Aluguer al) {
        this.aluguerID = al.getAluguerID();
        this.veiculoID = al.getVeiculoID();
        this.clienteID = al.getClienteID();
        this.propID = al.getPropID();
        this.inicioPercurso = al.getInicioPercurso();
        this.fimPercurso = al.getFimPercurso();
        this.posInicialVeiculo = al.getPosInicialVeiculo();
        this.rating = al.getRating();
        this.preco = al.getPreco();
        this.tempo = al.getTempo();
    }

    public int getAluguerID() {
        return this.aluguerID;
    }

    public int getVeiculoID() {
        return this.veiculoID;
    }

    public String getClienteID() {
        return this.clienteID;
    }

    public String getPropID() {
        return this.propID;
    }

    public Ponto getInicioPercurso() {
        return this.inicioPercurso.clone();
    }

    public Ponto getFimPercurso() {
        return this.fimPercurso.clone();
    }

    public Ponto getPosInicialVeiculo() {
        return this.posInicialVeiculo.clone();
    }

    public int getRating() {
        return this.rating;
    }

    public double getPreco() {
        return this.preco;
    }

    public double getTempo() {
        return this.tempo;
    }

    public void setAluguerID(int aluguerID) {
        this.aluguerID = aluguerID;
    }

    public void setVeiculoID(int veiculoID) {
        this.veiculoID = veiculoID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public void setPropID(String propID) {
        this.propID = propID;
    }

    public void setInicioPercurso(Ponto inicioPercurso) {
        this.inicioPercurso = new Ponto(inicioPercurso);
    }

    public void setFimPercurso(Ponto fimPercurso) {
        this.fimPercurso = new Ponto(fimPercurso);
    }

    public void setPosInicialVeiculo(Ponto posInicialVeiculo) {
        this.posInicialVeiculo = new Ponto(posInicialVeiculo);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("ID do Aluguer: ").append(this.aluguerID).
        append("\nID do Veículo: ").append(this.veiculoID).
        append("\nEmail do Cliente: ").append(this.clienteID).
        append("\nEmail do Proprietário: ").append(this.propID).
        append("\nPosição inicial do Cliente: ").append(this.inicioPercurso.toString()).
        append("Posição final do Cliente: ").append(this.fimPercurso.toString()).
        append("Posição inicial do Veículo:").append(this.posInicialVeiculo.toString()).
        append("Pontuação dada pelo Cliente: ").append(this.rating).
        append("\nCusto da viagem: ").append(this.preco).
        append("\nTempo que demorou a viagem: ").append(this.tempo).append("\n");
       // append("\n").append();

        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return (this.aluguerID == aluguer.getAluguerID() &&
                this.veiculoID == aluguer.getVeiculoID() &&
                this.clienteID == aluguer.getClienteID() &&
                this.propID == aluguer.getPropID() &&
                this.inicioPercurso.equals(aluguer.getInicioPercurso()) &&
                this.fimPercurso.equals(aluguer.getFimPercurso()) &&
                this.posInicialVeiculo.equals(aluguer.getPosInicialVeiculo()) &&
                this.rating == aluguer.getRating() &&
                this.preco == aluguer.getPreco() &&
                this.tempo == aluguer.getTempo()
                );
    }

    public Aluguer clone() {
        return new Aluguer(this);
    }

    public int compareTo(Aluguer al) {
        if(this.aluguerID == al.getAluguerID()) return 0;
        else if (this.aluguerID < al.getAluguerID()) return -1;
        else return 1;
    }

}
