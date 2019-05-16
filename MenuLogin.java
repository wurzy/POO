import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.out;

public class MenuLogin extends Menu{
    private String email;
    private String password;

    public MenuLogin(String[] opcoes) {
        super(opcoes);
        this.email = "";
        this.password = "";
    }

    public void executa(){
        do {
            showMenu();
            this.setOp(lerOpcao());
            out.print("Email: ");
            this.email = leString();
            out.print("Password: ");
            this.password = leString();
        } while (this.getOp() == -1);
    }

    public String leString(){
        String op = null;
        Scanner sc = new Scanner(System.in);
        try {
            op = sc.nextLine();
        }
        catch (InputMismatchException e){
            out.println("Não leu string");
        }

        return op;
    }

}
