import java.time.LocalDate;
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
    private int counter;

    public MyLog(){
        List<String> lidos = readLinesWithBR("logs.bak");
        this.clientes = new HashMap<>();
        this.proprietarios = new HashMap<>();
        this.listaVeiculos = new HashMap<>();
        this.alugueres = new TreeSet<>();
        this.counter = 1;
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
        p.setHistorico(new TreeSet<>());
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
                v.setTipo("Electrico");
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
        //System.out.println(v);
        return v;
    }

    private Aluguer makeAluguer(String linha, int ID){
        String[] partes = linha.split(",");
        Aluguer aluguer = new Aluguer();

        aluguer.setAluguerID(ID);
        aluguer.setClienteID(partes[0]);

        Ponto p = new Ponto(Double.parseDouble(partes[1]),Double.parseDouble(partes[2]));

        Cliente cx = this.clientes.get(partes[0]).clone();
        cx.setPosicaoF(p);
        this.clientes.put(partes[0],cx);

        aluguer.setFimPercurso(p);
        aluguer.setTipoVeiculo(partes[3]);
        aluguer.setTipo(partes[4]);

        Veiculo veic = new Eletrico();// = new Eletrico(this.listaVeiculos.get("CZ-73-82").clone());
        Cliente cl = this.clientes.get(partes[0]).clone();

        //out.println("veic\n" + veic);
        //out.println("x\n" + x);

        switch(partes[4]){
            case "MaisBarato":
                veic=rentCheapest(partes[0],partes[3]);
                break;
            case "MaisPerto":
                veic=rentClosest(partes[0],partes[3]);
                break;
            default:
                break;
        }

        aluguer.setVeiculoID(veic.getID());
        aluguer.setInicioPercurso(cl.getPosicaoI());
        aluguer.setPosInicialVeiculo(veic.getPosicao());
        aluguer.setPropID(veic.getProp());

        //dealt with later
        aluguer.setPreco(0);
        aluguer.setTempo(0);
        aluguer.setDate(LocalDate.of(1,1,1));
        addAluguer(partes[0],aluguer,veic);

        return aluguer;
    }

    public void addClassificacao(String linha) {
        String[] partes = linha.split(",");

        if(partes[0].contains("-")) {
            Veiculo v = this.listaVeiculos.get(partes[0]).clone();
            v.addClassificacao(Integer.parseInt(partes[1]));
            this.listaVeiculos.put(v.getID(),v);
        }

        else {
            if(this.proprietarios.containsKey(partes[0])){
                Proprietario p = this.proprietarios.get(partes[0]).clone();
                p.addClassificacao(Integer.parseInt(partes[1]));
                this.proprietarios.put(p.getPassword(),p);
            }
            else {
                Cliente cl = this.clientes.get(partes[0]).clone();
                cl.addClassificacao(Integer.parseInt(partes[1]));
                this.clientes.put(cl.getPassword(),cl);
            }
        }
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
                    try {
                    Veiculo carro = makeVeiculo(partes[1]);
                    this.listaVeiculos.put(carro.getID(),carro.clone());
                    break;
                    }
                    catch(PrintError e) {
                        out.println(e.getMessage());
                    }
                case "Aluguer":
                    Aluguer alug = makeAluguer(partes[1],this.counter);
                    this.counter++;
                    this.alugueres.add(alug);
                    break;
                case "Classificar":
                    addClassificacao(partes[1]);
                  //  out.println(this.proprietarios.get("463062725").getClassificacao());
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

    public Set<Aluguer> getAlugueres(){
        Set<Aluguer> set = new TreeSet<>();
        for(Aluguer l: this.alugueres) {
            set.add(l.clone());
        }
        return set;
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

    public Proprietario getProp(String password) {
        return this.proprietarios.get(password);
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

    public int getCounter(){
        return this.counter;
    }

    public void updateCounter(){
        this.counter++;
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

    public Set<Aluguer> getClienteHistorico(String id) {
        return this.clientes.get(id).getHistorico();
    }

    public Veiculo rentClosest(String nif, String tipo) {

       Cliente cliente = this.clientes.get(nif).clone();
       Ponto posF = cliente.getPosicaoF();
       Ponto posI = cliente.getPosicaoI();

       Veiculo aux = null;
       //Veiculo ret = null;

       double dist_carro, mindist = Double.MAX_VALUE,dist=0;

       for(Veiculo veiculo: this.listaVeiculos.values()) {
           Ponto posC = veiculo.getPosicao();
           dist_carro = posI.distancia(posC);
           dist = posC.distancia(posF);
           if(dist_carro<mindist && tipo.equals(veiculo.getTipo()) && veiculo.hasAutonomia(dist)) {
               mindist = dist_carro;
               if(veiculo instanceof Eletrico) {
                   aux = new Eletrico(veiculo);
                   //ret = new Eletrico(veiculo);
               }
               else if (veiculo instanceof Hibrido) {
                   //ret = new Hibrido(veiculo);
                   aux = new Hibrido(veiculo);
               }
               else {
                   //ret = new Gasolina(veiculo);
                   aux = new Gasolina(veiculo);
               }
           }
       }
       //out.println("carro inicio closest" + aux.getPosicao());
       /*
        if(aux!=null) {
           aux.setPosicao(posF);
           aux.updateAutonomia(dist);
           this.listaVeiculos.put(aux.getID(),aux);
       }
       */
        //out.println("carro fim closest" + aux.getPosicao());

     //   return ret;
        return aux;
    }

/*
    public Veiculo rentClosest(String nif) {

        Cliente cliente = this.clientes.get(nif).clone();
        Ponto posF = cliente.getPosicaoF();
        Ponto posI = cliente.getPosicaoI();

        Veiculo aux = null;

        double dist_carro, mindist = Double.MAX_VALUE,dist=0;

        for(Veiculo veiculo: this.listaVeiculos.values()) {
            Ponto posC = veiculo.getPosicao();
            dist_carro = posI.distancia(posC);
            dist = posC.distancia(posF);
            if(dist_carro<mindist  && veiculo.hasAutonomia(dist)) {
                mindist = dist_carro;
                if(veiculo instanceof Eletrico) {
                    aux = new Eletrico(veiculo);
                }
                else if (veiculo instanceof Hibrido) {
                    aux = new Hibrido(veiculo);
                }
                else {
                    aux = new Gasolina(veiculo);
                }
            }
        }
        //out.println("carro inicio closest" + aux.getPosicao());

        if(aux!=null) {
            aux.setPosicao(posF);
            aux.updateAutonomia(dist);
        }
        //out.println("carro fim closest" + aux.getPosicao());

        return aux;
    }
*/

    public Veiculo rentCheapest(String nif, String tipo) {
        double mincusto = Double.MAX_VALUE;
        Veiculo v = null;
        Cliente client = this.clientes.get(nif).clone();
        Ponto posF = client.getPosicaoF();
        Ponto posC;
       // Veiculo ret = null;
        double pkm, custo;
        double distancia=0;
        for(Veiculo x: this.listaVeiculos.values()) {
            posC = x.getPosicao();
            distancia = posC.distancia(posF);
            pkm = x.getPriceKm();
            custo = pkm*distancia;
            if(custo < mincusto && tipo.equals(x.getTipo()) && x.hasAutonomia(distancia)) {
                mincusto = custo;
                if(x instanceof Eletrico) {
                    v = new Eletrico(x);
                   // ret = new Eletrico(x);
                }
                else if (x instanceof Hibrido) {
                    v = new Hibrido(x);
                    //ret = new Hibrido(x);
                }
                else {
                    v = new Gasolina(x);
                   // ret = new Gasolina(x);
                }
            }
        }

       // out.println("inicio cheapest posicao" + v.getPosicao());

       // if(v!=null) {
            //v.setPosicao(posF);
            //v.updateAutonomia(distancia);
            //this.listaVeiculos.put(v.getID(),v);
       // }

        //out.println("carro fim cheapeast" + v.getPosicao());

        //return ret;
        return v;
    }

    public Veiculo rentCheapest(String nif, double raio) {
        double mincusto = Double.MAX_VALUE;
        Veiculo v = null;
        Cliente client = this.clientes.get(nif).clone();
        Ponto posI = client.getPosicaoI();
        Ponto posF = client.getPosicaoF();
        Ponto posC;
       // Veiculo ret=null;
        double pkm, custo;
        double distancia=0,raio_2;
        for(Veiculo x: this.listaVeiculos.values()) {
            posC = x.getPosicao();
            distancia = posC.distancia(posF);
            pkm = x.getPriceKm();
            custo = pkm*distancia;
            raio_2 = posC.distancia(posI);
            if(custo < mincusto && x.hasAutonomia(distancia) && raio_2<=raio) {
                mincusto = custo;
                if(x instanceof Eletrico) {
                    v = new Eletrico(x);
                  //  ret = new Eletrico(x);
                }
                else if (x instanceof Hibrido) {
                    v = new Hibrido(x);
                //    ret = new Hibrido(x);
                }
                else {
                    v = new Gasolina(x);
                   // ret = new Gasolina(x);
                }
            }
        }

        // out.println("inicio cheapest posicao" + v.getPosicao());
/*
        if(v!=null) {
            v.setPosicao(posF);
            v.updateAutonomia(distancia);
            this.listaVeiculos.put(v.getID(),v);
        }

        //out.println("carro fim cheapeast" + v.getPosicao());

        return ret;*/
        return v;
    }

    public Veiculo rentID(String matricula) throws PrintError{
        if(this.listaVeiculos.containsKey(matricula)) {
            return this.listaVeiculos.get(matricula).clone();
        }
        else {
            return null;
        }
    }

    public void addToQueue(String prop, Aluguer al) {
        Proprietario pr = this.proprietarios.get(prop).clone();
        pr.addToQueue(al);
        this.proprietarios.put(prop,pr);
    }

    public void addAluguer(String nif, Aluguer al, Veiculo d) {
        Cliente cl = this.clientes.get(nif).clone();
        Veiculo x = d.clone();

        al.setPreco(x.calculaTarifa(x.getPosicao(),al.getFimPercurso()));
        al.setDate(LocalDate.now());
        al.setTempo(x.calculaTempo(x.getPosicao(),al.getFimPercurso()));

        x.setPosicao(al.getFimPercurso());
        x.updateAutonomia(al.distancia());
        x.addAluguer(al);
        this.listaVeiculos.put(x.getID(),x);

        Proprietario p = this.proprietarios.get(x.getProp()).clone();
        p.updateFrota(x);
        p.addAluguer(al);
        this.proprietarios.put(p.getPassword(),p);

        cl.addAluguer(al);
        cl.setPosicaoI(al.getFimPercurso());
        this.clientes.put(nif,cl);

        this.alugueres.add(al.clone());
    }

    public void addAluguerQueue(Aluguer al1){
        Aluguer al = al1.clone();
        Veiculo v = this.listaVeiculos.get(al.getVeiculoID()).clone();

        al.setPreco(v.calculaTarifa(v.getPosicao(),al.getFimPercurso()));
        al.setDate(LocalDate.now());
        al.setTempo(v.calculaTempo(v.getPosicao(),al.getFimPercurso()));

        Proprietario p = this.proprietarios.get(al.getPropID()).clone();
        p.addToQueue(al);
        this.proprietarios.put(p.getPassword(),p);
    }

    public void confirmedAluguer(Aluguer al1) {
        Aluguer al = al1.clone();
        al.setDate(LocalDate.now());

        Cliente cl = this.clientes.get(al.getClienteID()).clone();
        Proprietario p = this.proprietarios.get(al.getPropID()).clone();
        Veiculo v = this.listaVeiculos.get(al.getVeiculoID()).clone();

        cl.setPosicaoI(al.getFimPercurso());

        v.updateAutonomia(al.distancia());
        v.setPosicao(al.getFimPercurso());

        cl.addAluguer(al);
        v.addAluguer(al);
        p.addAluguer(al);
        p.updateFrota(v);

        this.proprietarios.put(p.getPassword(),p);
        this.clientes.put(cl.getPassword(),cl);
        this.listaVeiculos.put(v.getID(),v);
        this.alugueres.add(al1);

        //manda para file aqui
    }

    public List<Veiculo> getVeiculosFill(String nif) throws PrintError {
        List<Veiculo> ret = new ArrayList<>();
        for(Veiculo v: this.proprietarios.get(nif).getFrota().values()) {
            if(!v.isFull()) {
                ret.add(v.clone());
            }
        }
        if(ret.size()==0) {
            throw new PrintError("Todos estão com depósito cheio.");
        }
        else {
            return ret;
        }
    }

    public void updateCarro(Veiculo v) {
        Proprietario p = this.proprietarios.get(v.getProp()).clone();
        p.updateFrota(v);
        this.proprietarios.put(p.getPassword(),p);
        this.listaVeiculos.put(v.getID(),v);
    }

    public List<Veiculo> getVeiculos(String nif) throws PrintError{
        List<Veiculo> ret = new ArrayList<>();
        for(Veiculo v: this.proprietarios.get(nif).getFrota().values()) {
            ret.add(v.clone());
        }
        if(ret.size()==0) {
            throw new PrintError("Não possuo veículos registados.");
        }
        else {
            return ret;
        }
    }

    public void updateFrota(String nif, List<Veiculo> frota, String matricula) throws PrintError{
        for(Veiculo v: frota) {
            if(v.getID().equals(matricula)) {
                Proprietario p = this.proprietarios.get(nif).clone();
                p.removeCarro(v);
                this.proprietarios.put(nif,p);
                return;
            }
        }
        throw new PrintError("Eu não tenho este veículo registado.");
    }

    //this.menuLogin.getPassword(),posicaoCreate,matricVeic,marcaVeic,velM,pKm,cKm,depMax

    public void createVeiculo(String nif, String tipo, Ponto p,  String matricula, String marca, double vel, double pkm, double ckm, double dep){
        Veiculo v;
        switch(tipo) {
            case "Hibrido":
                v = new Hibrido();
                break;
            case "Electrico":
                v = new Eletrico();
                break;
            case "Gasolina":
                v = new Gasolina();
                break;
            default:
                v=null;
                break;
        }
        v.setID(matricula);
        v.setMarca(marca);
        v.setProp(nif);
        v.setPosicao(p);
        v.setTipo(tipo);
        v.setVelocidade(vel);
        v.setPriceKm(pkm);
        v.setConsumoKm(ckm);
        v.setHistorico(new TreeSet<>());
        v.setClassificacoes(new ArrayList<>());
        v.setDepositoMax(dep);
        v.setDepositoAtual(dep);

        this.listaVeiculos.put(matricula,v);
        Proprietario prop= this.proprietarios.get(nif).clone();
        prop.addToFrota(v);
        this.proprietarios.put(nif,prop);
    }

    public void isInQueue(String nif, int id) throws PrintError{
        Proprietario p = this.proprietarios.get(nif).clone();
        if(p.isInQueue(id)) {
            return;
        }
        else {
            throw new PrintError("Não existe este aluguer na minha lista de espera.");
        }
    }

    public boolean decideAluguer(String nif, int id, String yn) {
        Proprietario p = this.proprietarios.get(nif).clone();
        switch(yn) {
            case "y":
                confirmedAluguer(p.getFromQueue(id));
                p = this.proprietarios.get(nif).clone();
                p.removeFromQueue(id);
                this.proprietarios.put(nif,p);
                return true;
            case "n":
                p.removeFromQueue(id);
                this.proprietarios.put(nif,p);
                return false;
            default:
                return false;
        }
    }

    public void addClassificacao(String id, int rating, String tipo){
        switch(tipo) {
            case "Veiculo":
                Veiculo v = this.listaVeiculos.get(id).clone();
                v.addClassificacao(rating);
                this.listaVeiculos.put(id,v);
                break;
            case "Cliente":
                Cliente cl = this.clientes.get(id).clone();
                cl.addClassificacao(rating);
                this.clientes.put(id,cl);
                break;
            case "Prop":
                Proprietario prop = this.proprietarios.get(id).clone();
                prop.addClassificacao(rating);
                this.proprietarios.put(id,prop);
                break;
            default:
                break;
        }

        //mandar para file.
    }
}
