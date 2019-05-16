import java.time.LocalDate;
import java.util.*;

public class App {
    private MyLog logNegocio = new MyLog();
    private Menu menuLogin;
    private Menu menuPrincipal;

    public static void main(String[] args){
        new App().run();
    }

    private App(){
        String[] opcoes = {"teste 1",
                "teste 2",
                "teste 3"};
        this.menuPrincipal = new Menu(opcoes);
    }

    private void run(){
        /*
        do{
            this.menuLogin.executaLogin();
        } while (this.logNegocio.verificaLogin(this.menuLogin.getTipo(), this.menuLogin.getPassword()));
*/
        do{
            this.menuPrincipal.executa();
            switch (menuPrincipal.getOp()){
                case 1: System.out.println("Escolheu a opção 1");
                    System.out.println(logNegocio.getQts());
                    break;
                case 2: System.out.println("Escolheu a opção 2");
                    Cliente c = new Cliente("@xD","ola","xd","lmao", LocalDate.of(4,4,4),new Ponto(0,0),new Ponto(0,0),new TreeSet<>());
                    //Veiculo x = rentClosest(c,logNegocio.getCarros());
                    //System.out.println(x);
                    System.out.println(logNegocio.getQts());
                    break;
                case 3: System.out.println("Escolheu a opção 3");
                    break;
            }
        } while (this.menuPrincipal.getOp() != 0);
        System.out.println("A sair do programa");
    }

    private Veiculo rentClosest(Cliente client, Map<String,Veiculo> cars) {
        double dist, mindist = 999999999;
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

    private Veiculo rentCheapest(Map<String,Veiculo> cars) {

    }
}
