import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class App {
    private MyLog logNegocio = new MyLog();
    private Menu menuCliente,menuProprietario,menuSign;
    private MenuLogin menuLogin;

    public static void main(String[] args){
        new App().run();
    }

    private void run2(){
        List<Veiculo> ve = this.logNegocio.getCarros().values().stream().collect(Collectors.toList());
        System.out.println(ve);
    }
    private App(){
        String[] cliente = {"Alugar carro mais perto",
                "Alugar o carro mais barato",
                "Alugar o carro mais barato num raio",
                "Alugar um carro em específico",
                "Alugar um carro com a autonomia desejada",
                "Alterar a minha localização",
                "Alterar o meu destino"};
        String[] props = {"Disponibilizar um veículo para aluguer",
                "Abastacer um dos meus veículos",
                "Alterar preço/km de um dos meus veículos",
                "Aceitar/Rejeitar um aluguer",
                "Registar o custo da viagem"
                };

        String[] sign = {"Sign-in",
                        "Sign-up"};

        String[] login = {"Cliente", "Proprietário"};
        this.menuSign = new Menu(sign);
        this.menuLogin = new MenuLogin(login);
        this.menuCliente = new Menu(cliente);
        this.menuProprietario = new Menu(props);
    }

    private void run(){
        /*
        do{
           if(!this.menuLogin.executaLogin()) break;
           System.out.println("opcao " + this.menuLogin.getTipo());
        } while(!this.logNegocio.verificaLogin(this.menuLogin.getTipo(),this.menuLogin.getPassword(),this.menuLogin.getEmail()));
        */
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
                                    this.menuCliente.executa();
                                    switch (menuCliente.getOp()) {
                                        case 1:
                                            System.out.println("O carro mais perto é:\n");
                                            System.out.println(rentClosest(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros()));
                                            break;
                                        case 2:
                                            System.out.println("O carro mais barato é:");
                                            System.out.println(rentCheapest(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros()));
                                            break;
                                        case 3:
                                            System.out.println("Inserir raio máximo até ao carro:\n");
                                            double d;
                                            do{
                                                System.out.print("Raio = ");
                                                d=menuLogin.lerDouble();
                                            }while(d==-1);
                                            System.out.println(rentCheapest(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros(),d));
                                            break;
                                        case 4:
                                            //esta aqui não faz sentido printar todos os carros
                                            double d2;
                                            String lido;
                                            System.out.println("Aqui estão os carros todos que existem num raio:");
                                            do{
                                                System.out.print("Raio = ");
                                                d2 = menuLogin.lerDouble();
                                            }while (d2==-1);
                                            try {
                                                List<Veiculo> ret = availableCars(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros(),d2);
                                                System.out.println(ret);
                                                System.out.println("Insira a matrícula do carro que deseja alugar: ");
                                                do {
                                                    System.out.print("Matrícula: ");
                                                    lido = menuLogin.leString();
                                                }while(lido==null);
                                                System.out.println();
                                                try {
                                                    Veiculo query4 = rentID(ret,lido);
                                                    System.out.println(query4);
                                                }
                                                catch (PrintError e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            catch (PrintError e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 5:
                                            System.out.println("Inserir autonomia:\n");
                                            double aut;
                                            do{
                                                System.out.print("Autonomia = ");
                                                aut=menuLogin.lerDouble();
                                            }while(aut==-1);
                                            try{
                                                System.out.println(rentAutonomy(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros(),aut));
                                            }
                                            catch (PrintError e){
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 6:
                                            System.out.println("A localização atual é: " + logNegocio.getClienteCoordI(menuLogin.getPassword()));
                                            System.out.println("Inserir novas coordenadas: ");
                                            Ponto novoPonto;
                                            do {
                                                novoPonto=menuLogin.lerCoordenada();
                                            } while(novoPonto==null);
                                            logNegocio.setClientCoordI(menuLogin.getPassword(),novoPonto);
                                            System.out.println("Ok. Localização atual: " + novoPonto);
                                            break;
                                        case 7:
                                            System.out.println("O destino atual é: " + logNegocio.getClienteCoordF(menuLogin.getPassword()));
                                            System.out.println("Inserir novas coordenadas: ");
                                            Ponto outroPonto;
                                            do {
                                                outroPonto=menuLogin.lerCoordenada();
                                            } while(outroPonto==null);
                                            logNegocio.setClientCoordF(menuLogin.getPassword(),outroPonto);
                                            System.out.println("Ok. Destino atual: " + outroPonto);
                                            break;
                                    }
                                } while (this.menuCliente.getOp() != 0);
                                System.out.println("Voltando ao menu de login...");
                                break;
                            case 2:
                                do {
                                    this.menuProprietario.executa();
                                    switch (menuProprietario.getOp()) {
                                        case 1:
                                            System.out.println("mjmj");
                                            break;
                                        case 2:
                                            System.out.println("heh");
                                            break;
                                        case 3:
                                            System.out.println("keke");
                                            break;
                                        case 4:
                                            System.out.println("dddd");
                                            break;
                                        case 5:
                                            System.out.println(":D");
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
                    //sign-up
                    break;
            }
        }while(this.menuSign.getOp()!=0);
        System.out.println("Saindo do programa...");
    }

    private Veiculo rentClosest(Cliente client, Map<String,Veiculo> cars) {
        double dist, mindist = Double.MAX_VALUE;
        Ponto posI = client.getPosicaoI();
        Ponto poscar;
        Veiculo aux = null;

        for(Veiculo x : cars.values()) {
            poscar = x.getPosicao();
            dist = poscar.distancia(posI);
            if(dist < mindist) { //tem de ter autonomia
                mindist = dist;
                if(x instanceof Eletrico) {
                    aux = new Eletrico(x);
                }
                else if (x instanceof Hibrido) {
                    aux = new Hibrido(x);
                }
                else {
                    aux = new Gasolina(x);
                }
            }
        }
        return aux;
    }

    private Veiculo rentCheapest(Cliente client, Map<String,Veiculo> cars) {
        double mincusto = Double.MAX_VALUE;
        Veiculo v = null;
        Ponto posF = client.getPosicaoF();
        Ponto posC;
        double pkm, custo,distancia;
        for(Veiculo x: cars.values()) {
            posC = x.getPosicao();
            distancia = posF.distancia(posC);
            pkm = x.getPriceKm();
            custo = pkm*distancia;
            if(custo < mincusto) {
                mincusto = custo;
                if(x instanceof Eletrico) {
                    v = new Eletrico(x);
                }
                else if (x instanceof Hibrido) {
                    v = new Hibrido(x);
                }
                else {
                    v = new Gasolina(x);
                }
            }
        }
        return v;
    }

    //este e para a distancia no maximo de raio X
    private Veiculo rentCheapest(Cliente client, Map<String,Veiculo> cars, double raio) {
        double mincusto = Double.MAX_VALUE;
        Veiculo v = null;
        Ponto posI = client.getPosicaoI();
        Ponto posF = client.getPosicaoF();
        Ponto posC;
        double pkm, custo;
        double distancia;
        for(Veiculo x: cars.values()) {
            posC = x.getPosicao();
            distancia = posC.distancia(posF);
            pkm = x.getPriceKm();
            custo = pkm*distancia;
            if(custo < mincusto && posI.distancia(posC)<=raio) {
                mincusto = custo;
                if(x instanceof Eletrico) {
                    v = new Eletrico(x);
                }
                else if (x instanceof Hibrido) {
                    v = new Hibrido(x);
                }
                else {
                    v = new Gasolina(x);
                }
            }
        }
        return v;
    }

    private Veiculo rentAutonomy(Cliente client, Map<String,Veiculo> cars, double autonomy) throws PrintError {
        Veiculo v = null;
        double autonomia;
        for(Veiculo x : cars.values()) {
            autonomia = x.getDepositoAtual();
            if(autonomia==autonomy) {
                if(x instanceof Eletrico) {
                    v = new Eletrico(x);
                    break;
                }
                else if (x instanceof Hibrido) {
                    v = new Hibrido(x);
                    break;
                }
                else {
                    v = new Gasolina(x);
                    break;
                }
            }
        }
        if(v==null) {
            throw new PrintError("Não existe carro que satisfaça as condições");
        }
        return v;
    }

    private Veiculo rentID(List<Veiculo> cars, String ID) throws PrintError {
        try {
            return this.logNegocio.getCarro(ID);
        }
        catch (PrintError e) {
            throw new PrintError(e.getMessage());
        }
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


}
