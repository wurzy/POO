import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Proprietario extends Ator{
    private List<Integer> classificacao; // podemos gerir isto por metodo externo
    private Map<String,Veiculo> frota; // id de carro -> carro
    private Set<Aluguer> queue;

    public Proprietario() {
        super();
        this.classificacao = new ArrayList<>();
        this.frota = new HashMap<>();
        this.queue = new TreeSet<>();
    }

    public Proprietario(String email, String name, String password, String address) {
        super(email, name, password, address, LocalDate.now(), new TreeSet<>());
        this.classificacao = new ArrayList<>();
        this.frota = new HashMap<>();
        this.queue = new TreeSet<>();
    }

    public Proprietario(Proprietario prop) {
        super(prop.getEmail(),prop.getName(),prop.getPassword(),prop.getAddress(),prop.getBirthday(),prop.getHistorico());
        setClassificacao(prop.getClassificacao());
        setFrota(prop.getFrota());
        setQueue(prop.getQueue());
    }

    public List<Integer> getClassificacao() {
        return new ArrayList<>(this.classificacao);
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

    public void setClassificacao(List<Integer> classificacao) {
        this.classificacao = new ArrayList<>(classificacao);
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
                "\n\nClassificação média: " + calculaClassificao() +
                "\nFrota de veículos:\n\n " + this.frota.toString() +
                "\nAlugueres por aceitar:\n\n" + this.queue.toString() + "\n");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario that = (Proprietario) o;
        return (super.equals(that) &&
                this.classificacao.equals(that.getClassificacao()) &&
                this.frota.equals(that.getFrota()) &&
                this.queue.equals(that.getQueue()));
    }

    public Proprietario clone(){
        return new Proprietario(this);
    }

    public double calculaClassificao() {
        int sum = 0;
        for(Integer x : this.classificacao) {
            sum+=x;
        }
        return (double) sum/this.classificacao.size();
    }

    public void addToFrota(Veiculo v) {
        this.frota.put(v.getID(),v.clone());
    }

    public void addToQueue(Aluguer al) {
        this.queue.add(al.clone());
    }
}

