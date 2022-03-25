package ProjectTic;
//Imports of swing & event for GUI & GUI interaction
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Player1Choice implements ActionListener{
	
	// Creates instance variables
	private static JFrame player1Frame;
	private static JPanel playerPanel;
	private static JRadioButton xOption;
	private static JRadioButton oOption;
	private static JLabel player1Choice;
	private String user;
	private String pass;
	
	Player1Choice (String user, String pass) {
		
        // Creating instance of the login JFrame& sets it's size 
		player1Frame = new JFrame();
		player1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player1Frame.setSize(225,150);
	    
        // Panel is created and added to JFrame
	    playerPanel = new JPanel();    
	    //Adds panel to frame
	    player1Frame.add(playerPanel);
	    
        // Null layout
	    playerPanel.setLayout(null);
	    
        // Allows the user to see the frame
	    player1Frame.setVisible(true);
	    
	    //Creates new Radio buttons & Labels
		xOption = new JRadioButton("X");
		oOption = new JRadioButton("O");
		player1Choice = new JLabel();
		
		player1Choice = new JLabel("Player 1 Choose Your Symbol:");
		
	//Sets label dimensions & adds it to the panel
        player1Choice.setBounds(10,10,200,25);
        playerPanel.add(player1Choice);
     
        //Sets the option's radio buttons dimensions, allows for actions when pressed, & adds them to the panel
        xOption.setBounds(30,45,100,15);
        xOption.addActionListener(this);
        playerPanel.add(xOption);
        
        oOption.setBounds(30,75,100,15);
        oOption.addActionListener(this);
        playerPanel.add(oOption);
        
        this.user = user;
        this.pass = pass;
	}

	public void actionPerformed(ActionEvent e) {
		
		// User chooses x as their symbol
		if(xOption.isSelected()) {
			TicTacToe xFirst = new TicTacToe(true, user, pass);
		}
		
		// User chooses o as their symbol
		else if(oOption.isSelected()) {
			TicTacToe oFirst = new TicTacToe(false, user, pass);
		}
        player1Frame.setVisible(false);    
	}
}
