import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupPage {

    private static JTextField fnameField, lnameField, emailField, usernameField;
    private static JPasswordField passwordField, password2Field;

    public static void signPage() {
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null); // center the frame

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // top, left, bottom, right padding

        // First Name
        JLabel fnameLabel = new JLabel("First Name:");
        fnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fnameField = new JTextField(15);
        fnameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Last Name
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lnameField = new JTextField(15);
        lnameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField = new JTextField(15);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Confirm Password
        JLabel password2Label = new JLabel("Confirm Password:");
        password2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        password2Field = new JPasswordField(15);
        password2Field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        mainPanel.add(fnameLabel);
        mainPanel.add(fnameField);
        mainPanel.add(Box.createVerticalStrut(10)); // spacing
        mainPanel.add(lnameLabel);
        mainPanel.add(lnameField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(password2Label);
        mainPanel.add(password2Field);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(submitButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Calling the signUser method
                signUser();
            }
        });
    }

    private static void signUser(){
        String firstName = fnameField.getText();
        String lastName = lnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String password2 = password2Field.getText();

        if(password.equals(password2)){
            try(Connection conn = DatabaseConnection.getConnection()){
                //only when you don't have procedures
                // String query = "insert into user(FirstName, LastName, email, username, password) values(?, ?, ?, ?, ?)";
                //only when you don't have procedures
                // PreparedStatement stmt = conn.prepareStatement(query);
                CallableStatement stmt = conn.prepareCall("{CALL userSignup(?,?,?,?) }");
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, username);
                stmt.setString(4, password);
                stmt.execute();

                JOptionPane.showMessageDialog(null, "Account created successfully! Please log in!👌");
                loginPage.logPage();

            }catch (SQLException e){
                e.printStackTrace();

            }

        }else{
            JOptionPane.showMessageDialog(null, "Passwords must match!");
        }

    }

}




