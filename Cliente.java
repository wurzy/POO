import java.util.*;

public class Cliente extends Ator{
    private Ponto posicao;
    //private Ponto posicaoF;
    private Set<Aluguer> historico;

    public Ponto getPosicao() {
        return this.posicao.clone();
    }

    public Set<Aluguer> getHistorico() {
        return historico;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return (super.equals(cliente)&&
                this.historico.equals(cliente.getHistorico())&&
                this.posicao.equals(cliente.getPosicao()));
    }

}
