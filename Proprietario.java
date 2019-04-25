import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Proprietario extends Ator{
    private Set<Aluguer> historico; // might be changed
    private double classificacao; // podemos gerir isto por metodo externo
    private Map<String,Veiculo> frota; // id de carro -> carro

    public Proprietario() {
        super();
        this.historico = new TreeSet<>();
        this.classificacao = 0;
        this.frota = new HashMap<>();
    }

    public Proprietario(String email, String name, String password, Address address, LocalDate date, Set<Aluguer> historico, double classificacao, Map<String, Veiculo> frota) {
        super(email, name, password, address, date);
        setHistorico(historico);
        this.classificacao = classificacao;
        setFrota(frota);
    }

    public Proprietario(Ator at, Set<Aluguer> historico, double classificacao, Map<String, Veiculo> frota) {
        super(at);
        setHistorico(historico);
        this.classificacao = classificacao;
        setFrota(frota);
    }

    public Proprietario(Proprietario prop) {
        super(prop.getEmail(),prop.getName(),prop.getPassword(),prop.getAddress(),prop.getBirthday());
        setHistorico(prop.getHistorico());
        this.classificacao = prop.getClassificacao();
        setFrota(prop.getFrota());
    }

    public Set<Aluguer> getHistorico() {
        Set<Aluguer> getter = new TreeSet<>();
        Iterator<Aluguer> it = this.historico.iterator();
        while(it.hasNext()) {
            getter.add(it.next().clone());
        }
        return getter;
    }

    public double getClassificacao() {
        return this.classificacao;
    }

    public Map<String, Veiculo> getFrota() {
        return this.frota.entrySet()
                         .stream()
                         .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setHistorico(Set<Aluguer> historico) {
        this.historico = new TreeSet<>();
        Iterator<Aluguer> it = historico.iterator();
        while(it.hasNext()) {
            this.historico.add(it.next().clone());
        }
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public void setFrota(Map<String, Veiculo> frota) {
        this.frota = frota.entrySet()
                          .stream()
                          .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public String toString(){
        return ("Classificação média: " + this.classificacao +
                "\n\nAlugueres realizados:\n\n" + this.historico.toString() +
                "\n\nFrota de veículos:\n\n " + this.frota.toString());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario that = (Proprietario) o;
        return (this.classificacao == that.getClassificacao() &&
                this.historico.equals(that.getHistorico()) &&
                this.frota.equals(that.getFrota()));
    }

    public Proprietario clone(){
        return new Proprietario(this);
    }
}

