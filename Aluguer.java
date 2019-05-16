import java.time.LocalDate;
import java.util.*;

public class Aluguer implements Comparable<Aluguer>{
    private int aluguerID; //ordenaçao
    private String veiculoID; //matricula do veiculo
    private String clienteID; //email do cliente
    private String tipo;
    //tirei email prop meti data
    private Ponto inicioPercurso; //onde estava cliente
    private Ponto fimPercurso; //onde quer ir cliente
    private Ponto posInicialVeiculo; //onde veiculo estava
    private int rating; // rating
    private double preco; // inicio->fim preco
    private double tempo; // inicio->fim tempo
    private LocalDate date;

    public Aluguer() {
        this.aluguerID = -1;
        this.veiculoID = "N/A";
        this.clienteID = "N/A";
        this.tipo = "N/A";
        this.inicioPercurso = new Ponto();
        this.fimPercurso = new Ponto();
        this.posInicialVeiculo = new Ponto();
        this.rating = 0;
        this.preco = 0;
        this.tempo = 0;
        this.date = LocalDate.now();
    }

    public Aluguer(int aluguerID, String veiculoID, String clienteID, String tipo, Ponto inicioPercurso, Ponto fimPercurso, Ponto posInicialVeiculo, int rating, double preco, double tempo, LocalDate date) {
        this.aluguerID = aluguerID;
        this.veiculoID = veiculoID;
        this.clienteID = clienteID;
        this.tipo = tipo;
        this.inicioPercurso = inicioPercurso.clone();
        this.fimPercurso = fimPercurso.clone();
        this.posInicialVeiculo = posInicialVeiculo.clone();
        this.rating = rating;
        this.preco = preco;
        this.tempo = tempo;
        this.date = LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth());
    }

    public Aluguer(Aluguer al) {
        this.aluguerID = al.getAluguerID();
        this.veiculoID = al.getVeiculoID();
        this.clienteID = al.getClienteID();
        this.tipo = al.getTipo();
        this.inicioPercurso = al.getInicioPercurso();
        this.fimPercurso = al.getFimPercurso();
        this.posInicialVeiculo = al.getPosInicialVeiculo();
        this.rating = al.getRating();
        this.preco = al.getPreco();
        this.tempo = al.getTempo();
        this.date = al.getDate();
    }

    public int getAluguerID() {
        return this.aluguerID;
    }

    public String getVeiculoID() {
        return this.veiculoID;
    }

    public String getClienteID() {
        return this.clienteID;
    }

    public String getTipo(){return this.tipo;}

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

    public LocalDate getDate(){
        return LocalDate.of(this.date.getYear(),this.date.getMonth(),this.date.getDayOfMonth());
    }

    public void setAluguerID(int aluguerID) {
        this.aluguerID = aluguerID;
    }

    public void setVeiculoID(String veiculoID) {
        this.veiculoID = veiculoID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public void setTipo(String tipo) {this.tipo = tipo;}

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

    public void setDate(LocalDate date) {
        this.date = LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth());
    }

    public String toString(){
        return ("ID do Aluguer: " + this.aluguerID +
                "\nMatrícula do Veículo: " + this.veiculoID +
                "\nEmail do Cliente: " + this.clienteID +
                "\nTipo de Aluguer: "  + this.tipo +
                "\nPosição inicial do Cliente: " + this.inicioPercurso +
                "Posição final do Cliente: " + this.fimPercurso +
                "Posição inicial do Veículo:" + this.posInicialVeiculo+
                "Pontuação dada pelo Cliente: " + this.rating +
                "\nCusto da viagem: " + this.preco +
                "\nTempo que demorou a viagem: " + this.tempo +
                "\nData em que foi realizado: " + this.date);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return (this.aluguerID == aluguer.getAluguerID() &&
                this.veiculoID.equals(aluguer.getVeiculoID()) &&
                this.clienteID.equals(aluguer.getClienteID()) &&
                this.tipo.equals(aluguer.getTipo()) &&
                this.inicioPercurso.equals(aluguer.getInicioPercurso()) &&
                this.fimPercurso.equals(aluguer.getFimPercurso()) &&
                this.posInicialVeiculo.equals(aluguer.getPosInicialVeiculo()) &&
                this.rating == aluguer.getRating() &&
                this.preco == aluguer.getPreco() &&
                this.tempo == aluguer.getTempo() &&
                this.date.equals(aluguer.getDate())
                );
    }

    public Aluguer clone() {
        return new Aluguer(this);
    }

    public int compareTo(Aluguer al) {
        //return Integer.compare(this.aluguerID,al.getAluguerID());

        if(this.aluguerID == al.getAluguerID()) return 0;
        else if (this.aluguerID < al.getAluguerID()) return -1;
        else return 1;

    }

}
