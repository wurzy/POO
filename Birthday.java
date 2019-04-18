/**
 * Classe que implementa uma data de aniverário.
 * Esta classe gere um dia, mês e ano (os 3 sendo números inteiros), que representam a mesma data.
 *
 * @author  Grupo de Trabalho -->INSERIR<--
 */
public class Birthday {
    private int day, month, year; //DD-MM-YY

    /**
     * Construtor por omissão da data de aniversário.
     */
    public Birthday() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    /**
     * Construtor por parâmetro da data de aniversário.
     *
     * @param day dia.
     * @param month mês.
     * @param year ano.
     */
    public Birthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Construtor de cópia da data de aniversário.
     * Aceita como parãmetro outra data e utiliza os métodos de acesso aos valores das variáveis de instância.
     */
    public Birthday(Birthday bd) {
        this.day = bd.getDay();
        this.month = bd.getMonth();
        this.year = bd.getYear();
    }

    /**
     * Método de instância (get).
     * Devolve o dia da data de aniversário.
     *
     * @return dia da data de aniversário.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Método de instância (get).
     * Devolve o mês da data de aniversário.
     *
     * @return mês da data de aniversário.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Método de instância (get).
     * Devolve o ano da data de aniversário.
     *
     * @return ano da data de aniversário.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Método de instância (set).
     * Atualiza o dia da data de aniversário.
     *
     * @param day novo dia na data.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Método de instância (set).
     * Atualiza o mês da data de aniversário.
     *
     * @param month novo mês na data.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Método de instância (set).
     * Atualiza o ano da data de aniversário.
     *
     * @param year novo ano na data.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Método que devolve a representação em String da data.
     * @return String com o dia, mês e ano no formato DD-MM-AAAA.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.day+ "-");
        sb.append(this.month + "-");
        sb.append(this.year);
        return sb.toString();
    }

    /**
     * Método que compara dois objetos.
     * @return booleano que dá verdadeiro se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Birthday b = (Birthday) o;
        return (this.day == b.getDay() &&
                this.month == b.getMonth() &&
                this.year == b.getYear());
    }

    /**
     * Método que faz uma cópia da data de aniversário receptora da mensagem.
     * @return clone da data de aniversário que recebe a mensagem.
     */
    public Birthday clone(){
        return new Birthday(this);
    }
}
