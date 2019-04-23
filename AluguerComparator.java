import java.util.Comparator;

public class AluguerComparator implements Comparator<Aluguer> {
    public int compare(Aluguer al1, Aluguer al2) {
        return Integer.compare(al1.getAluguerID(),al2.getAluguerID());
    }
}

