import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class Menu {
    private List<String> opcoes;
    private int op;

    public Menu(String[] opcoes){
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void showMenu(){
        out.println("\n *** Menu ***");
        for (int i=0; i<this.opcoes.size(); i++){
            out.print(i+1);
            out.print(" - ");
            out.println(this.opcoes.get(i));
        }
        out.println("0 - Sair");
    }

    public int lerOpcao(){
        int op;
        Scanner sc = new Scanner(System.in);
        out.print("Opção: ");
        try {
            op = sc.nextInt();
        }
        catch (InputMismatchException e){
            op = -1;
        }

        if (op < 0 || op > this.opcoes.size()){
            out.println("Opção inválida!!!");
            op = -1;
        }
        return op;
    }

    public int getOp(){
        return this.op;
    }

    public double lerDouble(){
        double op = -1;
        Scanner sc = new Scanner(System.in);
        //out.print("Input: ");
        try {
            op = sc.nextDouble();
        }
        catch (InputMismatchException e){
            out.println("Não foi um double");
        }
        return op;
    }

    public void setOp(int op){ this.op = op; }
}
