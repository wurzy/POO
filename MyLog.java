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
        Veiculo v = null; // se necessario meter um throw
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

        Ponto p = null;

        v.setMarca(partes[1]);
        v.setID(partes[2]); // id == matricula (?)
        v.setVelocidade(Double.parseDouble(partes[4]));
        v.setPriceKm(Double.parseDouble(partes[5]));
        v.setConsumoKm(Double.parseDouble(partes[6]));
        v.setDepositoAtual(Double.parseDouble(partes[7]));
        p = new Ponto(Double.parseDouble(partes[8]), Double.parseDouble(partes[9]));
        v.setPosicao(p);

        return v;
    }

    public int getQts(){
        return this.clientes.size();
    }

    public void createData (List<String> logFile){
        String[] partes;

        for (String i : logFile){
            partes = i.split(":");
            switch (partes[0]){
                case "NovoProp":
                    Proprietario p = makeProprietario(partes[1]);
                    this.proprietarios.put(p.getEmail(), p.clone());
                    break;
                case "NovoCliente":
                    Cliente c = makeCliente(partes[1]);
                    this.clientes.put(c.getEmail(), c.clone());
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

    public boolean verificaLogin(String tipo, String password){
        boolean retValue;
        switch (tipo){
            case "Cliente":
                retValue = this.clientes.containsKey(password);
                break;
            case "Proprietario":
                retValue = this.proprietarios.containsKey(password);
                break;
            default:
                retValue = false;
        }
        return retValue;
    }
}
