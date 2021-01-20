	package pbo;

	import java.awt.Dimension;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;

		public class GameFrame extends JFrame implements ActionListener {
			JMenu game ;
			JMenuBar menuBar;
			JMenuItem newGame;
 
				public GameFrame() {
					initGame();
				}
 
				public void initGame() {
					GamePanel panel = new GamePanel();
  
					menuBar = new JMenuBar();
  
					game = new JMenu("Game");
					menuBar.add(game);
  
					newGame = new JMenuItem("New Game");
					game.add(newGame);
  
					newGame.addActionListener(this);
  
					this.add(panel);
					this.setJMenuBar(menuBar);
					this.setTitle("Snake Apple Bite");
					this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					this.setResizable(false);
					this.pack();
					this.setVisible(true);
				}
 
				public void resetGame() {
					this.setVisible(false);
					this.dispose();
					SnakeGame.main(null);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource()==newGame) {
						resetGame();
					}
				}
		}	