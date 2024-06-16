import java.util.ArrayList;
import java.util.List;

public class graphnode {
    String node;
    List<graphnode> right = new ArrayList<graphnode>();
    public graphnode(String n) {
        this.node = n;
    }
    public void addRight(graphnode s) {
        this.right.add(s);
    }
}