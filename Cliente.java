import java.time.LocalDate;
import java.util.*;

public class Cliente extends Ator{
    private Ponto posicao;
    //private Ponto posicaoF;
    private Set<Aluguer> historico;

    // -> aqui da jeito ter 4 construtores i think
    public Cliente() {
        super();
        this.posicao = new Ponto();
        this.historico = new TreeSet<>();
    }

    public Cliente(String email, String name, String password, Address address, LocalDate date, Ponto posicao, Set<Aluguer> historico) {
        super(email, name, password, address, date);
        this.posicao = posicao;
        setHistorico(historico);
    }

    public Cliente(Ator at, Ponto posicao, Set<Aluguer> historico) {
        super(at);
        this.posicao = posicao.clone();
        setHistorico(historico);
    }

    public Cliente(Cliente cl) {
        super(cl.getEmail(),cl.getName(),cl.getPassword(),cl.getAddress(),cl.getBirthday());
        this.posicao = cl.getPosicao();
        setHistorico(cl.getHistorico());

    }

    public Ponto getPosicao() {
        return this.posicao.clone();
    }

    public Set<Aluguer> getHistorico() {
        Set<Aluguer> getter = new TreeSet<>();
        Iterator<Aluguer> it = this.historico.iterator();
        while(it.hasNext()) {
            getter.add(it.next().clone());
        }
        return getter;
    }

    public void setPosicao(Ponto posicao) {
        this.posicao = posicao.clone();
    }

    public void setHistorico(Set<Aluguer> al) {
        this.historico = new TreeSet<>();
        Iterator<Aluguer> it = al.iterator();
        while(it.hasNext()) {
            this.historico.add(it.next().clone());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString()).
        append("\nAlugueres realizados: \n").append(this.historico).
        append("\nPosição atual: (x , y) = ").append(this.posicao.toString()).append("\n");

        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return (super.equals(cliente)&&
                this.historico.equals(cliente.getHistorico())&&
                this.posicao.equals(cliente.getPosicao()));
    }

}
