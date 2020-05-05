import javax.swing.*;

public class Element extends JFrame {

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected JLabel support;

    public Element (int x, int y , JLabel support){
        this.x= x;
        this.y= y;
        this.support=support;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
