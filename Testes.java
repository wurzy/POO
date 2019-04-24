import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        LocalDate teste = LocalDate.of(1999,4,2);
       System.out.println(teste.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

       Address add = new Address(4269,420,21,2,"Lisboa","Benfica","Santa Clara","Yes");
       Address add2 = new Address(4213,213,3,0,"xd","xk","xf","xs");
       Birthday bir = new Birthday(25,12,1992);
       Birthday bir2 = new Birthday(22,4,1999);
       Ator at = new Ator("ola@user.com","Fernando","ola123",add,bir);
       Ator at2 = new Ator("ola@user.com","Fernando","ola123",add,bir);
        System.out.println(at.equals(at2));
       at2.setAddress(add2);
       System.out.println(at.equals(at2));

       Veiculo vei = new Veiculo();
       Veiculo audi = new Veiculo(23,12,3,1,2);
       Map<String,Veiculo> xd = new HashMap<>();
       xd.put("ola",vei);
       xd.put("XDDDDDDDDDDDDD",audi);
       List<Integer> c = new ArrayList<>();
       c.add(2);
       c.add(3);

       //Birthday bir = new Birthday(25,12,1992);
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

       Address add = new Address(4269,420,21,2,"Lisboa","Benfica","Santa Clara","Yes");
       Ator novo = new Ator("xd","ola","...",add,LocalDate.of(1999,1,3));
       System.out.println(novo);

       Ponto p1 = new Ponto();
       Ponto p2 = p1.clone();
       p2.setY(4.2);
       p2.setX(1.4);
       Ponto p3 = new Ponto(p2);
       p3.setX(69);
       p3.setY(20.2);

       Aluguer al1 = new Aluguer(4,2,"xd","xd2",p1,p2,p3,100,2.4,69);
       //System.out.println(al);
        Aluguer al2 = new Aluguer(1,9,"a","b",p2,p1,p1,20,40,10.2);
        Aluguer al3 = new Aluguer(5,10,"uu","sdaa",p3,p3,p3,40,1,2);
        Aluguer al4 = new Aluguer(2,10,"uu","sdaa",p3,p3,p3,40,1,2);

       Set<Aluguer> teste = new TreeSet<>();
       teste.add(al1);
       teste.add(al2);
       teste.add(al3);
       teste.add(al4);
        for(Aluguer l:teste) {
          System.out.println(l.getAluguerID());
        }

        Ponto p1 = new Ponto();
        Ponto p2 = p1.clone();
        p2.setY(4.2);
        p2.setX(1.4);
        Ponto p3 = new Ponto(p2);
        p3.setX(69);
        p3.setY(20.2);

        List<Integer> class1 = new ArrayList<>();
        class1.add(4);
        class1.add(20);
        class1.add(100);
        class1.add(50);

       Set<Aluguer> hist1 = new TreeSet<>(new AluguerComparator());
        Aluguer al1 = new Aluguer(4,2,"xd","xd2",p1,p2,p3,100,2.4,69);
        Aluguer al2 = new Aluguer(1,9,"a","b",p2,p1,p1,20,40,10.2);
        Aluguer al3 = new Aluguer(5,10,"uu","sdaa",p3,p3,p3,40,1,2);
        Aluguer al4 = new Aluguer(2,103,"ssss","sdadaa",p1,p3,p2,401,21,332);

       hist1.add(al1);
       hist1.add(al2);
       hist1.add(al3);


        Veiculo v1 = new Veiculo(4,0,21.2,4.2,1.2,hist1,class1,4.2,10);

       //System.out.println(v1);

        System.out.println("\nACABOU\n");

        Set<Aluguer> hist2 = v1.getHistorico();
        hist2.add(al4);
        //System.out.println(hist1.get);
        for(Aluguer l:hist1) {
            System.out.println(l.getAluguerID());
        }
        System.out.println("\n");
        for(Aluguer l:hist2) {
            System.out.println(l.getAluguerID());
        }
        System.out.println("\n");
        for(Aluguer l:hist1) {
            System.out.println(l.getAluguerID());
        }
        //System.out.println(hist1.equals(hist2));

      //  System.out.println(hist2.equals(hist1));
    */
       LocalDate bir = LocalDate.of(1999,2,3);
       Address add = new Address(4269,420,21,2,"Lisboa","Benfica","Santa Clara","Yes");
       Set<Aluguer> hist1 = new TreeSet<>();

       Ator at = new Ator("ola@user.com","Fernando","ola123",add,bir);
       Ator at2 = new Ator("ssss@user.com","Leandro","2222",add,bir);

       Ponto p1 = new Ponto(2.0,4.2);
       Ponto p2 = new Ponto( 7.2, 1.9 );
       Ponto p3 = new Ponto(6.1,20.3);

       Aluguer al1 = new Aluguer(4,2,"xd","xd2",p1,p2,p3,100,2.4,69);
       Aluguer al2 = new Aluguer(1,9,"a","b",p2,p1,p1,20,40,10.2);
       Aluguer al3 = new Aluguer(5,10,"uu","sdaa",p3,p3,p3,40,1,2);
       Aluguer al4 = new Aluguer(2,103,"ssss","sdadaa",p1,p3,p2,401,21,332);

       hist1.add(al1);
       hist1.add(al2);
       hist1.add(al3);

       Cliente cli = new Cliente(at2,p1,hist1);

       //hist1.add(al4);

       Cliente cli2 = new Cliente(at2,p1,hist1);
       System.out.println(cli);
    }

}
