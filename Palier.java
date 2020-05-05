import javax.swing.*;
import java.awt.event.KeyEvent;


public class Palier extends Element {

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    final int WIDTH= HEIGHT/2;
    protected int type ;

    public Palier(int x, int y, JLabel palier, int t){
        super(x,y, palier);
        this.width = HEIGHT/18;
        this.height = WIDTH/29;
        this.type = t;
        palier.setBounds(x, y, width, height);
        palier.setLayout(null);

    }
}


