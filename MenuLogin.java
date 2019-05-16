import static java.lang.System.out;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLogin extends Menu{
        private String email;
        private String password;

        public MenuLogin(String[] opcoes) {
            super(opcoes);
            this.email = "";
            this.password = "";
        }

        public void executaParametros() {
            out.print("E-mail: ");
            this.email = leString();
            out.print("Password: ");
            this.password = leString();
        }

        public void executaReader(){
            showMenu();
            int aux;
            do{
                aux=lerOpcao();
            } while(aux<0 || aux>2);
            this.setOp(aux);
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

        public String getEmail(){
            return this.email;
        }

        public String getPassword(){
            return this.password;
        }
}
