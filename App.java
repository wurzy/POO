import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class App {
    private MyLog logNegocio = new MyLog();
    private Menu menuCliente,menuProprietario;
    private MenuLogin menuLogin;

    public static void main(String[] args){
        new App().run();
    }

    private App(){
        String[] cliente = {"Alugar carro mais perto",
                "Alugar o carro mais barato",
                "Alugar o carro mais barato num raio R",
                "Alugar um carro em específico",
                "Alugar um carro com a autonomia desejada A"};
        String[] props = {"Disponibilizar um veículo para aluguer",
                "Abastacer um dos meus veículos",
                "Alterar preço/km de um dos meus veículos",
                "Aceitar/Rejeitar um aluguer",
                "Registar o custo da viagem V"
                };
        String[] login = {"Cliente", "Proprietário"};
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
                                //EXECUTE QUERIES
                                System.out.println("O carro mais perto é:\n");
                                System.out.println(rentClosest(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros()));
                                break;
                            case 2:
                                System.out.println("O carro mais barato é:");
                                System.out.println(rentCheapest(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros()));
                                break;
                            case 3:
                                System.out.println("Inserir distância:\n");
                                double d;
                                do{
                                    System.out.print("D = ");
                                    d=menuLogin.lerDouble();
                                }while(d==-1);
                                System.out.println(rentCheapest(logNegocio.getCliente(menuLogin.getPassword()),logNegocio.getCarros(),d));
                                break;
                            case 4:
                                System.out.println("kkkk");
                                break;
                            case 5:
                                System.out.println(":O");
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
        System.out.println("A sair do programa");
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
                }
                else if (x instanceof Hibrido) {
                    v = new Hibrido(x);
                }
                else {
                    v = new Gasolina(x);
                }
            }
        }
        if(v==null) {
            throw new PrintError("Não existe carro que satisfaça as condições");
        }
        return v;
    }

    private Veiculo rentID(Cliente client, Map<String,Veiculo> cars, String ID) throws PrintError {
        Veiculo v = null;
        if(cars.containsKey(ID)) {
            v = cars.get(ID).clone();
        }
        else {
            throw new PrintError("Não existe carro com essa Matrícula");
        }
        return v;
    }

    private void printAvailableCars(Map<String,Veiculo> cars) {
        System.out.println(cars.toString());
    }
}
