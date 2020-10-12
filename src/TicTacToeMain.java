import controller.Controller;
import model.Model;
import view.View;

public class TicTacToeMain {

    public static void main(String[] args) {
        // Create the components
        Model m = new Model();
        View v = new View();
        Controller c = new Controller();

        // Use aggregation to put the components together
        m.registerView(v);
        v.setActionListener(c);
        c.setModel(m);
    }
}

