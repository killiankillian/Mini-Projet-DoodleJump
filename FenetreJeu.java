import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public  class FenetreJeu extends JFrame implements KeyListener, ActionListener{

		LinkedList<Palier> listePalier = new LinkedList<>();
		LinkedList<Palier> listePalierStock = new LinkedList<>();

		ImageIcon imageDoodle = new ImageIcon("C:\\Users\\manon\\IdeaProjects\\doodleJump\\src\\Doodle.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\manon\\projetDoodleJump\\Fond.png");
		ImageIcon imagePalier0= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier.png");
		ImageIcon imagePalier1= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier1.png");
		ImageIcon imagePalier2= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier2.png");
		ImageIcon imagePalier3= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier3.png");
		ImageIcon imagePalier4= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier4.png");
		ImageIcon imagePalier5= new ImageIcon("C:\\Users\\manon\\DoodleFinal\\src\\palier5.png");

		/*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Doodle.png");
		ImageIcon imagePalier = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\palier.png");
		ImageIcon imageFond = new ImageIcon("C:\\Users\\marie\\OneDrive\\Bureau\\doodleJump\\Fond.png"); */

		/*ImageIcon imageDoodle = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Doodle.png"); //killian//killian//killian//killian
		ImageIcon imageFond = new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\Fond.png");//killian//killian//killian//killian//killian
		ImageIcon imagePalier0= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier.png");//killian//killian//killian//killian tu foooooooooorces
		ImageIcon imagePalier1= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier1.png");//killian//killian//killian//killian toi tu force a effacer a chaque foiiiiiiiiiis
		ImageIcon imagePalier2= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier2.png");//killian//killian//killian//killian  tssssssssssss (déso pas déso)
		ImageIcon imagePalier3= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier3.png");//killian//killian//killian//killian
		ImageIcon imagePalier4= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier4.png");//killian//killian//killian//killian
		ImageIcon imagePalier5= new ImageIcon("C:\\Users\\utilisateur\\doodleJump\\palier5.png");//killian//killian//killian//killian*/

		final int HEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		final int WIDTH= HEIGHT/2;

		JLabel labelDoodle;
		JLabel labelPalier;
		Doodle monDoodle;
		Palier monPalier;

		private int deltaY=0;
		int hauteurMax= HEIGHT*2/5;
		int score;
		Timer mt= new Timer(40,this);

	public FenetreJeu() {

			this.setTitle("DoodleJump");
			this.setSize(WIDTH, HEIGHT);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addKeyListener(this);
			mt.start();

			imageDoodle = new ImageIcon(imageDoodle.getImage().getScaledInstance((WIDTH/10) ,(HEIGHT/20), Image.SCALE_DEFAULT));
			labelDoodle = new JLabel (imageDoodle);
			monDoodle= new Doodle(((WIDTH-(WIDTH/10))/2), (HEIGHT+(HEIGHT/20))/2,labelDoodle);
			this.add(monDoodle.support);

			double pourcentage = 0.02;
			int calculNbPalier= (int)(HEIGHT*pourcentage);
			for (int i=0 ; i<calculNbPalier; i++) {
				int a = (int) (Math.random() * (HEIGHT - WIDTH/29));
				int b = (int) (Math.random() * (WIDTH -HEIGHT/18));
				creationPalier(a,b);
			}

			imageFond = new ImageIcon(imageFond.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
			JLabel labelFond = new JLabel(imageFond);
			labelFond.setBounds(0, 0, WIDTH, HEIGHT);
			this.add(labelFond);

			this.setVisible(true);
		}

		/**créée et place des palliers de manière aléatoire*/
		public void creationPalier(int a, int b ){
			int s = (int)(95*Math.random());
			int ty = type(s);
			labelPalier = new JLabel (imageType(ty));
			monPalier = new Palier(b, a, labelPalier, ty);
			this.add(monPalier.support);
			listePalier.add(monPalier);
		}

		/** créée des paliers de secours hors de la fenêtres qui descendent lorsque la distance entre le doodle et les paliers existant
		 *  est supérieure à la distance parcouru par un saut (en hauteur) */
		public void palierDeSecours() {
			if (deltaY >HEIGHT/6) {
				int a = -20;
				int b = (int) (Math.random() * (WIDTH-58));
				if (listePalierStock.size ()>=1) {
					listePalierStock.get(0).setX(b);
					listePalierStock.get(0).setY(a);
					listePalierStock.get(0).type=0;
					imagePalier0 = new ImageIcon(imagePalier0.getImage().getScaledInstance(monPalier.width, monPalier.height, Image.SCALE_DEFAULT));
					listePalierStock.get(0).support.setIcon(imagePalier0);
					listePalier.add(listePalierStock.get(0));
					listePalierStock.remove(0);
					deltaY = 0;
				}
			}
		}
		/** détermine la collision entre le doodle et les paliers en fonction de leur type et lance les méthodes de saut , toucher un palier de type 5: c'est perdu*/
		public void collision() {
			for (Palier palier : listePalier) {
				if (monDoodle.vitesseY > 0) {
					if (((monDoodle.y + monDoodle.height) < (palier.y + 1.25 * palier.height)) && ((monDoodle.y + 1.1 * monDoodle.height) > (palier.y))) {
						if (((monDoodle.x + 0.6 * monDoodle.width) > (palier.x)) && ((monDoodle.x) < (palier.x + palier.width))) {
							switch (palier.type) {
								case 0:
									monDoodle.saut();
									break;
								case 1:
									monDoodle.petitSaut();
									break;
								case 2:
									monDoodle.moyenSaut();
									break;
								case 3:
									monDoodle.superSaut();
									break;
								case 4:
									monDoodle.saut();
									palier.x = (int) (Math.random() * (WIDTH -HEIGHT/18));
									palier.y = -1000;
									break;
							}
						}
					}
				}
				if (((monDoodle.y ) < (palier.y +0.6*palier.height)) && ((monDoodle.y + 0.6*monDoodle.height) > (palier.y)) &&
						((monDoodle.x + 0.6*monDoodle.width) > (palier.x)) && ((monDoodle.x) < (palier.x + palier.width)) && palier.type == 5 ) {
						FenetreMort maFenetreMort = new FenetreMort(score);
						maFenetreMort.setVisible(true);
						mt.stop();
						this.setVisible(false);
				}
			}
		}

		/** permet au doodle et au palier de type 3 la sortie de l'écran d'un côté pour revenir de l'autre*/
		public void checkSortieEcran(){
			if(monDoodle.x>WIDTH) {
				monDoodle.x=-monDoodle.width;
			}
			if(monDoodle.x+monDoodle.width<0) {
				monDoodle.x=WIDTH;
			}
			for (int i=0;i<listePalier.size();i++) {
				if ((listePalier.get(i).type==2)&&(listePalier.get(i).x > WIDTH)) {
					listePalier.get(i).setX(-listePalier.get(i).width);
				}
				if  ((listePalier.get(i).type==2)&&(listePalier.get(i).x  + listePalier.get(i).width < 0)) {
					listePalier.get(i).setX(WIDTH);
				}
			}
		}

	/** déplace les palliers vers le bas proportionellement à la hauteur du doodle
	 Diminue le nombre de palliers en fonction du score, recycle les paliers qui tombe en les ramenant plus haut que l'écran de jeu et changent leurs types
	 */
	public void bougeEcran(){
			 if(monDoodle.y< hauteurMax ) {
				 int depassement = hauteurMax - monDoodle.y;
				 monDoodle.setY(hauteurMax);
				 for (int i = 0; i < listePalier.size(); i++) {
					 listePalier.get(i).setY(listePalier.get(i).y + depassement);
					 listePalier.get(i).support.setLocation(listePalier.get(i).x, listePalier.get(i).y);
					 double a = Math.random();
					 double prob = 0.90;
					 if (listePalier.get(i).y > HEIGHT) {
						 if (a < prob) {
							 int b = (int) (-20 - Math.random()*80);
							 int d = (int) (Math.random() * (WIDTH - monPalier.width));
							 listePalier.get(i).setY(b);
							 listePalier.get(i).setX(d);
							 if(listePalier.get(i).type != 5 && listePalier.get(i).type != 1) {
								 deltaY = 0;
							 }
							 int s = (int)(100*Math.random());
							 int w =  type(s);
							 ImageIcon img = imageType(w);
							 listePalier.get(i).type=w;
							 listePalier.get(i).support.setIcon(img);
						 } else {
							 listePalierStock.add(listePalier.get(i));
							 listePalier.remove(i);
						 }
					 }
				 }
				 score += depassement;
				 deltaY += depassement;
			 }
	}

	public void checkMort() {
		if((monDoodle.y+monDoodle.height)> 1.3*HEIGHT) {
			FenetreMort maFenetreMort = new FenetreMort(score);
			maFenetreMort.setVisible(true);
			mt.stop();
			this.setVisible(false);
		}
	}
	/** renvoie l'image correspondante au type*/
	public ImageIcon imageType(int p){
		ImageIcon imagePalier=imagePalier0;
		switch (p){
			case 0:
			imagePalier = new ImageIcon(imagePalier0.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 1:
			imagePalier = new ImageIcon(imagePalier1.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 2:
			imagePalier = new ImageIcon(imagePalier2.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 3 :
			imagePalier = new ImageIcon(imagePalier3.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 4:
			imagePalier = new ImageIcon(imagePalier4.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
			case 5:
			imagePalier = new ImageIcon(imagePalier5.getImage().getScaledInstance(HEIGHT / 18, WIDTH / 29, Image.SCALE_DEFAULT));
			break;
		}
		return imagePalier;
	}

		/** renvoie le type */
		public int type(int p){
			int type=0;
			if ((p>71)&&(p<=76)) {
				type = 1;
			}else if ((p>76)&&(p<=83)) {
				type = 2;
			}else if ((p>83)&&(p<=89)) {
				type = 3;
			}else if ((p>89)&&(p<95)) {
				type = 4;
			}else if (p>95){
				type = 5;
			}return type;
		}

		/** déplace de manière continue les paliers de type 2 vers la gauche ou la droite selon leurs position dans la liste listePalier*/
		public void bougePalier(){
			int d = 3;
			for(int i=0 ; i<listePalier.size();i++){
				if ((listePalier.get(i).type==2)&&(i%2==0)){
					listePalier.get(i).setX(listePalier.get(i).x + d);
					listePalier.get(i).support.setLocation(listePalier.get(i).x,listePalier.get(i).y );
				}
				else if ((listePalier.get(i).type==2)&&(i%2!=0)){
					listePalier.get(i).setX(listePalier.get(i).x - d);
					listePalier.get(i).support.setLocation(listePalier.get(i).x,listePalier.get(i).y );
				}
			}
		}

	/**
	 * Suite à un événement
	 */
		public void actionPerformed(ActionEvent e) {

			this.setTitle("DoodleJump " + score);
			monDoodle.support.setLocation(monDoodle.x,monDoodle.y);
			monDoodle.tombeDoodle();
			monDoodle.bougeX();
			bougeEcran();
			bougePalier();
			collision();
			palierDeSecours();
			checkSortieEcran();
			checkMort();

		}
		public void keyPressed (KeyEvent e){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					monDoodle.droite= true;
					monDoodle.stopDroite= false;
					monDoodle.gauche=false;
					monDoodle.stopGauche=false;
					break;
				case KeyEvent.VK_LEFT:

					monDoodle.gauche= true;
					monDoodle.stopGauche= false;
					monDoodle.droite= false;
					monDoodle.stopDroite= false;
					break;
			}
		}
		public void keyReleased (KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_RIGHT && monDoodle.vitesseX>0) {
				monDoodle.stopDroite= true;
				monDoodle.droite= false;
				monDoodle.stopGauche= false;
				monDoodle.gauche= false;
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT && monDoodle.vitesseX<0) {
				monDoodle.stopGauche= true;
				monDoodle.gauche= false;
				monDoodle.stopDroite= false;
				monDoodle.droite= false;
			}
		}
		public void keyTyped (KeyEvent e){
		}

	}

