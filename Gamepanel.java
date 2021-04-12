	package memorygame;

	import java.awt.BorderLayout; 
	import java.awt.Color;
	import java.awt.GridLayout;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;

	import sun.audio.AudioData;
	import sun.audio.AudioPlayer;
	import sun.audio.AudioStream;
	import sun.audio.ContinuousAudioDataStream;


	public class Gamepanel extends JFrame implements ActionListener, Memory{

		private JPanel mainPanel, cardPanel, southPanel, northPanel, eastPanel, westPanel;
		private JButton[][] matchingCards; 
		private JButton flipBack, endGame; 
		private JLabel attempts, matches, score, title,title2, status; 

		private int counter = 0;
		private int match = 0;
		private int attempt = 0;
		
		private boolean rugratsMatch= false;
		private boolean  HeyArnoldMatch= false;
		private boolean  DextersLabMatch= false;
		private boolean PowerpuffgirlsMatch = false;
		private boolean CatDogMatch= false;
		private boolean KimPossibleMatch= false;
		
		private boolean noMatch = false;
		private ImageIcon preLogo = new ImageIcon("/Users/kerstyne/Pictures/logo.png"); 
		private ImageIcon logo = new ImageIcon(((Image)preLogo.getImage()).getScaledInstance(200, 200,Image.SCALE_SMOOTH));
		private ImageIcon prerugrats = new ImageIcon("/Users/kerstyne/Pictures/rugrats.png");
		private ImageIcon rugrats = new ImageIcon(((Image)prerugrats.getImage()).getScaledInstance(100,175,Image.SCALE_FAST));
		private ImageIcon preHeyArnold = new ImageIcon("/Users/kerstyne/Pictures/hey arnold2.png");
		private ImageIcon HeyArnold = new ImageIcon(((Image)preHeyArnold.getImage()).getScaledInstance(150,75,Image.SCALE_FAST));
		private ImageIcon preDexterLab = new ImageIcon("/Users/kerstyne/Pictures/dexter lab.png");
		private ImageIcon DexterLab = new ImageIcon(((Image)preDexterLab.getImage()).getScaledInstance(150,150,Image.SCALE_FAST));
		private ImageIcon prePowerpuffgirls = new ImageIcon("/Users/kerstyne/Pictures/powerpuff girls.png");
		private ImageIcon Powerpuffgirls = new ImageIcon(((Image)prePowerpuffgirls.getImage()).getScaledInstance(75,150,Image.SCALE_FAST));
		private ImageIcon preCatDog = new ImageIcon("/Users/kerstyne/Pictures/cat and dog.png");
		private ImageIcon CatDog = new ImageIcon(((Image)preCatDog.getImage()).getScaledInstance(150,150,Image.SCALE_FAST));
		private ImageIcon preKimPossible = new ImageIcon("/Users/kerstyne/Pictures/kim possible.png");
		private ImageIcon KimPossible = new ImageIcon(((Image)preKimPossible.getImage()).getScaledInstance(175,200,Image.SCALE_FAST));
		
		
		public Gamepanel() {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			
			cardPanel = new JPanel();
			cardPanel.setLayout(new GridLayout(3,4));
			
			
			southPanel = new JPanel();
			southPanel.setLayout(new GridLayout(2,2));
			
			northPanel = new JPanel();
			northPanel.setLayout(new BorderLayout());
			
			eastPanel = new JPanel();
			westPanel = new JPanel();
			
			flipBack = new JButton("Continue");
			
			
			endGame = new JButton("End Game");
			attempts = new JLabel("Attempts: ");
     		matches = new JLabel("Matches: ");
			score = new JLabel("Score: ");	
			title = new JLabel("Lets play a throwback memory game");
			title2 =new JLabel("Do You have good memory?");
			status = new JLabel("Status: ");
			
			setBoard();
			
			
			
			flipBack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("click");
					flipAgain();
					
				}
				
			});
			JFrame A = new JFrame();
			JFrame B = new JFrame();
			String message = "Would you like to end the game?";
			endGame.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int keepGoing = JOptionPane.showConfirmDialog(A, message, "End Game?", JOptionPane.YES_NO_OPTION);
					if (JOptionPane.showConfirmDialog(A, message, "End Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						double finalScore = ((double)match / (double)attempt) * 100.0;
						String memoryAbility = "Score is " + (int)finalScore + ". " + userAbility();
						
						JOptionPane.showMessageDialog(B, memoryAbility);
						System.exit(0);
					}
					
				}
				
			});
			southPanel.add(flipBack);
			southPanel.add(status);
			southPanel.add(endGame);
			southPanel.add(score);
			
			northPanel.add(title, BorderLayout.NORTH);
			northPanel.add(title2, BorderLayout.NORTH);
			northPanel.add(attempts, BorderLayout.WEST);
			northPanel.add(matches, BorderLayout.EAST);
			
			northPanel.setBackground(Color.white);
			eastPanel.setBackground(Color.BLUE);
			westPanel.setBackground(Color.magenta);
			southPanel.setBackground(Color.magenta);
			cardPanel.setBackground(Color.CYAN);
			
			
			mainPanel.add(cardPanel, BorderLayout.CENTER);
			mainPanel.add(southPanel, BorderLayout.SOUTH);
			mainPanel.add(northPanel, BorderLayout.NORTH);
			mainPanel.add(eastPanel, BorderLayout.EAST);
			mainPanel.add(westPanel, BorderLayout.WEST);
			add(mainPanel);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnClicked = (JButton)e.getSource();
			flipCard(btnClicked);
			btnClicked.setEnabled(false);
			counter++;
			if (counter % 2 == 0) {
				cardMatch();
				attemptsMade();
			}
			userScore();
		} 
		@Override
		public void setBoard() {
			matchingCards = new JButton[3][4];
			for(int i = 0; i < matchingCards.length; i++) {
				for(int j = 0; j < matchingCards[i].length; j++) {
					matchingCards[i][j] = new JButton(logo);
					matchingCards[i][j].addActionListener(this);
					cardPanel.add(matchingCards[i][j]);
				}
			}
			
		}
		 public void flipCard(JButton btnClicked) {
			if (btnClicked == matchingCards[0][0]) {
				btnClicked.setIcon(rugrats);
				btnClicked.setDisabledIcon(rugrats);
			}
			if (btnClicked == matchingCards[0][1]) {
				btnClicked.setIcon(rugrats);
				btnClicked.setDisabledIcon(rugrats);
			}
			if (btnClicked == matchingCards[0][2]) {
				btnClicked.setIcon(HeyArnold);
				btnClicked.setDisabledIcon(HeyArnold);
			}
			if (btnClicked == matchingCards[0][3]) {
				btnClicked.setIcon(HeyArnold);
				btnClicked.setDisabledIcon(HeyArnold);
			}
			if (btnClicked == matchingCards[1][0]) {
				btnClicked.setIcon(Powerpuffgirls);
				btnClicked.setDisabledIcon(Powerpuffgirls);
			}
			if (btnClicked == matchingCards[1][1]) {
				btnClicked.setIcon(Powerpuffgirls);
				btnClicked.setDisabledIcon(Powerpuffgirls);
			}
			if (btnClicked == matchingCards[1][2]) {
				btnClicked.setIcon(DexterLab);
				btnClicked.setDisabledIcon(DexterLab);
			}
			if (btnClicked == matchingCards[1][3]) {
				btnClicked.setIcon(DexterLab);
				btnClicked.setDisabledIcon(DexterLab);
			}
			if (btnClicked == matchingCards[2][0]) {
				btnClicked.setIcon(CatDog);
				btnClicked.setDisabledIcon(CatDog);
			}
			if (btnClicked == matchingCards[2][1]) {
				btnClicked.setIcon(CatDog);
				btnClicked.setDisabledIcon(CatDog);
			}
			if (btnClicked == matchingCards[2][2]) {
				btnClicked.setIcon(KimPossible);
				btnClicked.setDisabledIcon(KimPossible);
			}
			if (btnClicked == matchingCards[2][3]) {
				btnClicked.setIcon(KimPossible);
				btnClicked.setDisabledIcon(KimPossible);
			}
			
		}

		public void cardMatch() {
			
			if (rugratsMatch == false) {
				if (matchingCards[0][0].isEnabled() == false && matchingCards[1][3].isEnabled() == false) {
					rugratsMatch = true; 
					match++;
					userStatus(rugratsMatch);
					System.out.println("MATCH");
				}
			}
			if (HeyArnoldMatch == false) {
				if (matchingCards[0][1].isEnabled() == false && matchingCards[2][2].isEnabled() == false) {
					HeyArnoldMatch = true; 
					match++;
					userStatus(HeyArnoldMatch);
					System.out.println("MATCH");
				}
				
			}
			if (PowerpuffgirlsMatch == false) {
				if (matchingCards[0][2].isEnabled() == false && matchingCards[2][0].isEnabled() == false) {
					PowerpuffgirlsMatch = true; 
					match++;
					userStatus(PowerpuffgirlsMatch);
					System.out.println("MATCH");
				}
				
			}
			if (DextersLabMatch == false) {
				if (matchingCards[0][3].isEnabled() == false && matchingCards[2][3].isEnabled() == false) {
					DextersLabMatch = true; 
					match++;
					userStatus(DextersLabMatch);
					System.out.println("MATCH");
				}
				
			}
			if (CatDogMatch == false) {
				if (matchingCards[1][0].isEnabled() == false && matchingCards[2][1].isEnabled() == false) {
					CatDogMatch = true; 
					match++;
					userStatus(CatDogMatch);
					System.out.println("MATCH");
				}
				
			}
			if (KimPossibleMatch == false) {
				if (matchingCards[1][1].isEnabled() == false && matchingCards[1][2].isEnabled() == false) {
					KimPossibleMatch = true; 
					match++;
					userStatus(KimPossibleMatch);
					System.out.println("MATCH");
				}
				
			}
			
			matchesFound();
		
		}
		
		public void flipAgain() {
			for(int i = 0; i < matchingCards.length; i++) {
				for(int j = 0; j < matchingCards[i].length; j++) {
					matchingCards[i][j].setIcon(logo);
					matchingCards[i][j].setEnabled(true);
				}
			}
			if (rugratsMatch == true) {
				matchingCards[1][0].setIcon(rugrats); matchingCards[1][0].setEnabled(false);
				matchingCards[2][1].setIcon(rugrats); matchingCards[2][1].setEnabled(false);
			}
			if (HeyArnoldMatch == true) {
				matchingCards[0][0].setIcon(HeyArnold); matchingCards[0][0].setEnabled(false);
				matchingCards[1][3].setIcon(HeyArnold); matchingCards[1][3].setEnabled(false);
			}
			if (PowerpuffgirlsMatch == true) {
				matchingCards[0][3].setIcon(Powerpuffgirls); matchingCards[0][3].setEnabled(false);
				matchingCards[2][3].setIcon(Powerpuffgirls); matchingCards[2][3].setEnabled(false);
			}
			if (DextersLabMatch == true) {
				matchingCards[1][1].setIcon(DexterLab); matchingCards[1][1].setEnabled(false);
				matchingCards[1][2].setIcon(DexterLab); matchingCards[1][2].setEnabled(false);
			}
			if (CatDogMatch == true) {
				matchingCards[0][2].setIcon(CatDog); matchingCards[0][2].setEnabled(false);
				matchingCards[2][0].setIcon(CatDog); matchingCards[2][0].setEnabled(false);
			}
			if (KimPossibleMatch == true) {
				matchingCards[0][1].setIcon(KimPossible); matchingCards[0][1].setEnabled(false);
				matchingCards[2][2].setIcon(KimPossible); matchingCards[2][2].setEnabled(false);
			}
			status.setText("Status: ");
		}

		@Override
		public void attemptsMade() {
			
			attempt = counter / 2;
			attempts.setText("Attempts: " + attempt);
		}

		@Override
		public void matchesFound() {
			
			matches.setText("Matches: " + match);
		}

		@Override
		public void userScore() {
			
			double finalScore = 0;
			if (match == 0) {
				finalScore = 0.0;
			} else {
				finalScore = (double)match / (double)attempt;
			}
			score.setText("Score: " + (finalScore * 100.0));
		}
		
		public void userStatus(boolean b1) {
			status.setText("Status: Not a match! Hit continue to flip over!");
			if (b1 == true) {
				status.setText("Status: Match Complete! Congrats!");
			} else {
				status.setText("Status: Not a match! Hit continue to flip over!");
			}
		}

		@Override
		public String userAbility() {

			double finalScore = ((double)match / (double)attempt) * 100.0;
			String memoryAbility = null;
			
			if (finalScore  < 5) {
				memoryAbility = "your memory aint shit";
				music();
			} else if (finalScore < 10) {
				memoryAbility = "Your memory is trash, bro";
				music();
			} else if (finalScore < 30){
				memoryAbility = "Bro, you smoke too much for this type of memory";
				music();
			} else if (finalScore < 60) {
				memoryAbility = "Your memory is alright, i guess i would see a doctor tho";
				music();
			} else if (finalScore < 80) {
				memoryAbility = "Your memory is awesomesauce";
				music();
			} else if (finalScore < 97) {
				memoryAbility = "Your memory is FIRE!!!!!!";
				music();
			}
			
			return memoryAbility;
		}
		
		public void preventFlip() {
			
			for(int i = 0; i < matchingCards.length; i++) {
				for(int j = 0; j < matchingCards[i].length; j++) {
					
					matchingCards[i][j].setEnabled(false);
				}
			}
		}
		public void music() {
			AudioPlayer MGP = AudioPlayer.player;
			AudioStream BGM;
			AudioData MD;
			ContinuousAudioDataStream loop = null;
			try {
				BGM = new AudioStream(new FileInputStream("applause-2.wav"));
				MD = BGM.getData();
				loop =new ContinuousAudioDataStream(MD);
			}catch(IOException error) {}
			MGP.start((InputStream)loop); 
			
				}
		

	}
