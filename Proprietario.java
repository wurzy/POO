import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Proprietario extends Ator implements Serializable {
    private Map<String,Veiculo> frota; // id de carro -> carro
    private Set<Aluguer> queue;

    public Proprietario() {
        super();
        this.frota = new HashMap<>();
        this.queue = new TreeSet<>();
    }

    public Proprietario(String email, String name, String password, String address) {
        super(email, name, password, address, LocalDate.now(), new TreeSet<>(),new ArrayList<>());
        this.frota = new HashMap<>();
        this.queue = new TreeSet<>();
    }

    public Proprietario(Proprietario prop) {
        super(prop.getEmail(),prop.getName(),prop.getPassword(),prop.getAddress(),prop.getBirthday(),prop.getHistorico(),prop.getClassificacao());
        setClassificacao(prop.getClassificacao());
        setFrota(prop.getFrota());
        setQueue(prop.getQueue());
    }


    public Map<String, Veiculo> getFrota() {
        return this.frota.entrySet()
                         .stream()
                         .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Set<Aluguer> getQueue(){
        Set<Aluguer> ret = new TreeSet<>();
        for(Aluguer l: this.queue) {
            ret.add(l.clone());
        }
        return ret;
    }


    public void setFrota(Map<String, Veiculo> frota) {
        this.frota = frota.entrySet()
                          .stream()
                          .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setQueue(Set<Aluguer> queue) {
        this.queue = new TreeSet<>();
        for(Aluguer l: queue) {
            this.queue.add(l.clone());
        }
    }

    public String toString(){
        return (super.toString() +
                "\n--------------------------------------------------\n");
    }

    public String toPrint(){
        return "NovoProp:" +
                this.getName() + "," +
                this.getPassword() + "," +
                this.getEmail() + "," +
                this.getAddress();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario that = (Proprietario) o;
        return (super.equals(that) &&
                this.frota.equals(that.getFrota()) &&
                this.queue.equals(that.getQueue()));
    }

    public Proprietario clone(){
        return new Proprietario(this);
    }

    public void addToFrota(Veiculo v) {
        this.frota.put(v.getID(),v.clone());
    }

    public void addToQueue(Aluguer al) {
        this.queue.add(al.clone());
    }

    public void updateFrota(Veiculo x) {
        this.frota.put(x.getID(),x);
    }

    public void removeCarro(Veiculo x) {
        this.frota.remove(x.getID());
    }

    public boolean isInQueue(int id) {
        for(Aluguer l : this.queue) {
            if(l.getAluguerID()==id) {
                return true;
            }
        }
        return false;
    }

    public void removeFromQueue(int id) {
        for(Aluguer l:this.queue) {
            if(id==l.getAluguerID()) {
                this.queue.remove(l);
                return;
            }
        }
    }

    public Aluguer getFromQueue(int id) {
        for(Aluguer l:this.queue) {
            if(id==l.getAluguerID()) {
                return l.clone();
            }
        }
        return null;
    }
}

