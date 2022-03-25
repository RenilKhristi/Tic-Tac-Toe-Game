package ProjectTic;
//Imports of swing & event for GUI & GUI interaction, SQL imports relevant for a database
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class TicTacToe implements ActionListener {
	// Creates instance variables
	private static JButton newGameButton;
	public boolean player1Symbol;
	public boolean player1Choice;
	public String username;
	public String password;
	
	// Creation of the JFrame
	JFrame frame = new JFrame("Tic Tac Toe");
	// Creation of the Panels needed
	JPanel titlePanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JPanel commandsPanel = new JPanel();
	
	// Creates the label for the title and the tic-tac-toe buttons used
	JLabel titleLabel = new JLabel();
	JButton[] buttons = new JButton[9];
	
	TicTacToe(boolean symbol, String user, String pass) {
		
		// Basic frame design, closes it upon pressing X, sets the size and the background coloring
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(115,100,105));
		
		// Chooses the java swing BoyderLayout and makes the frame visible
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// Sets the title label's background and text color, the font, alignment, makes the text say Tic-Tac-Toe and makes it opaque
		titleLabel.setBackground(new Color(247,123,105));
		titleLabel.setForeground(new Color(70,175,200));
		titleLabel.setFont(new Font("Courier BOLD",Font.ITALIC,115));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setText("Tic-Tac-Toe");
		titleLabel.setOpaque(true);
		// Makes the title panel have a borderlayout and sets it's bounds
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0,0,800,100);
		
		// Makes the button panel have a borderlayout and sets it's bounds
		buttonsPanel.setLayout(new GridLayout(3,3));
		buttonsPanel.setBackground(new Color(150,150,150));
		
		// For loop that creates buttons, adds them onto the panel, adjusts their features and allows for action occurence upon one being pressed
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonsPanel.add(buttons[i]);
			buttons[i].setFont(new Font("Monospaced Plain",Font.BOLD,95));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		// Adds title label to the title panel
		titlePanel.add(titleLabel);
		
		// Adds all of the panels onto the frame 
		frame.add(titlePanel,BorderLayout.NORTH);
		frame.add(buttonsPanel);
		frame.add(commandsPanel,BorderLayout.SOUTH);
		
		// Creates a new button and adjusts it's features, and allows for action upon being pressed
		newGameButton = new JButton("New Game");
        newGameButton.setBounds(10, 80, 80, 25);
        newGameButton.addActionListener(this);
        // Adds the new game button onto the command panel
        commandsPanel.add(newGameButton);
		
        username = user;
        password = pass;
       
        // If else statement that saves if the player chose the symbol x or y
        if(symbol) {
        	player1Symbol = true;
        	player1Choice = true;
        	titleLabel.setText("Player X turn");
        }
        else {
        	player1Symbol=false;
        	player1Choice = false;
        	titleLabel.setText("Player O turn");
        }
		
	}

	public void actionPerformed(ActionEvent e) {
		
		// If the new game button is pressed, creates a fresh tic-tac-toe game
		if(e.getSource() == newGameButton) {
			frame.setVisible(false);
			TicTacToe newTic = new TicTacToe(player1Choice, username, password);
			
		}
		// For loop that will see if one of the 9 tic-tac-toe buttons are pressed and if it is, will show the symbol on that button, change it's color, and move turns
		for(int i = 0; i < 9; i++) {
			if(e.getSource() == buttons[i]) {
				if(player1Symbol) {
					if(buttons[i].getText() == "") {
						
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1Symbol=false;
						titleLabel.setText("Player O turn");
						check();
					}
				}
				else {
					if(buttons[i].getText() == "") {
						
						buttons[i].setForeground(new Color(0,0,0));
						buttons[i].setText("O");
						player1Symbol=true;
						titleLabel.setText("Player X turn");
						check();
					}
				}
			}			
		}
	}
	
	// Method to determine if a player has won
	public void check() {
		
		// Series of if statements that will check all of the scenarios to determine if player X or O has won
		if((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
			xWins(0,1,2);
		}
		else if((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
			xWins(3,4,5);
		}
		else if((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(6,7,8);
		}
		else if((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
			xWins(0,3,6);
		}
		else if((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
			xWins(1,4,7);
		}
		else if((buttons[2].getText() == "X") && (buttons[5].getText() == "X") &&(buttons[8].getText() == "X")) {
			xWins(2,5,8);
		}
		else if((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText( )== "X")) {
			xWins(0,4,8);
		}
		else if((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
			xWins(2,4,6);
		}
		else if((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
			oWins(0,1,2);
		}
		else if((buttons[3].getText()== "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
			oWins(3,4,5);
		}
		else if((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(6,7,8);
		}
		else if((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
			oWins(0,3,6);
		}
		else if((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
			oWins(1,4,7);
		}
		else if((buttons[2].getText() == "O") &&(buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(2,5,8);
		}
		else if((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(0,4,8);
		}
		else if((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
			oWins(2,4,6);
		}
	}
	
	Connection c;
	PreparedStatement s;
	
	public void xWins(int x,int y,int z) {
		// Sets the winning buttons background to red 
		buttons[x].setBackground(Color.RED);
		buttons[y].setBackground(Color.RED);
		buttons[z].setBackground(Color.RED);
		
		// Disabled all of the buttons because the game is concluded
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		// The title label displays that player X won
		titleLabel.setText("Player X wins");
		
		if(player1Choice == true) {
			try {
				// Communicates to the SQL database to save the player's elo, wins, & losses after a game has concluded
				Class.forName("com.mysql.jdbc.Driver");
				
				c = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");
				
				s = c.prepareStatement("Select * from data where user=? and pass=?");
				s.setString(1, username);
				s.setString(2, password);
				ResultSet r = s.executeQuery();
				
				if(r.next()) {
					s = c.prepareStatement("UPDATE data set user=? , pass=? , elo=? , win=? ,loss=? where id="+r.getInt(1));
					int newElo = Integer.parseInt(r.getString(4)) + 10;
					int newWin = Integer.parseInt(r.getString(5)) + 1;
					s.setString(1, username);
					s.setString(2, password);
					s.setInt(3, newElo);
					s.setInt(4, newWin);
					s.setInt(5, r.getInt(6));
					s.executeUpdate();
				}
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else {
			try {
				// Communicates to the SQL database to save the player's elo, wins, & losses after a game has concluded
				Class.forName("com.mysql.jdbc.Driver");
				
				c = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");
				
				s = c.prepareStatement("Select * from data where user=? and pass=?");
				s.setString(1, username);
				s.setString(2, password);
				ResultSet r = s.executeQuery();
				
				if(r.next()) {
					s = c.prepareStatement("UPDATE data set user=? , pass=? , elo=? , win=? ,loss=? where id="+r.getInt(1));
					int newElo = Integer.parseInt(r.getString(4)) - 10;
					int newLoss = Integer.parseInt(r.getString(5)) + 1;
					s.setString(1, username);
					s.setString(2, password);
					s.setInt(3, newElo);
					s.setInt(4, r.getInt(5));
					s.setInt(5, newLoss);
					s.executeUpdate();
				}
			
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void oWins(int x,int y,int z) {
		// Sets the winning buttons background to red 
		buttons[x].setBackground(Color.GREEN);
		buttons[y].setBackground(Color.GREEN);
		buttons[z].setBackground(Color.GREEN);
		
		// Disabled all of the buttons because the game is concluded
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		// The title displays that player O won
		titleLabel.setText("Player O wins");
		
		if(player1Choice == false) {
			try {
				
				// Communicates to the SQL database to save the player's elo, wins, & losses after a game has concluded
				Class.forName("com.mysql.jdbc.Driver");
				
				c = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");
				
				s = c.prepareStatement("Select * from data where user=? and pass=?");
				s.setString(1, username);
				s.setString(2, password);
				ResultSet r = s.executeQuery();
				
				if(r.next()) {
					s = c.prepareStatement("UPDATE data set user=? , pass=? , elo=? , win=? ,loss=? where id="+r.getInt(1));
					int newElo = Integer.parseInt(r.getString(4)) + 10;
					int newWin = Integer.parseInt(r.getString(5)) + 1;
					s.setString(1, username);
					s.setString(2, password);
					s.setInt(3, newElo);
					s.setInt(4, newWin);
					s.setInt(5, r.getInt(6));
					s.executeUpdate();		
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else {
			try {
				// Communicates to the SQL database to save the player's elo, wins, & losses after a game has conclude
				Class.forName("com.mysql.jdbc.Driver");
			
				c = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");
				
				s = c.prepareStatement("Select * from data where user=? and pass=?");
				s.setString(1, username);
				s.setString(2, password);
				ResultSet r = s.executeQuery();
				
				if(r.next()) {
					s = c.prepareStatement("UPDATE data set user=? , pass=? , elo=? , win=? ,loss=? where id="+r.getInt(1));
					int newElo = Integer.parseInt(r.getString(4)) - 10;
					int newLoss = Integer.parseInt(r.getString(6)) + 1;
					s.setString(1, username);
					s.setString(2, password);
					s.setInt(3, newElo);
					s.setInt(4, r.getInt(5));
					s.setInt(5, newLoss);
					s.executeUpdate();
				}
			}  catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}