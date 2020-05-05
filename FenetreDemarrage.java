import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreDemarrage extends JFrame implements ActionListener{

 /*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png");
	ImageIcon imagePalier= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
	ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png");
	ImageIcon imageTitre= new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Titre.png");*/

    ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Doodle.png");
    ImageIcon imagePalier= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\palier.png");
    ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
    ImageIcon imageTitre= new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Titre.png");

   /* ImageIcon imageDoodle = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png"); //killian//killian//killian//killian//killian//killian//killian
    ImageIcon imagePalier= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier.png");     //killian//killian//killian//killian//killian//killian
    ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");    //killian//killian//killian//killian//killian//killian//killian
    ImageIcon imageTitre= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Titre.png");   //killian//killian//killian//killian//killian//killian//killian*/

    private JButton monBoutonDemarrer;

    final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    final int WIDTH=HEIGHT/2;

    Doodle monDoodle;
    Palier monPalier;
    JLabel labelDoodle;
    JLabel labelPalier;

    public FenetreDemarrage(){

        this.setTitle("DoodleJump ");
        this.setSize(WIDTH,HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer mt= new Timer(40,this);
		mt.start();

        imageDoodle = new ImageIcon(imageDoodle.getImage().getScaledInstance((WIDTH/10) ,(HEIGHT/20), Image.SCALE_DEFAULT));
        labelDoodle = new JLabel (imageDoodle);
        int x = (WIDTH - (WIDTH / 10))/2;
        int y = (HEIGHT + (HEIGHT/20))/2;
        monDoodle= new Doodle(x, y,labelDoodle);
        this.add(monDoodle.support);

        imageTitre = new ImageIcon(imageTitre.getImage().getScaledInstance(WIDTH, HEIGHT/6, Image.SCALE_DEFAULT));
        JLabel labelTitre = new JLabel(imageTitre);
        labelTitre.setBounds(0, 0, WIDTH, HEIGHT/6);
        this.add(labelTitre);

        monBoutonDemarrer = new JButton("Démarrer");
        monBoutonDemarrer.setBounds((WIDTH/2 -130),HEIGHT*4/5,260,100);
        monBoutonDemarrer.setBackground(new Color(10,144,10));
        monBoutonDemarrer.setForeground(Color.white);
        monBoutonDemarrer.addActionListener(this);
        this.add(monBoutonDemarrer);

		imagePalier = new ImageIcon(imagePalier.getImage().getScaledInstance((HEIGHT/18),(WIDTH/29) , Image.SCALE_DEFAULT));
		labelPalier= new JLabel (imagePalier);
        monPalier = new Palier((WIDTH-HEIGHT/18)/2, monDoodle.y+monDoodle.height, labelPalier,0);
        this.add(monPalier.support);

        imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        JLabel labelFond = new JLabel(imageFond);
        labelFond.setLocation(0, 0);
        this.add(labelFond);

        this.setVisible(true);
    }

    public void collision() {
            if (monDoodle.vitesseY > 0) {
                if (((monDoodle.y + monDoodle.height) < (monPalier.y + 1.25*monPalier.height)) && ((monDoodle.y + 1.1*monDoodle.height) > (monPalier.y))) {
                    if (((monDoodle.x + 0.6*monDoodle.width) > (monPalier.x)) && ((monDoodle.x) < (monPalier.x + monPalier.width))) {
                        monDoodle.saut();
                    }
                }
            }
        }
    /**
     * Suite à un événement
     */
    public void actionPerformed (ActionEvent e){
			this.setTitle("DoodleJump " );
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
            monDoodle.support.setLocation(monDoodle.x,monDoodle.y);
			collision();
			
        if (e.getSource()== monBoutonDemarrer){
            FenetreJeu maFenetreJeu = new FenetreJeu();
            maFenetreJeu.setVisible(true);
            this.setVisible (false);
        }
    }
}

