import java.util.*;
import java.util.stream.Collectors;

public abstract class Veiculo {
    private String ID; // matricula
    private String marca;
    private String prop;
    private Ponto posicao;
    private String tipo;
    private double velocidade; // Vel media
    private double priceKm; // €/km
    private double consumoKm; // Combust/KM
    private Set<Aluguer> historico; // alugueres
    private List<Integer> classificoes; // ratings
    private double depositoMax; // autonomia
    private double depositoAtual; // autonomia atual

    public Veiculo() {
        this.ID = "N/A";
        this.marca = "N/A";
        this.prop = "N/A";
        this.posicao = new Ponto();
        this.tipo = "N/A";
        this.velocidade = 0.0;
        this.priceKm = 0.0; //gerimos isto nas extended
        this.consumoKm = 0.0;
        this.historico = new TreeSet<>();
        this.classificoes = new ArrayList<>();
        this.depositoMax = 0;
        this.depositoAtual = 0;
    }

    public Veiculo(String id, String marca, String prop, Ponto posicao, String tipo, Set<Aluguer> historico, List<Integer> classificoes, double depositoAtual, double depositoMax) {
        this.ID = id;
        this.marca = marca;
        this.prop = prop;
        this.posicao = posicao.clone();
        this.tipo = tipo;
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
        this.prop = x.getProp();
        this.posicao = x.getPosicao();
        this.tipo = x.getTipo();
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

    public String getTipo(){return this.tipo;}

    public String getProp(){return this.prop;}

    public void setID(String id) { this.ID = id; }

    public void setMarca(String marca) {this.marca = marca;}

    public void setTipo(String tipo) {this.tipo = tipo;}

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

    public void setProp(String prop) {this.prop = prop;}

    public String historicString(){
        return "Histórico de Alugueres:\n " + this.getHistorico();
    }

    public String toString() {
        return "\nMatrícula do Veículo: " + this.getID() +
                "\nMarca: " + this.getMarca() +
                "\nNIF Proprietário: " + this.getProp() +
                "\nPosição atual: " + this.getPosicao() +
                "Velocidade média: " + this.getVelocidade() +
                "\nPreço/km: " + this.getPriceKm() + " €/km" +
                "\nConsumo/km: " + this.getConsumoKm() + " L/km" +
                "\nAutonomia: " + this.getDepositoAtual() + "/" + this.getDepositoMax() + " km" +
                "\n-------------------------------------------\n";
                //"\n\nHistórico de Alugueres:\n\n " + this.getHistorico() +
                //"\n\nHistórico de Classificações:\n\n " + this.getClassificacoes() + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return (this.ID.equals(veiculo.getID()) &&
                this.marca.equals(veiculo.getMarca()) &&
                this.tipo.equals(veiculo.getTipo()) &&
                this.prop.equals(veiculo.getProp()) &&
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

    public boolean hasAutonomia(double dist) {
        double consumo = this.consumoKm*dist;
        double result = this.depositoAtual - consumo;

        return (result >= 0);
    }

    public void updateAutonomia(double dist) {
        double consumo = this.consumoKm*dist;
        double result = this.depositoAtual - consumo;
        this.depositoAtual = (double) Math.round(result*100)/100;
    }

    public void addClassificacao(int rating) {
        this.classificoes.add(rating);
    }

    public double calculaTarifa(Ponto i, Ponto f) {
        double preco = 0;
        double dist = i.distancia(f);
        preco = this.priceKm * dist;
        return (double) Math.round(preco*100)/100;
    }

    public double calculaTempo(Ponto i, Ponto f) {
        double tempo;
        double dist = i.distancia(f);
        tempo = dist/this.velocidade;
        return (double) Math.round(tempo*100)/100;
    }

}