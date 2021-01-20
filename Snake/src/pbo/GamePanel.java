package pbo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

	public class GamePanel extends JPanel implements ActionListener {
		static final int SCREEN_WIDTH = 600;
		static final int SCREEN_HEIGHT = 600;
		static final int UNIT_SIZE = 25;
		static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
		static final int DELAY = 200;
		final int x[] = new int[GAME_UNITS];
		final int y[] = new int[GAME_UNITS];
		int bodyPart = 3;
		int countApple;
		int appleX;
		int appleY;
		int appleGreenX, appleGreenY;
		char direction = 'R';
		boolean inGame = false;
		Timer timer;
		Random random;
 
		spawnapel apel = new spawnapel();
		spawngreenapple greenapple = new spawngreenapple();
		private Image ball;
		private Image apple;
		private Image head;
		private Image greenApple;
 
 
		public GamePanel(){
			random = new Random();
			this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
			this.setBackground(Color.black);
			this.setFocusable(true);
			this.addKeyListener(new MyKeyAdapter());
			loadImage();
			startGame();
		}
 
		public void startGame() {
			locateapel();
			locategreenapple();
			inGame = true;
			timer = new Timer(DELAY,this);
			timer.start();
		}
 
		private void locateapel() {
		
			apel.spawnobjectt();
			appleX = apel.getX();
			appleY = apel.getY(); 
			    
		}
		
		private void locategreenapple(){
			greenapple.locategreen();
			appleGreenX = greenapple.getX();
			appleGreenY = greenapple.getY();	
		}

		private void loadImage() {
			ImageIcon iid = new ImageIcon("src/pbo/bodyfixbet.png");
			ball = iid.getImage();
  
			ImageIcon iia = new ImageIcon("src/pbo/apelipin.png");
			apple = iia.getImage();

			ImageIcon iih = new ImageIcon("src/pbo/kepala.png");
			head = iih.getImage();
			ImageIcon iig = new ImageIcon("src/pbo/headd.png");
			greenApple=iig.getImage();
		}
	
 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		public void draw(Graphics g) {
  
			if(inGame) {
				for(int i = 0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
					g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
					g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
				}
				
				
				g.drawImage(apple, appleX, appleY, this);
				g.drawImage(greenApple, appleGreenX, appleGreenY, this);
   
					for (int z = 0; z < bodyPart; z++) {
						if (z == 0) {
							g.drawImage(head, x[z], y[z], this);
						} else {
                     g.drawImage(ball, x[z], y[z], this);
						}
					}
					g.setColor(Color.RED);
					g.setFont(g.getFont().deriveFont(25.0f));
					FontMetrics metrics = getFontMetrics(g.getFont());
					g.drawString("Score: "+countApple, (SCREEN_WIDTH - metrics.stringWidth("Score: "+countApple))/2, g.getFont().getSize());
			}
			else {
				gameOver(g);
			}
  
		}
	
		public void move(){
			for(int i = bodyPart;i>0;i--) {
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
  
				switch(direction) {
				case 'U':
					y[0] = y[0] - UNIT_SIZE;
					break;
				case 'D':
					y[0] = y[0] + UNIT_SIZE;
					break;
				case 'L':
					x[0] = x[0] - UNIT_SIZE;
					break;
				case 'R':
					x[0] = x[0] + UNIT_SIZE;
					break;
				}
  
			}
		public void checkApple() {
			if((x[0] == appleX) && (y[0] == appleY)) {
				countApple++;
				bodyPart++;
				locateapel();
			}
		}
		public void checkgreenApple() {
			if((x[0] == appleX) && (y[0]== appleY)) {
				bodyPart++;
				locategreenapple();
			}
		}
		
	
		public void checkCollisions() {
			for(int i = bodyPart;i>0;i--) {
				if((x[0] == x[i])&& (y[0] == y[i])) {
					inGame = false;
				}
			}
 
					if(x[0] < 0) {
					inGame = false;
					}
  
					if(x[0] > SCREEN_WIDTH) {
						inGame = false;
					}
				
					if(y[0] < 0) {
						inGame = false;
					}
 
					if(y[0] > SCREEN_HEIGHT) {
						inGame = false;
					}
  
					if(!inGame) {
						timer.stop();
					}
				}
			public void gameOver(Graphics g) {
			
				g.setColor(Color.WHITE);
				g.setFont( new Font("Calibri",Font.BOLD, 40));
				FontMetrics metrics1 = getFontMetrics(g.getFont());
				g.drawString("Score: "+countApple, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+countApple))/2, g.getFont().getSize());
  
  
				g.setColor(Color.WHITE);
				g.setFont( new Font("Impact",Font.BOLD, 75));
				FontMetrics metrics2 = getFontMetrics(g.getFont());
				g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
			}
			@Override
			public void actionPerformed(ActionEvent e) {
  
				if(inGame) {
					move();
					checkApple();
					checkCollisions();
				}
				repaint();
			}
 
			public class MyKeyAdapter extends KeyAdapter{
				@Override
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if(direction != 'R') {
							direction = 'L';
						}
						break;
					case KeyEvent.VK_RIGHT:
						if(direction != 'L') {
							direction = 'R';
						}
						break;
					case KeyEvent.VK_UP:
						if(direction != 'D') {
							direction = 'U';
						}
						break;
					case KeyEvent.VK_DOWN:
						if(direction != 'U') {
							direction = 'D';
						}
						break;
					}
				}
			}
	}