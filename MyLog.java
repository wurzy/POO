import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class MyLog {
    private Map<String,Cliente> clientes;
    private Map<String,Proprietario> proprietarios;
    private Map<String,Veiculo> listaVeiculos; // MATRICULA - CARRO
    private Set<Aluguer> alugueres;

    public MyLog(){
        List<String> lidos = readLinesWithBR("logs.bak");
        this.clientes = new HashMap<>();
        this.proprietarios = new HashMap<>();
        this.listaVeiculos = new HashMap<>();
        createData(lidos);
    }

    public static List<String> readLinesWithBR(String fichtxt){
        List<String> linhas = new ArrayList<>();
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader(new FileReader(fichtxt));
            while((linha = inFile.readLine()) != null)
                linhas.add(linha);
        } catch (IOException e) {out.println(e);}
        return linhas;
    }

    private Proprietario makeProprietario(String linha){
        String[] partes = linha.split(",");
        Proprietario p = new Proprietario();

        p.setName(partes[0]);
        p.setPassword(partes[1]);
        p.setEmail(partes[2]);
        p.setAddress(partes[3]);

        return p;
    }

    private Cliente makeCliente(String linha){
        String[] partes = linha.split(",");
        Cliente c = new Cliente();
        Ponto p = null;

        c.setName(partes[0]);
        c.setPassword(partes[1]);
        c.setEmail(partes[2]);
        c.setAddress(partes[3]);
        p = new Ponto(Double.parseDouble(partes[4]), Double.parseDouble(partes[5]));
        c.setPosicaoI(p);

        return c;
    }

    private Veiculo makeVeiculo(String linha) throws PrintError{
        String[] partes = linha.split(",");
        Veiculo v; // se necessario meter um throw
        switch (partes[0]){
            case "Electrico":
                v = new Eletrico();
                v.setTipo("Eletrico");
                break;
            case "Gasolina":
                v = new Gasolina();
                v.setTipo("Gasolina");
                break;
            case "Hibrido":
                v = new Hibrido();
                v.setTipo("Hibrido");
                break;
            default:
                throw new PrintError("Tipo de veículo inválido");
        }

        Ponto p;

        v.setMarca(partes[1]);
        v.setID(partes[2]);
        Proprietario prop = this.proprietarios.get(partes[3]).clone();
        v.setProp(partes[3]);
        v.setVelocidade(Double.parseDouble(partes[4]));
        v.setPriceKm(Double.parseDouble(partes[5]));
        v.setConsumoKm(Double.parseDouble(partes[6]));
        v.setDepositoAtual(Double.parseDouble(partes[7]));
        v.setDepositoMax(Double.parseDouble(partes[7]));
        p = new Ponto(Double.parseDouble(partes[8]), Double.parseDouble(partes[9]));
        v.setPosicao(p);
        prop.addToFrota(v);
        this.proprietarios.put(partes[3],prop);
        return v;
    }

    private Aluguer makeAluguer(String linha, int ID){
        String[] partes = linha.split(",");
        Aluguer aluguer = new Aluguer();
        aluguer.setAluguerID(ID);
        aluguer.setClienteID(partes[0]);
        Ponto p = new Ponto(Double.parseDouble(partes[1]),Double.parseDouble(partes[2]));
        aluguer.setFimPercurso(p);

        return aluguer;
    }

    public int getNumClientes(){
        return this.clientes.size();
    }

    public int getNumCarros(){
        return this.listaVeiculos.size();
    }

    public int getNumProprietarios(){
        return this.proprietarios.size();
    }

    public void createData (List<String> logFile){
        String[] partes;
        int counter = 1;

        for (String i : logFile){
            partes = i.split(":");
            switch (partes[0]){
                case "NovoProp":
                    Proprietario p = makeProprietario(partes[1]);
                    this.proprietarios.put(p.getPassword(), p.clone());
                    break;
                case "NovoCliente":
                    Cliente c = makeCliente(partes[1]);
                    this.clientes.put(c.getPassword(), c.clone());
                    break;
                case "NovoCarro":
                    try {
                    Veiculo carro = makeVeiculo(partes[1]);
                    this.listaVeiculos.put(carro.getID(),carro.clone());
                    break;
                    }
                    catch(PrintError e) {
                        out.println(e.getMessage());
                    }
                case "Aluguer":
                    //Aluguer alug = makeAluguer(partes[1],counter);
//                    this.alugueres.add(alug);
                    break;
                case "Classificar":
                    //fazer qq coisa;
                    break;
            }
        }
    }

    public Map<String,Veiculo> getCarros() {
        return this.listaVeiculos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<String,Cliente> getClientes(){
        return this.clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<String,Proprietario> getProps(){
        return this.proprietarios.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public boolean verificaLogin(Integer tipo, String password, String email){
        boolean retValue;
        switch (tipo){
            case 1:
                retValue = this.clientes.containsKey(password);
                if(retValue && this.clientes.get(password).getEmail().equals(email)) {
                    retValue = true;
                }
                else {
                    retValue = false;
                }
                break;
            case 2:
                retValue = this.proprietarios.containsKey(password);
                if(retValue && this.proprietarios.get(password).getEmail().equals(email)) {
                    retValue = true;
                }
                else {
                    retValue = false;
                }
                break;
            default:
                retValue = false;
        }
        return retValue;
    }

    public Cliente getCliente(String password) {
        return this.clientes.get(password);
    }

    private void addVehicle(Veiculo v, String password) {
        Proprietario p = this.proprietarios.get(password).clone();
        p.addToFrota(v);
        this.proprietarios.put(password,p);
    }

    public Veiculo getCarro(String ID) throws PrintError{
        if(this.listaVeiculos.containsKey(ID)){
            return this.listaVeiculos.get(ID);
        }
        else {
            throw new PrintError("Não existe o carro pedido com a matrícula fornecida.");
        }
    }

    public void setClientCoordI(String client, Ponto nova) {
        Cliente cl = this.clientes.get(client).clone();
        cl.setPosicaoI(nova);
        this.clientes.put(client,cl);
    }

    public void setClientCoordF(String client, Ponto nova) {
        Cliente cl = this.clientes.get(client).clone();
        cl.setPosicaoF(nova);
        this.clientes.put(client,cl);
    }

    public Ponto getClienteCoordI(String id) {
        return this.clientes.get(id).getPosicaoI().clone();
    }

    public Ponto getClienteCoordF(String id) {
        return this.clientes.get(id).getPosicaoF().clone();
    }

    private Veiculo fetchClosestBy(String tipo, Cliente client) {
        double mincusto = Double.MAX_VALUE;
        Veiculo v = null;
        Ponto posF = client.getPosicaoF();
        Ponto posC;
        double pkm, custo,distancia;
        for(Veiculo x: this.listaVeiculos.values()) {
            posC = x.getPosicao();
            distancia = posF.distancia(posC);
            pkm = x.getPriceKm();
            custo = pkm*distancia;
            if(custo<mincusto) {

            }
        }
        return v;
    }
}
