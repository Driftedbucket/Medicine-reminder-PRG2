import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;


public class loginPage {

    public static JTextField usernamefield;
    public static  JTextField passwordfield;

    public static void logPage() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new BorderLayout());

        // ppanel to hold everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // load Image
        ImageIcon imageIcon = new ImageIcon("pills2.jpeg"); // Make sure the image is in your project folder
        Image img = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // for username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamefield = new JTextField(20);
        usernamefield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // for password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordfield = new JPasswordField(20);
        passwordfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // for login button
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components so we see them
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(usernameLabel);
        mainPanel.add(usernamefield);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordfield);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(loginButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // submit button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDBPage();
            }
        });
    }

    public static void loginDBPage(){
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        try(Connection conn = DatabaseConnection.getConnection()){
        CallableStatement stmt = conn.prepareCall("{CALL userLogin(?, ?)}");
        stmt.setString(1,username);
        stmt.setString(2, password);
        stmt.execute();
        JOptionPane.showMessageDialog(null,"Successfully logged in fn!😁");
        }catch(SQLException e){
        e.printStackTrace();
        }

    }

}
