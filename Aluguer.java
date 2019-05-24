import java.io.Serializable;
import java.time.LocalDate;

public class Aluguer implements Comparable<Aluguer>, Serializable {
    private int aluguerID; //ordenaçao
    private String veiculoID; //matricula do veiculo
    private String clienteID; //password do cliente
    private String propID;
    private String tipo;
    private String tipoVeiculo;
    private Ponto inicioPercurso; //onde estava cliente
    private Ponto fimPercurso; //onde quer ir cliente
    private Ponto posInicialVeiculo; //onde veiculo estava
    private double preco; // inicio->fim preco
    private double tempo; // inicio->fim tempo
    private LocalDate date;
    private Randomizer randomizer;

    public Aluguer() {
        this.aluguerID = -1;
        this.veiculoID = "N/A";
        this.clienteID = "N/A";
        this.propID = "N/A";
        this.tipo = "N/A";
        this.tipoVeiculo = "N/A";
        this.inicioPercurso = new Ponto();
        this.fimPercurso = new Ponto();
        this.posInicialVeiculo = new Ponto();
        this.preco = 0;
        this.tempo = 0;
        this.date = LocalDate.now();
        this.randomizer = new Randomizer(0);
    }

    public Aluguer(Aluguer al) {
        this.aluguerID = al.getAluguerID();
        this.veiculoID = al.getVeiculoID();
        this.clienteID = al.getClienteID();
        this.propID = al.getPropID();
        this.tipo = al.getTipo();
        this.tipoVeiculo = al.getTipoVeiculo();
        this.inicioPercurso = al.getInicioPercurso();
        this.fimPercurso = al.getFimPercurso();
        this.posInicialVeiculo = al.getPosInicialVeiculo();
        this.preco = al.getPreco();
        this.tempo = al.getTempo();
        this.date = al.getDate();
        this.randomizer = al.getRandomizer();
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

    public String getPropID(){return this.propID;}

    public String getTipo(){return this.tipo;}

    public String getTipoVeiculo(){return this.tipoVeiculo;}

    public Ponto getInicioPercurso() {
        return this.inicioPercurso.clone();
    }

    public Ponto getFimPercurso() {
        return this.fimPercurso.clone();
    }

    public Ponto getPosInicialVeiculo() {
        return this.posInicialVeiculo.clone();
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

    public Randomizer getRandomizer(){
        return this.randomizer.clone();
    }

    public void setRandomizer(Randomizer r) {
        this.randomizer = r.clone();
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

    public void setPropID(String propID) {this.propID = propID;}

    public void setTipoVeiculo(String tipoVeiculo) {this.tipoVeiculo = tipoVeiculo;}

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
                "\nNIF do Cliente: " + this.clienteID +
                "\nNIF do Proprietário: " + this.propID +
                "\nTipo de Aluguer: "  + this.tipo +
                "\nTipo de Veículo: " + this.tipoVeiculo +
                "\nPosição inicial do Cliente: " + this.inicioPercurso +
                "Posição final do Cliente: " + this.fimPercurso +
                "Posição inicial do Veículo: " + this.posInicialVeiculo+
                "Distância total (em km): " + this.distancia()  +
                "\nCusto da viagem (em €): " + this.preco +
                "\nTempo que demorou a viagem (em h): " + this.tempo +
                "\nData em que foi realizado: " + this.date +
                "\n---------------------------------------------\n");
    }

    public String toPrint(){
        return "Aluguer:" +
                this.clienteID + "," +
                this.fimPercurso.getX() + "," +
                this.fimPercurso.getY() + "," +
                this.tipoVeiculo + "," +
                this.tipo + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return (this.aluguerID == aluguer.getAluguerID() &&
                this.veiculoID.equals(aluguer.getVeiculoID()) &&
                this.clienteID.equals(aluguer.getClienteID()) &&
                this.propID.equals(aluguer.getPropID()) &&
                this.tipo.equals(aluguer.getTipo()) &&
                this.tipoVeiculo.equals(aluguer.getTipoVeiculo()) &&
                this.inicioPercurso.equals(aluguer.getInicioPercurso()) &&
                this.fimPercurso.equals(aluguer.getFimPercurso()) &&
                this.posInicialVeiculo.equals(aluguer.getPosInicialVeiculo()) &&
                this.preco == aluguer.getPreco() &&
                this.tempo == aluguer.getTempo() &&
                this.date.equals(aluguer.getDate()) &&
                this.randomizer.equals(aluguer.getRandomizer())
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

    public double distancia() {
        double res = this.posInicialVeiculo.distancia(this.getFimPercurso());
        return (double) Math.round(res*100)/100;
    }

}
