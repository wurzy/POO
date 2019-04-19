import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Calendar;
import java.util.*;

public class Testes
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       /*
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

       Address add = new Address(4269,420,21,2,"Lisboa","Benfica","Santa Clara","Yes");
       Address add2 = new Address(4213,213,3,0,"xd","xk","xf","xs");
       Birthday bir = new Birthday(25,12,1992);
       Birthday bir2 = new Birthday(22,4,1999);
       Ator at = new Ator("ola@user.com","Fernando","ola123",add,bir);
       Ator at2 = new Ator("ola@user.com","Fernando","ola123",add,bir);
        System.out.println(at.equals(at2));
       at2.setAddress(add2);
       System.out.println(at.equals(at2));
    */
       Veiculo vei = new Veiculo();
       Veiculo audi = new Veiculo(23,12,3,1,2);
       Map<String,Veiculo> xd = new HashMap<>();
       xd.put("ola",vei);
       xd.put("XDDDDDDDDDDDDD",audi);
       List<Integer> c = new ArrayList<>();
       c.add(2);
       c.add(3);
       Address add = new Address(4269,420,21,2,"Lisboa","Benfica","Santa Clara","Yes");
       Birthday bir = new Birthday(25,12,1992);
       Ator at = new Ator("ola@user.com","Fernando","ola123",add,bir);
       Proprietario pro = new Proprietario(at,xd,c);
       System.out.println(pro);
       System.out.println(pro.getClassificacao());
       Proprietario pro2 = new Proprietario(pro);
       //c.add(4);
       //xd.put("oxa",vei);
       pro.setClassificacao(c);
       pro.setHistorico(xd);
       System.out.println(pro.equals(pro2));

    }
}
