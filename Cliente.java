import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Cliente extends Ator implements Serializable {
    private Ponto posicaoI;
    private Ponto posicaoF;
    private Set<Aluguer> queue;


    public Cliente() {
        super();
        this.queue = new TreeSet<>();
        this.posicaoI = new Ponto();
        this.posicaoF = new Ponto();
    }

    public Cliente(String email, String name, String password, String address, LocalDate date, Ponto posicaoI, Set<Aluguer> queue) {
        super(email, name, password, address, date, new TreeSet<>(),new ArrayList<>());
        this.posicaoI = posicaoI.clone();
        this.posicaoF = new Ponto(0,0);
        setQueue(queue);
    }

    public Cliente(Ator at, Ponto posicaoI, Ponto posicaoF, Set<Aluguer> queue) {
        super(at);
        this.posicaoI = posicaoI.clone();
        this.posicaoF = posicaoF.clone();
        setQueue(queue);
    }

    public Cliente(Cliente cl) {
        super(cl.getEmail(),cl.getName(),cl.getPassword(),cl.getAddress(),cl.getBirthday(),cl.getHistorico(),cl.getClassificacao());
        this.posicaoI = cl.getPosicaoI();
        this.posicaoF = cl.getPosicaoF();
        setQueue(cl.getQueue());
    }

    public Ponto getPosicaoI() {
        return this.posicaoI.clone();
    }

    public Ponto getPosicaoF() {return this.posicaoF.clone();}

    public void setPosicaoI(Ponto posicao) {
        this.posicaoI = posicao.clone();
    }

    public void setPosicaoF(Ponto posicao) {this.posicaoF = posicao.clone();}

    public void setQueue(Set<Aluguer> l) {
        this.queue = new TreeSet<>();
        for(Aluguer a: l) {
            this.queue.add(a.clone());
        }
    }

    public Set<Aluguer> getQueue(){
        Set<Aluguer> ret = new TreeSet<>();
        for(Aluguer l: this.queue){
            ret.add(l.clone());
        }
        return ret;
    }

    public List<Aluguer> getQueueList() {
        List<Aluguer> ret = new ArrayList<>();
        for(Aluguer l: this.queue){
            ret.add(l.clone());
        }
        return ret;
    }

    public void addQueue(Aluguer l) {
        this.queue.add(l);
    }

    public void removeFromQueue(int id) {
        for(Aluguer l:this.queue) {
            if(id==l.getAluguerID()) {
                this.queue.remove(l);
                return;
            }
        }
    }


    public String toString() {
        return  super.toString() +
                "\n\nPosição atual: (x , y) = " + this.posicaoI.toString() +
                "\nPosição pretendida: (x , y) = " + this.posicaoF.toString() +
                "\n------------------------------------------------------------\n";

    }

    public String toPrint() {
        Ponto p = this.getPosicaoI();
        return "NovoCliente:" +
                this.getName() + "," +
                this.getPassword() + "," +
                this.getEmail() + "," +
                this.getAddress() + "," +
                p.getX() + "," +
                p.getY();
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
