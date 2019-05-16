import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Proprietario extends Ator{
    private List<Integer> classificacao; // podemos gerir isto por metodo externo
    private Map<String,Veiculo> frota; // id de carro -> carro

    public Proprietario() {
        super();
        this.classificacao = new ArrayList<>();
        this.frota = new HashMap<>();
    }

    public Proprietario(String email, String name, String password, String address, LocalDate date, Set<Aluguer> historico, List<Integer> classificacao, Map<String, Veiculo> frota) {
        super(email, name, password, address, date,historico);
        this.classificacao = new ArrayList<>(classificacao);
        setFrota(frota);
    }

    public Proprietario(Ator at, List<Integer> classificacao, Map<String, Veiculo> frota) {
        super(at);
        this.classificacao = new ArrayList<>(classificacao);
        setFrota(frota);
    }

    public Proprietario(Proprietario prop) {
        super(prop.getEmail(),prop.getName(),prop.getPassword(),prop.getAddress(),prop.getBirthday(),prop.getHistorico());
        this.classificacao = prop.getClassificacao();
        setFrota(prop.getFrota());
    }

    public List<Integer> getClassificacao() {
        return new ArrayList<>(this.classificacao);
    }

    public Map<String, Veiculo> getFrota() {
        return this.frota.entrySet()
                         .stream()
                         .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setClassificacao(List<Integer> classificacao) {
        this.classificacao = new ArrayList<>(classificacao);
    }

    public void setFrota(Map<String, Veiculo> frota) {
        this.frota = frota.entrySet()
                          .stream()
                          .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public String toString(){
        return (super.toString() +
                "\n\nClassificação média: " + calculaClassificao() +
                "\nFrota de veículos:\n\n " + this.frota.toString()) + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario that = (Proprietario) o;
        return (super.equals(that) &&
                this.classificacao.equals(that.getClassificacao()) &&
                this.frota.equals(that.getFrota()));
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

}

