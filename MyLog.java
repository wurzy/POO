import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class MyLog {
    private Map<String,Cliente> clientes;
    private Map<String,Proprietario> proprietarios;
    private Map<String,Veiculo> listaVeiculos; // MATRICULA - CARRO

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

    private Veiculo makeVeiculo(String linha){
        String[] partes = linha.split(",");
        Veiculo v = new Eletrico(); // se necessario meter um throw
        switch (partes[0]){
            case "Eletrico":
                v = new Eletrico();
                break;
            case "Gasolina":
                v = new Gasolina();
                break;
            case "Hibrido":
                v = new Hibrido();
                break;
        }

        Ponto p;

        v.setMarca(partes[1]);
        v.setID(partes[2]);

        v.setVelocidade(Double.parseDouble(partes[4]));
        v.setPriceKm(Double.parseDouble(partes[5]));
        v.setConsumoKm(Double.parseDouble(partes[6]));
        v.setDepositoAtual(Double.parseDouble(partes[7]));
        v.setDepositoMax(Double.parseDouble(partes[7]));
        p = new Ponto(Double.parseDouble(partes[8]), Double.parseDouble(partes[9]));
        v.setPosicao(p);

        return v;
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
                    Veiculo carro = makeVeiculo(partes[1]);
                    this.listaVeiculos.put(carro.getID(),carro.clone());
                    break;
                case "Aluguer":
                    //fazer qq coisa;
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
}
