/**
 * Classe que implementa um Ator que servirá de base para outras classes mais extensas, que utilizam esta mesma informação.
 * Esta classe trata um Ator, constituído por email, nome, password, morada e data de aniversário.
 *
 * @author  Grupo de Trabalho -->INSERIR<--
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Ator implements Serializable {
    private String email; //key
    private String name;
    private String password;
    private String address; // varias componentes da morada
    private LocalDate birthday; // Day-Mth-Yr
    private Set<Aluguer> historico;
    private List<Integer> classificacao; // podemos gerir isto por metodo externo

    /**
     * Construtor por omissão de um Ator.
     */
    public Ator() {
        this.email = "N/A";
        this.name = "N/A";
        this.password = "N/A";
        this.address = "N/A";
        this.birthday = LocalDate.of(1999,1,1);
        this.historico = new TreeSet<>();
        this.classificacao = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de um Ator.
     * Aceita como parâmetros cada componente necessária.
     */
    public Ator(String email, String name, String password, String address, LocalDate date, Set<Aluguer> hist, List<Integer> classif) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.birthday = LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth());
        setHistorico(hist);
        setClassificacao(classif);
    }

    /**
     * Construtor de cópia de um Ator.
     * Aceita como parâmetro outro Ator e utiliza os métodos de acesso aos valores das variáveis de instância.
     */
    public Ator(Ator at) {
        this.email = at.getEmail();
        this.name = at.getName();
        this.password = at.getPassword();
        this.address = at.getAddress();
        this.birthday = at.getBirthday();
        this.historico = at.getHistorico();
        this.classificacao = at.getClassificacao();
    }

    /**
     * Método de instância (get).
     * Devolve o email associado ao Ator.
     *
     * @return email do Ator.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Método de instância (get).
     * Devolve o nome associado ao Ator.
     *
     * @return nome do Ator.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método de instância (get).
     * Devolve a password do email associado ao Ator.
     *
     * @return password do email do Ator.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Método de instância (get).
     * Devolve a morada associado ao Ator.
     *
     * @return Morada do Ator.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Método de instância (get).
     * Devolve a data de aniversário associada ao Ator.
     *
     * @return data de aniversário do Ator.
     */
    public LocalDate getBirthday() {
        return LocalDate.of(this.birthday.getYear(),this.birthday.getMonth(),this.birthday.getDayOfMonth());
    }

    public Set<Aluguer> getHistorico(){
        Set<Aluguer> novo = new TreeSet<>();
        for(Aluguer l : this.historico) {
            novo.add(l.clone());
        }
        return novo;
    }

    public List<Integer> getClassificacao(){
        return new ArrayList<>(this.classificacao);
    }

    /**
     * Método de instância (set).
     * Atualiza o email do utilizador.
     *
     * @param email novo email do utilizador.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método de instância (set).
     * Atualiza o nome do utilizador.
     *
     * @param name novo nome do utilizador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método de instância (set).
     * Atualiza a password do email do utilizador.
     *
     * @param password nova password do utilizador.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método de instância (set).
     * Atualiza a morada do utilizador.
     *
     * @param address nova morada do utilizador.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Método de instância (set).
     * Atualiza a data de aniversário do utilizador.
     *
     * @param bd nova data de aniversário do utilizador.
     */
    public void setBirthday(LocalDate bd) {
        this.birthday = LocalDate.of(bd.getYear(),bd.getMonth(),bd.getDayOfMonth());
    }

    public void setHistorico(Set<Aluguer> hist) {
        this.historico = new TreeSet<>();
        for(Aluguer l : hist) {
           this.historico.add(l.clone());
       }
    }

    public void setClassificacao(List<Integer> classif) {
        this.classificacao = new ArrayList<>(classif);
    }

    /**
     * Método que devolve a representação em String do Ator.
     * @return String com toda a informação do Ator.
     */

    public String toString(){
        return "Nome: " + this.name +
                "\nClassificação média: " + this.calculaClassificao() +
                "\nAniversário: " + this.birthday +
                "\nE-mail: " + this.email +
                "\nPassword: " + this.password +
                "\nMorada: " + this.address;
    }

    /**
     * Método que compara dois objetos.
     * @return booleano que dá verdadeiro se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return (this.email.equals(ator.getEmail()) &&
                this.name.equals(ator.getName()) &&
                this.password.equals(ator.getPassword()) &&
                this.address.equals(ator.getAddress()) &&
                this.birthday.equals(ator.getBirthday()) &&
                this.historico.equals(ator.getHistorico())) &&
                this.classificacao.equals(ator.getClassificacao());
    }

    /**
     * Método que faz uma cópia do ponto receptor da mensagem.
     * @return clone do ponto que recebe a mensagem.
     */
    public Ator clone(){
        return new Ator(this);
    }

    public void addAluguer(Aluguer aluguer){
        this.historico.add(aluguer.clone());
    }

    public double calculaClassificao() {
        int sum = 0;
        for(Integer x : this.classificacao) {
            sum+=x;
        }
        return (double) sum/this.classificacao.size();
    }

    public void addClassificacao(Integer i) {
        this.classificacao.add(i);
    }
}
