package memorygame;



import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class ContinueButton extends AbstractAction {
	public ContinueButton() {
		super(); //necessary?
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton buttonClicked = (JButton)event.getSource();
		System.out.println("click");
		
		
	}
	
	
	//NOT NEEDED
	

}