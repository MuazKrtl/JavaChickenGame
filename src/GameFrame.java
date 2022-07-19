import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame{

    public GameFrame(){
        this.add(new Game());
        this.setTitle("ChickenAway");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.CYAN);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}