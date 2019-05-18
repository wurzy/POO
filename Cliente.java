import java.time.LocalDate;
import java.util.*;

public class Cliente extends Ator{
    private Ponto posicaoI;
    private Ponto posicaoF;


    // -> aqui da jeito ter 4 construtores i think
    public Cliente() {
        super();
        this.posicaoI = new Ponto();
        this.posicaoF = new Ponto();
    }

    public Cliente(String email, String name, String password, String address, LocalDate date, Ponto posicaoI, Ponto posicaoF,Set<Aluguer>historico, List<Integer> classif) {
        super(email, name, password, address, date,historico, classif);
        this.posicaoI = posicaoI;
        this.posicaoF = posicaoF;

    }

    public Cliente(Ator at, Ponto posicaoI, Ponto posicaoF) {
        super(at);
        this.posicaoI = posicaoI.clone();
        this.posicaoF = posicaoF.clone();
    }

    public Cliente(Cliente cl) {
        super(cl.getEmail(),cl.getName(),cl.getPassword(),cl.getAddress(),cl.getBirthday(),cl.getHistorico(),cl.getClassificacao());
        this.posicaoI = cl.getPosicaoI();
        this.posicaoF = cl.getPosicaoF();
    }

    public Ponto getPosicaoI() {
        return this.posicaoI.clone();
    }

    public Ponto getPosicaoF() {return this.posicaoF.clone();}

    public void setPosicaoI(Ponto posicao) {
        this.posicaoI = posicao.clone();
    }

    public void setPosicaoF(Ponto posicao) {this.posicaoF = posicao.clone();}


    public String toString() {
        return  super.toString() +
                "\n\nPosição atual: (x , y) = " + this.posicaoI.toString() +
                "\nPosição pretendida: (x , y) = " + this.posicaoF.toString() +
                "\n------------------------------------------------------------\n";

    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return (super.equals(cliente) &&
                this.posicaoI.equals(cliente.getPosicaoI()) &&
                this.posicaoF.equals(cliente.getPosicaoF()));
    }

    public Cliente clone(){
        return new Cliente(this);
    }

}
