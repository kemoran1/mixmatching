package memorygame;

	import javax.swing.JButton;
	import javax.swing.JFrame;

	public class Showgame {

		public static void main(String[] args) {
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					Gamepanel mg = new Gamepanel();
					mg.setVisible(true);
					mg.setSize(750,750);
					mg.setTitle("Mix Matching: 90s theme!!!");
					//mg.setBackground(Color.CYAN);
					mg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
				}
				
			});
			

		}

	}