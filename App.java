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
                                System.out.println("xD");
                                break;
                            case 2:
                                System.out.println("lol");
                                break;
                            case 3:
                                System.out.println("lmao");
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
            if(dist < mindist) {
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
/*
    private Veiculo rentCheapest(Map<String,Veiculo> cars) {

    }
    */
}
