import java.util.*;
import java.util.stream.Collectors;

public class Proprietario extends Ator{
    private Map< String , Veiculo > historico; // <K = NOME DO CLIENTE, VEICULO ALUGADO>
    private List<Integer> classificacao;

    public Proprietario() {
        super();
        this.historico = new HashMap<>();
        this.classificacao = new ArrayList<>();
    }

    public Proprietario(Ator at, Map<String,Veiculo> hist, List<Integer> classif) {
        super(at);
        setHistorico(hist);
        setClassificacao(classif);
    }

    public Proprietario(Proprietario pro) {
        super(pro);
        this.historico = pro.getHistorico();
        this.classificacao = pro.getClassificacao();
    }

    public void setHistorico(Map<String,Veiculo> hist){
        this.historico = hist.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setClassificacao(List<Integer> classif) {
        this.classificacao = new ArrayList<>();
        for(Integer i:classif) {
            this.classificacao.add(i);
        }
    }

    public Map<String, Veiculo> getHistorico() {
        return this.historico.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public List<Integer> getClassificacao() {
        return this.classificacao.stream().collect(Collectors.toList());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(this.historico.toString()).append("\n\n").
                append(this.classificacao.toString()).append("\n\n");
        sb.append(super.toString());
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Proprietario that = (Proprietario) o;
        return (super.equals(that) &&
                this.classificacao.equals(that.getClassificacao()) &&
                this.historico.equals(that.getHistorico()));
    }

    public Proprietario clone() {
        return new Proprietario(this);
    }

}
