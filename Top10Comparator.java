import java.util.Comparator;

public class Top10Comparator implements Comparator<Cliente> {

    public int compare(Cliente cl1, Cliente cl2) {
        if (cl1.getHistorico().size() > cl2.getHistorico().size()) {
            return -1;
        }
        else if (cl1.getHistorico().size() < cl2.getHistorico().size()){
            return 1;
        }
        else return 0;
    }
}
