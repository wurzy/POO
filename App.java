import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class App implements Serializable{

    private MyLog logNegocio;
    private Menu menuCliente,menuProprietario,menuSign,menuSignUp;
    private MenuLogin menuLogin;
    private Randomizer estado = new Randomizer();

    public void guardaEstado() throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream("Logs.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(logNegocio);
        oos.flush();
        oos.close();
    }

    public void carregaEstado() throws FileNotFoundException,IOException,ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Logs.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        logNegocio = (MyLog) ois.readObject();
        ois.close();
    }

    public static void main(String[] args){
        new App().run();
    }

    private App(){
        String[] cliente = {"Alugar carro mais perto",
                "Alugar o carro mais barato",
                "Alugar o carro mais barato num raio",
                "Alugar um carro em específico",
                "Alterar a minha localização",
                "Alterar o meu destino",
                "Ver o meu histórico de alugueres aceites",
                "Ver a minha localização atual",
                "Ver o meu destino atual",
                "Ver minha informação pessoal"};
        String[] props = {"Abastacer um dos meus veículos",
                "Alterar preço/km de um dos meus veículos",
                "Aceitar/Rejeitar um aluguer",
                "Inserir nova viatura para aluguer",
                "Remover uma viatura de aluguer",
                "Ver o meu histórico de alugueres aceites",
                "Ver a minha frota atual de veículos",
                "Ver a minha informação pessoal"};

        String[] sign = {"Sign-in",
                        "Sign-up",
                        "Top 10"};

        String[] login = {"Cliente", "Proprietário"};

        String[] signup = {"Novo cliente",
                "Novo proprietário"};
        try {
            carregaEstado();
        }
        catch(FileNotFoundException e) {
            this.logNegocio = new MyLog();
        }
        catch(ClassNotFoundException | IOException e1) {
            System.out.println(e1.getMessage());
            return;
        }
        this.menuSign = new Menu(sign);
        this.menuLogin = new MenuLogin(login);
        this.menuCliente = new Menu(cliente);
        this.menuProprietario = new Menu(props);
        this.menuSignUp = new Menu(signup);
    }

    private void run(){
        System.out.println("Clima de hoje: " + this.estado.getClima());
        do {
            this.menuSign.executa();
            switch(menuSign.getOp()) {
                case 1:
                    do {
                        this.menuLogin.executaReader();
                        if (this.menuLogin.getOp() == 0) {
                            break;
                        }
                        do {
                            this.menuLogin.executaParametros();
                        } while (!this.logNegocio.verificaLogin(this.menuLogin.getOp(), this.menuLogin.getPassword(), this.menuLogin.getEmail()));

                        switch (menuLogin.getOp()) {
                            case 1:
                                do {
                                    String clPW = this.menuLogin.getPassword();
                                    this.menuCliente.executa();
                                    switch (menuCliente.getOp()) {
                                        case 1:
                                            alugarPerto(clPW);
                                            break;
                                        case 2:
                                            alugarCusto(clPW);
                                            break;
                                        case 3:
                                            alugarRaio(clPW);
                                            break;
                                        case 4:
                                            alugarEspecifico(clPW);
                                            break;
                                        case 5:
                                            alterarLocalizacao(clPW);
                                            break;
                                        case 6:
                                            alteraDestino(clPW);
                                            break;
                                        case 7:
                                            buscaHistoricoCliente(logNegocio.getClienteHistorico(clPW));
                                            break;
                                        case 8:
                                            posAtual(clPW);
                                            break;
                                        case 9:
                                            destinoAtual(clPW);
                                            break;
                                        case 10:
                                            buscaPersonalCl(clPW);
                                            break;
                                    }
                                } while (this.menuCliente.getOp() != 0);
                                System.out.println("Voltando ao menu de login...");
                                break;
                            case 2:
                                do {
                                    String propPW = this.menuLogin.getPassword();
                                    this.menuProprietario.executa();
                                    switch (menuProprietario.getOp()) {
                                        case 1:
                                            encherDeposito(propPW);
                                            break;
                                        case 2:
                                            mudaPKM(propPW);
                                            break;
                                        case 3:
                                            acceptReject(propPW);
                                            break;
                                        case 4:
                                            insertCarro(propPW);
                                            break;
                                        case 5:
                                            removeCarro(propPW);
                                            break;
                                        case 6:
                                            buscaHistoricoProp(this.logNegocio.getProp(propPW).getHistorico());
                                            break;
                                        case 7:
                                            buscaFrota(propPW);
                                            break;
                                        case 8:
                                            buscaPersonal(logNegocio.getProp(propPW));
                                            break;
                                    }
                                } while (this.menuProprietario.getOp() != 0);
                                System.out.println("Voltando ao menu de login...");
                                break;
                        }
                    } while(this.menuLogin.getOp()!=0);
                    System.out.println("Voltando ao menu de sign-in/sign-up...");
                    break;
                case 2:
                    do {
                        this.menuSignUp.executa();
                        switch(menuSignUp.getOp()) {
                            case 1:
                                createNewUser(0);
                                break;
                            case 2:
                                createNewUser(1);
                                break;
                        }
                    }while(this.menuSignUp.getOp()!=0);
                    System.out.println("Voltando ao menu de sign-in/sign-up...");
                    break;
                case 3:
                    top10();
                    System.out.println("Voltando ao menu de sign-in/sign-up...");
                    break;

            }
        }while(this.menuSign.getOp()!=0);
        save();
        System.out.println("Saindo do programa...");
    }

    private Veiculo rentID(List<Veiculo> cars, String ID) throws PrintError {
            for(Veiculo ret: cars) {
                if(ret.getID().equals(ID)) {
                    return ret.clone();
                }
            }
            throw new PrintError("Não é possivel obter o carro pedido.");
    }

    private List<Veiculo> availableCars(Cliente client, Map<String,Veiculo> cars, double d) throws PrintError{
        Ponto p = client.getPosicaoI();
        Ponto pf;
        double dist;
        List<Veiculo> ret = new ArrayList<>();
        for(Veiculo v: cars.values()) {
            pf = v.getPosicao();
            dist = pf.distancia(p);
            if(dist<=d) {
                ret.add(v.clone());
            }
        }
       if(ret.isEmpty()) {
           throw new PrintError("Não é possível encontrar um carro no raio desejado.");
       }
       else {
           return ret;
       }
    }

    private void alterarPKM(Map<String,Veiculo> cars, String ID, double pkm) {
        Veiculo x = cars.get(ID).clone();
        x.setPriceKm(pkm);
        cars.put(ID,x);
    }

    private void fillDeposit(Map<String,Veiculo> cars, String ID) {
        Veiculo x = cars.get(ID).clone();
        x.setDepositoAtual(x.getDepositoMax());
        cars.put(ID,x);
    }


    private void createNewUser(int x){
        String nome,morada;
        int nif;
        LocalDate birthday;

        do{
            System.out.print("Nome: ");
            nome = this.menuLogin.leMarca();
        }while(nome==null);

        do {
            System.out.print("NIF: ");
            nif = this.menuLogin.leInt();
        }while(nif==-1);

        try {
            this.logNegocio.existsProp(Integer.toString(nif));
            this.logNegocio.existsCliente(Integer.toString(nif));
            System.out.println("Ok. NIF válido.");
            String email = nif + "@gmail.com";
            System.out.println("E-mail por defeito: " + email);
            System.out.println("Password por defeito: " + nif);
            System.out.println("Inserir data de nascimento: ");
            birthday = this.menuLogin.lerData();

            do {
                System.out.print("Morada: ");
                morada = this.menuLogin.leMarca();
            }while(morada==null);

            switch(x) {
                // 0-> cliente; 1 -> proprietario
                case 0:
                    System.out.println("Localização atual:");
                    Ponto posicaoI = this.menuLogin.lerCoordenada();
                    Cliente cl = new Cliente(email,nome,Integer.toString(nif),morada,birthday,posicaoI);
                    this.logNegocio.signCliente(cl);
                    System.out.println("Novo cliente gravado com sucesso.");
                    break;
                case 1:
                    Proprietario p = new Proprietario(email,nome,Integer.toString(nif),morada);
                    p.setBirthday(birthday);
                    this.logNegocio.signProp(p);
                    System.out.println("Novo proprietário gravado com sucesso.");
                    break;
                default:
                    break;
            }
        }
        catch(PrintError e) {
            System.out.println("Já existe registado uma pessoa com esse NIF");
            return;
        }
    }

    private void buscaFrota(String password) {
        System.out.println("A minha frota atual de veículos é: \n");
        try{
            List<Veiculo> frotaAtual = logNegocio.getVeiculos(password);
            System.out.println(frotaAtual);
        }
        catch(PrintError e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscaPersonal(Proprietario p){
        System.out.println("A minha informação pessoal é:\n");
        System.out.println(p);
    }

    private void buscaHistoricoProp(Set<Aluguer> set) {
        System.out.println("Esta é a minha lista de alugueres aceites: ");
        System.out.println(set);
    }

    private void removeCarro(String password) {
        String toremove;
        System.out.println("A minha frota atual de veículos é: \n");
        List<Veiculo> frotaRemove;
        try{
            frotaRemove = logNegocio.getVeiculos(password);
            System.out.println(frotaRemove);
        }
        catch(PrintError e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("O veículo para remover é: \n");

        do {
            System.out.print("Matrícula: ");
            toremove = menuLogin.leString();
        }while(toremove==null);

        try {
            this.logNegocio.updateFrota(password,frotaRemove,toremove);
            System.out.println("Ok. Removido da minha frota.");
        }
        catch(PrintError e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void insertCarro(String password) {
        System.out.println("Inserir o tipo de Veículo: ");
        String tipoVeic, matricVeic,marcaVeic;
        Ponto posicaoCreate;
        double velM,pKm,cKm,depMax;
        do {
            System.out.print("Tipo: ");
            tipoVeic = this.menuLogin.lerTipo();
        }while(tipoVeic==null);
        do{
            posicaoCreate=this.menuLogin.lerCoordenada();
        }while(posicaoCreate==null);
        do {
            System.out.print("Matricula: ");
            matricVeic=this.menuLogin.leMatricula();
        }while(matricVeic==null);

        try {
            Veiculo auxVei = this.logNegocio.getCarro(matricVeic);
            System.out.println("Já existe um veículo com esta matrícula.");
            return;
        }
        catch(PrintError e) {
            do {
                System.out.print("Marca: ");
                marcaVeic = this.menuLogin.leMarca();
            }while(marcaVeic==null);
            do{
                System.out.print("Velocidade média: ");
                velM = menuLogin.lerDouble();
            }while(velM==-1);
            do{
                System.out.print("Preço/km: ");
                pKm = menuLogin.lerDouble();
            }while(pKm==-1);
            do{
                System.out.print("Consumo/km: ");
                cKm = menuLogin.lerDouble();
            }while(cKm==-1);
            do{
                System.out.print("Autonomia: ");
                depMax = menuLogin.lerDouble();
            }while(depMax==-1);
            this.logNegocio.createVeiculo(password,tipoVeic,posicaoCreate,matricVeic,marcaVeic,velM,pKm,cKm,depMax);
            System.out.println("Ok. Veículo criado.");

        }
    }

    private void acceptReject(String password) {
        int leitInt,ratecl;
        String yesNo;
        System.out.println("Esta é a lista de Alugueres: ");
        System.out.println(this.logNegocio.getProp(password).getQueue());
        if(this.logNegocio.getProp(password).getQueue().isEmpty()){
            System.out.println("Não há alugueres para aceitar/recusar.");
            return;
        }
        do{
            System.out.print("Inserir ID do aluguer: ");
            leitInt = this.menuLogin.leInt();
        }while(leitInt==-1);
        try {
            this.logNegocio.isInQueue(password,leitInt);
            String vx = this.logNegocio.getProp(password).getFromQueue(leitInt).getVeiculoID();
            Veiculo veic = this.logNegocio.getCarro(vx);
            Ponto posV = veic.getPosicao();
            String claux = this.logNegocio.getProp(password).getFromQueue(leitInt).getClienteID();
            Cliente cla = this.logNegocio.getCliente(claux);
            Ponto posC = cla.getPosicaoI();
            double tempo = posC.distancia(posV)/4;
            tempo = (double) Math.round(tempo*100)/100;
            System.out.println("O cliente chega ao veículo em: " + tempo + " h.");
            do{
                System.out.print("Aceitar? [y/n]: ");
                yesNo = this.menuLogin.leYesNo();
            }while(yesNo==null);
            Aluguer auxAluguer = logNegocio.getProp(password).getFromQueue(leitInt);
            boolean decided = this.logNegocio.decideAluguer(password,leitInt,yesNo);
            if(decided) {
                System.out.println("Estado que o veículo ficou: " + this.logNegocio.getAluguer(leitInt).getRandomizer().getVeiculo());
                do{
                    System.out.print("Classificação do Cliente (0-100): ");
                    ratecl=this.menuLogin.leInt();
                }while(ratecl==-1);
                this.logNegocio.addClassificacao(auxAluguer.getClienteID(),ratecl,"Cliente");
            }
        }
        catch(PrintError e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void mudaPKM(String password) {
        System.out.println("Alterar o preço/km de: ");
        String matr2;
        double novopkm;
        List<Veiculo> frotaPreco;
        try {
            frotaPreco = logNegocio.getVeiculos(password);
            System.out.println(frotaPreco);
            System.out.println("Alterar o preço de:");
        }
        catch (PrintError e) {
            System.out.println(e.getMessage());
            return;
        }
        do {
            System.out.print("Matrícula: ");
            matr2 = menuLogin.leString();
        }while(matr2==null);

        try {
            Veiculo vxx = rentID(frotaPreco,matr2);
            do{
                System.out.print("Novo preço/km: ");
                novopkm = menuLogin.lerDouble();
            }while(novopkm==-1);
            vxx.setPriceKm(novopkm);
            logNegocio.updateCarro(vxx);
            System.out.println("Ok. Alterado o novo preco/km.");
        }
        catch (PrintError e) {
            System.out.println(e.getMessage());
        }
    }

    private void encherDeposito(String password) {
        String matr1;
        try {
            List<Veiculo> frotaFill = logNegocio.getVeiculosFill(password);
            System.out.println(frotaFill);
            System.out.println("Encher o depósito de: ");
        }
        catch (PrintError e) {
            System.out.println(e.getMessage());
            return;
        }
        do {
            System.out.print("Matrícula: ");
            matr1 = menuLogin.leString();
        }while(matr1==null);

        try {
            Veiculo v = logNegocio.getCarro(matr1).clone();
            v.fillDeposito();
            logNegocio.updateCarro(v);
            System.out.println("Ok. Depósito do carro está agora cheio.");
        }
        catch(PrintError e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscaPersonalCl(String password) {
        System.out.println("A minha informação pessoal é:\n");
        System.out.println(logNegocio.getCliente(password));
    }

    private void destinoAtual(String password) {
        System.out.println("O meu destino atual é: \n");
        System.out.println(logNegocio.getCliente(password).getPosicaoF());
    }

    private void posAtual(String password) {
        System.out.println("A minha posição atual é: \n");
        System.out.println(logNegocio.getCliente(password).getPosicaoI());
    }

    private void buscaHistoricoCliente(Set<Aluguer> set) {
        System.out.println("O meu histórico de alugueres é:\n");
        System.out.println(set);
    }

    private void alteraDestino(String password) {
        System.out.println("O destino atual é: " + logNegocio.getClienteCoordF(password));
        System.out.println("Inserir novas coordenadas: ");
        Ponto outroPonto;
        do {
            outroPonto=menuLogin.lerCoordenada();
        } while(outroPonto==null);
        logNegocio.setClientCoordF(password,outroPonto);
        System.out.println("Ok. Destino atual: " + outroPonto);
    }

    private void alterarLocalizacao(String password) {
        System.out.println("A localização atual é: " + logNegocio.getClienteCoordI(password));
        System.out.println("Inserir novas coordenadas: ");
        Ponto novoPonto;
        do {
            novoPonto=menuLogin.lerCoordenada();
        } while(novoPonto==null);
        logNegocio.setClientCoordI(password,novoPonto);
        System.out.println("Ok. Localização atual: " + novoPonto);
    }

    private void alugarEspecifico(String password) {
        double d2;
        String lido;
        int rate4;
        System.out.println("Aqui estão os carros todos que existem num raio:");
        do{
            System.out.print("Raio = ");
            d2 = menuLogin.lerDouble();
        }while (d2==-1);
        try {
            List<Veiculo> ret = availableCars(logNegocio.getCliente(password),logNegocio.getCarros(),d2);
            System.out.println(ret);
            System.out.println("Insira a matrícula do carro que deseja alugar: ");
            do {
                System.out.print("Matrícula: ");
                lido = menuLogin.leString();
            }while(lido==null);
            System.out.println();
            try {
                Veiculo query4 = rentID(ret,lido);
                System.out.println("Dentre a lista de carros foi alugado:\n");
                System.out.println(query4);
                Aluguer theCheap4 = new Aluguer();
                theCheap4.setAluguerID(this.logNegocio.getCounter());
                this.logNegocio.updateCounter();
                theCheap4.setVeiculoID(query4.getID());
                theCheap4.setClienteID(password);
                theCheap4.setPropID(query4.getProp());
                theCheap4.setTipo("MaisBarato");
                theCheap4.setTipoVeiculo(query4.getTipo());
                theCheap4.setInicioPercurso(this.logNegocio.getCliente(password).getPosicaoI());
                theCheap4.setFimPercurso(this.logNegocio.getCliente(password).getPosicaoF());
                theCheap4.setPosInicialVeiculo(query4.getPosicao());
                do{
                    System.out.print("Classificação do proprietário (0-100): ");
                    rate4=this.menuLogin.leInt();
                }while(rate4==-1);
                this.logNegocio.addClassificacao(query4.getProp(),rate4,"Prop");

                do{
                    System.out.print("Classificação do carro (0-100): ");
                    rate4=this.menuLogin.leInt();
                }while(rate4==-1);
                this.logNegocio.addClassificacao(query4.getID(),rate4,"Veiculo");
                //this.logNegocio.addAluguer(this.menuLogin.getPassword(),theCheap4,query4);
                this.logNegocio.addAluguerQueue(theCheap4,this.estado.getClima());
            }
            catch (PrintError e){
                System.out.println(e.getMessage());
            }
        }
        catch (PrintError e) {
            System.out.println(e.getMessage());
        }
    }

    private void alugarRaio(String password) {
        System.out.println("Inserir raio máximo até ao carro:\n");
        double d;
        int rate3;
        do{
            System.out.print("Raio = ");
            d=menuLogin.lerDouble();
        }while(d==-1);
        System.out.println("A minha posição atual é: " + this.logNegocio.getCliente(password).getPosicaoI());
        Veiculo raioCheap = logNegocio.rentCheapest(password,d);
        if(raioCheap!=null) {
            double disttotal = raioCheap.getPosicao().distancia(this.logNegocio.getClienteCoordI(password));
            System.out.println("Alugado o veículo mais barato, com distância de " + (double) Math.round(disttotal*100)/100 + "m.\n");
            System.out.println(raioCheap);
            Aluguer theCheap3 = new Aluguer();
            theCheap3.setAluguerID(this.logNegocio.getCounter());
            this.logNegocio.updateCounter();
            theCheap3.setVeiculoID(raioCheap.getID());
            theCheap3.setClienteID(password);
            theCheap3.setPropID(raioCheap.getProp());
            theCheap3.setTipo("MaisBarato");
            theCheap3.setTipoVeiculo(raioCheap.getTipo());
            theCheap3.setInicioPercurso(this.logNegocio.getCliente(password).getPosicaoI());
            theCheap3.setFimPercurso(this.logNegocio.getCliente(password).getPosicaoF());
            theCheap3.setPosInicialVeiculo(raioCheap.getPosicao());
            do{
                System.out.print("Classificação do proprietário (0-100): ");
                rate3=this.menuLogin.leInt();
            }while(rate3==-1);
            this.logNegocio.addClassificacao(raioCheap.getProp(),rate3,"Prop");

            do{
                System.out.print("Classificação do carro (0-100): ");
                rate3=this.menuLogin.leInt();
            }while(rate3==-1);
            this.logNegocio.addClassificacao(raioCheap.getID(),rate3,"Veiculo");
            //this.logNegocio.addAluguer(this.menuLogin.getPassword(),theCheap3,raioCheap);
            this.logNegocio.addAluguerQueue(theCheap3,this.estado.getClima());
        }
        else {
            System.out.println("Não há nenhum veículo nesse raio.");
        }
    }

    private void alugarCusto(String password) {
        String tipoC;
        int rateCheap2;
        System.out.println("Inserir o tipo de Carro: Gasolina | Hibrido | Electrico");
        do{
            System.out.print("Tipo: ");
            tipoC = menuLogin.lerTipo();
        }while (tipoC==null);
        Veiculo cheap = logNegocio.rentCheapest(password,tipoC);
        System.out.println("O meu destino é: "+ this.logNegocio.getCliente(password).getPosicaoF());
        System.out.println("Alugado o carro seguinte, com custo de " + cheap.calculaTarifa(this.logNegocio.getClienteCoordF(password),cheap.getPosicao()) +" €.\n");
        System.out.println(cheap!=null?cheap:"Não existe nenhum carro com estas condições.");
        if(cheap!=null) {
            Aluguer theCheap2 = new Aluguer();
            theCheap2.setAluguerID(this.logNegocio.getCounter());
            this.logNegocio.updateCounter();
            theCheap2.setVeiculoID(cheap.getID());
            theCheap2.setClienteID(password);
            theCheap2.setPropID(cheap.getProp());
            theCheap2.setTipo("MaisBarato");
            theCheap2.setTipoVeiculo(cheap.getTipo());
            theCheap2.setInicioPercurso(this.logNegocio.getCliente(password).getPosicaoI());
            theCheap2.setFimPercurso(this.logNegocio.getCliente(password).getPosicaoF());
            theCheap2.setPosInicialVeiculo(cheap.getPosicao());
            do{
                System.out.print("Classificação do proprietário (0-100): ");
                rateCheap2=this.menuLogin.leInt();
            }while(rateCheap2==-1);
            this.logNegocio.addClassificacao(cheap.getProp(),rateCheap2,"Prop");
            do{
                System.out.print("Classificação do carro (0-100): ");
                rateCheap2=this.menuLogin.leInt();
            }while(rateCheap2==-1);
            this.logNegocio.addClassificacao(cheap.getID(),rateCheap2,"Veiculo");
            // this.logNegocio.addAluguer(this.menuLogin.getPassword(),theCheap2,cheap);
            this.logNegocio.addAluguerQueue(theCheap2,this.estado.getClima());
        }
    }

    private void alugarPerto(String password) {
        String tipo;
        int rateCheap;
        System.out.println("Inserir o tipo de Carro: Gasolina | Hibrido | Electrico");
        do{
            System.out.print("Tipo: ");
            tipo = menuLogin.lerTipo();
        }while (tipo==null);
        System.out.println("A minha posição atual é: " + this.logNegocio.getCliente(password).getPosicaoI());
        System.out.println("Alugado o carro mais perto:\n");
        Veiculo cheapest = this.logNegocio.rentClosest(password,tipo);
        System.out.println(cheapest!=null?cheapest:"Não existe nenhum carro com estas condições.");
        if(cheapest!=null) {
            Aluguer theCheap = new Aluguer();
            theCheap.setAluguerID(this.logNegocio.getCounter());
            this.logNegocio.updateCounter();
            theCheap.setVeiculoID(cheapest.getID());
            theCheap.setClienteID(password);
            theCheap.setPropID(cheapest.getProp());
            theCheap.setTipo("MaisPerto");
            theCheap.setTipoVeiculo(cheapest.getTipo());
            theCheap.setInicioPercurso(this.logNegocio.getCliente(password).getPosicaoI());
            theCheap.setFimPercurso(this.logNegocio.getCliente(password).getPosicaoF());
            theCheap.setPosInicialVeiculo(cheapest.getPosicao());
            //this.logNegocio.addAluguer(this.menuLogin.getPassword(),theCheap,cheapest);
            do{
                System.out.print("Classificação do proprietário (0-100): ");
                rateCheap=this.menuLogin.leInt();
            }while(rateCheap==-1);
            this.logNegocio.addClassificacao(cheapest.getProp(),rateCheap,"Prop");
            do{
                System.out.print("Classificação do veículo (0-100): ");
                rateCheap=this.menuLogin.leInt();
            }while(rateCheap==-1);
            this.logNegocio.addClassificacao(cheapest.getID(),rateCheap,"Veiculo");
            this.logNegocio.addAluguerQueue(theCheap,this.estado.getClima());
        }
    }

    private void top10(){
        int i = 0;
        System.out.println("Os 10 clientes que mais compraram em toda a aplicação foram:\n");
        List<Cliente> ret = this.logNegocio.fetchTop10();
        for(Cliente l: ret) {
                System.out.print("["+ (i+1) + "] Lugar: ");
                System.out.println( l.getHistorico().size() + " alugueres.\n");
                System.out.println(l);
                i++;
        }
    }

    private void save(){
        try {
            guardaEstado();
        }
        catch(IOException e) {
            System.out.println("Não foi possível escrever no ficheiro.");
        }
    }
}
