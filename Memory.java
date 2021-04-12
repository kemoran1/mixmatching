package memorygame;

	import javax.swing.JButton;

	public interface Memory {
		
		//initial
		public void setBoard();
		public void flipCard(JButton jb);
		public void cardMatch();
		public void attemptsMade();
		public void matchesFound();
		public void userScore();
		public String userAbility(); //might have to return the string
		
		
		
		
	}