import java.util.Scanner;
import java.util.Arrays;
import java.util.Calendar;

public class Testes
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       /*
        Cliente c = new Cliente();
        Birthday d = new Birthday(2,3,4);
        Birthday h = d.clone();
        h.setDay(69);
        Address ad = new Address(4750,519,65,"Lel","Kek","lmao");
        Address xd = ad.clone();
        xd.setCity("LEL");
        xd.setCityPostal(6969);
        Email em = new Email("olaxd","google.pt");
        Email ex = em.clone();
        ex.setDomain("microsoft-live.pt");
        ex.setUser("xdola");
        System.out.println(em.toString() + "\n" + ex.toString());

       Ponto p = new Ponto();
       p.setX(2.0);
       p.setY(4.265);
       Ponto x = p.clone();
       x.setX(9.2);
       x.setY(4.12);
       System.out.println(p.equals(x));
       System.out.println(p.iguais(x));
       System.out.println(p);
       System.out.println(x);
       */
       Address add = new Address(4269,420,21,2,"Lisboa","Benfica","Santa Clara","Yes");
       System.out.println(add);
    }
}
