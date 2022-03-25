package ProjectTic;
// Imports of swing & event for GUI & GUI interaction, SQL imports relevant for a database
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login implements ActionListener{
	
    // Creates instance variables
    private static JFrame loginFrame;
    private static JPanel loginPanel;
    private static JLabel usernameLabel;
    private static JLabel passwordLabel;
    private static JTextField usernameTextField;
    private static JPasswordField passwordTextField;
    private static JButton loginButton;
    private static JButton signUpButton;
    
    public static void main(String[] args) {    
    	
        // Creating instance of the login JFrame& sets it's size 
        loginFrame = new JFrame("Arcade Games Login");
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panel is created and added to JFrame
        loginPanel = new JPanel();    
        loginFrame.add(loginPanel);
        
        // Calls a method that will place components onto the panel
        loginPanelComponentsAddition(loginPanel);

        // Allows the user to see the frame
        loginFrame.setVisible(true);
    }

    private static void loginPanelComponentsAddition(JPanel panel) {

        // Null layout
        panel.setLayout(null);

        // Creates a Username Label, sets it's location, & adds it to the panel
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10,20,80,25);
        panel.add(usernameLabel);

        // Creats a Username Text Field, sets it's location, & adds it to the panel
        usernameTextField = new JTextField(20);
        usernameTextField.setBounds(100,20,165,25);
        panel.add(usernameTextField);

        // Same as the username for the password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordTextField = new JPasswordField(20);
        passwordTextField.setBounds(100,50,165,25);
        panel.add(passwordTextField);

        // Creates a login button and allows for logins to occur when pressed
        loginButton = new JButton("Sign in");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new Login());
        panel.add(loginButton);
        
        // Same as the login button for the sign up button
        signUpButton = new JButton("Sign up");
        signUpButton.setBounds(100, 80, 80, 25);
        signUpButton.addActionListener(new Login());
        panel.add(signUpButton);
        
    }
    
    Connection c;
	PreparedStatement s;
	
	public void actionPerformed(ActionEvent e) {
		
		// Stores the user and pass typed by the user to strings
		String tempUsername = usernameTextField.getText();
        String tempPassword = passwordTextField.getText();
        
        // If statement that goes through if the sign up button is clicked
		if(e.getSource() == signUpButton) {
		// Try catch statement 
		try {
			
			//Allows for communication to the database
			Class.forName("com.mysql.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");
			
			// Communicates to the SQL database through SQL syntax in Java
			s = c.prepareStatement("insert into data(user,pass,elo,win,loss)values(?,?,?,?,?)");
			s.setString(1,tempUsername);
			s.setString(2, tempPassword);
			s.setInt(3, 1000);
			s.setInt(4, 0);
			s.setInt(5, 0);
			//Updates database
			s.executeUpdate();
			
			// Pop up notifying the user of a successful sing up
			JOptionPane.showMessageDialog(null, "Successful Sign Up!");

			//Empties the text field
			usernameTextField.setText("");
			passwordTextField.setText("");
			
			//Sets the cursor within the username text field
			usernameTextField.requestFocus();
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}
		
		// If statement that goes through if the login button is clicked
		if(e.getSource() == loginButton) {
			try {
				
			//Allows for communication to the database	
			Class.forName("com.mysql.jdbc.Driver");
			
			c = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");

			// Communicates to the SQL database through SQL syntax in Java
			s = c.prepareStatement("Select * from data where user=? and pass=?");
			s.setString(1, usernameTextField.getText());
			s.setString(2, usernameTextField.getText());
			//Runs query
			ResultSet r = s.executeQuery();
			if(r.next()) {
				
				// Pop up notifying the user of a successful sing in
				JOptionPane.showMessageDialog(null, "Successful Sign In!");
				
				//Opens up new GUI for the player to choose their symbol & makes the login frame not visible anymore
				Player1Choice pc1 = new Player1Choice(usernameTextField.getText(), passwordTextField.getText());
				 loginFrame.setVisible(false);
			}
			else {
				
				// Pop up notifying the user of a failed sing in
				JOptionPane.showMessageDialog(null, "Incorrect Sign In Credentials");
			}
			c.close();
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}
	}
}
