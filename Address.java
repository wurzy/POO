/**
 * Classe que implementa uma morada.
 * Esta classe gere um código postal, distrito, concelho, freguesia, rua, número da casa e andar, caso seja um prédio.
 * Nota: o andar deverá ser 0 quando se trata duma casa particular e não um prédio.
 *
 * @author  Grupo de Trabalho -->INSERIR<--
 */
public class Address {

    private int cityPostal, /* 4750-202  -> 4750 */ townPostal, /* 4750-202  ->  202*/  houseNo /*número da casa*/, andar /*em caso de prédio, caso não seja é 0 por defeito*/;
    private String district, city, town, street;

    /**
     * Construtor por omissão duma morada.
     */
    public Address() {
        this.cityPostal=0;
        this.townPostal=0;
        this.houseNo = 0;
        this.andar = 0;
        this.district = "";
        this.city = "";
        this.town = "";
        this.street="";
    }
    /**
     * Construtor parametrizado duma morada.
     * Aceita como parâmetros os diversos componentes da morada
     * @param cityPostal para um código postal 4750-222 insere-se o 4750.
     * @param townPostal para o mesmo código postal, insere-se o 222.
     * @param houseNo para o número da casa.
     * @param andar para o andar (caso seja um prédio).
     * @param district para o nome do distrito
     * @param city para o nome da concelho.
     * @param town para o nome da freguesia.
     * @param street para o nome da rua.
     */
    public Address(int cityPostal, int townPostal, int houseNo,int andar, String district, String city, String town, String street) {
        this.cityPostal = cityPostal;
        this.townPostal = townPostal;
        this.houseNo = houseNo;
        this.andar = andar;
        this.district = district;
        this.city = city;
        this.town = town;
        this.street = street;
    }

    /**
     * Construtor por cópia de uma morada.
     * Aceita como parâmetro uma morada já definida, utilizando os métodos de acesso às variáveis de instância.
     */
    public Address(Address ad) {
        this.cityPostal = ad.getCityPostal();
        this.townPostal = ad.getTownPostal();
        this.houseNo = ad.getHouseNo();
        this.andar = ad.getAndar();
        this.district = ad.getDistrict();
        this.city = ad.getCity();
        this.town = ad.getTown();
        this.street = ad.getStreet();
    }

    /**
     * Método de instância (get).
     * Devolve o valor da primeira parte do código postal.
     *
     * @return o valor referido (4750-222 devolve 4750).
     */
    public int getCityPostal() {
        return this.cityPostal;
    }

    /**
     * Método de instância (get).
     * Devolve o valor da segunda parte do código postal.
     *
     * @return o valor referido (4750-222 devolve 222) associado à morada.
     */
    public int getTownPostal() {
        return this.townPostal;
    }

    /**
     * Método de instância (get).
     * Devolve o número da casa.
     *
     * @return o número da casa associado à morada.
     */
    public int getHouseNo() {
        return this.houseNo;
    }

    /**
     * Método de instância (get).
     * Devolve o andar (caso seja um prédio).
     *
     * @return o andar associado à morada.
     */
    public int getAndar() {
        return this.andar;
    }

    /**
     * Método de instância (get).
     * Devolve o nome dum distrito.
     *
     * @return distrito associado à morada.
     */
    public String getDistrict() {
        return this.district;
    }

    /**
     * Método de instância (get).
     * Devolve o nome dum conselho.
     *
     * @return o nome do conselho associado à morada.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Método de instância (get).
     * Devolve o nome da freguesia.
     *
     * @return o nome da freguesia assoaciada à morada.
     */
    public String getTown() {
        return this.town;
    }

    /**
     * Método de instância (get).
     * Devolve o nome da rua.
     *
     * @return o nome da rua associada à morada.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Método de instância (set).
     * Atualiza o valor da primeira parte do código postal (refere-se ao 4750 em 4750-222).
     *
     * @param cityPostal novo valor para a primeira parte do código postal.
     */
    public void setCityPostal(int cityPostal) {
        this.cityPostal = cityPostal;
    }

    /**
     * Método de instância (set).
     * Atualiza o valor da segunda parte do código postal (refere-se ao 222 em 4750-222).
     *
     * @param townPostal novo valor para a segunda parte do código postal.
     */
    public void setTownPostal(int townPostal) {
        this.townPostal = townPostal;
    }

    /**
     * Método de instância (set).
     * Atualiza o número da casa duma morada.
     *
     * @param houseNo novo número da casa para a morada.
     */
    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    /**
     * Método de instância (set).
     * Atualiza o andar da morada.
     *
     * @param andar novo andar da morada.
     */
    public void setAndar(int andar) {
        this.andar = andar;
    }

    /**
     * Método de instância (set).
     * Atualiza o distrito da morada.
     *
     * @param district novo distrito para a morada.
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Método de instância (set).
     * Atualiza o concelho da morada.
     *
     * @param city novo concelho para a morada.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Método de instância (set).
     * Atualiza a freguesia da morada.
     *
     * @param town nova freguesia para a morada.
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Método de instância (set).
     * Atualiza a rua da morada.
     *
     * @param street nova rua para a morada.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Método que constrói o formato padrão do código postal.
     * @return formato padrão do código postal. Exemplo: 4750-222
     */
    public String postalCode(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.cityPostal).append("-").
        append(this.townPostal).append("\n");

        return sb.toString();
    }

    /**
     * Método que devolve a representação em String de Address.
     * @return String com as componentes todas da morada.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sp;
        int a = this.getAndar();
        boolean x = a!=0;
        String aux = (x?"\nÉ um prédio":"\nNão é um prédio");
        sp = new StringBuilder(aux);
        if(x) {
            sp.append(". Andar: ").append(a);
        }
        sb.append("Distrito: ").append(this.district).
        append("\nConcelho: ").append(this.city).
        append("\nFreguesia: ").append(this.town).
        append("\nRua: ").append(this.street).
        append("\nNúmero da porta: ").append(this.houseNo).
        append(sp).
        append("\nCódigo Postal: ").append(this.postalCode());

        return sb.toString();
    }

    /**
     * Método que compara dois objetos.
     * @return booleano que dá verdadeiro se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Address add = (Address) o;
        return this.cityPostal == add.getCityPostal() &&
                this.townPostal == add.getTownPostal() &&
                this.houseNo == add.getHouseNo() &&
                this.andar == add.getAndar() &&
                this.district == add.getDistrict() &&
                this.city.equals(add.getCity()) &&
                this.town.equals(add.getTown()) &&
                this.street.equals(add.getStreet());
    }

    /**
     * Método que faz uma cópia da morada receptora da mensagem.
     * @return clone da morada que recebe a mensagem.
     */
    public Address clone() {
        return new Address(this);
    }
}
