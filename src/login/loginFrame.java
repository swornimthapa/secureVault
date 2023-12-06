package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Dashboard.dashboardFrame;


import UI_elements.RoundedButton;
import UI_elements.RoundedLabel;
import UI_elements.RoundedPanel;
public class loginFrame implements ActionListener {
    JFrame frame = new JFrame("login");
    JButton btnNewButton;
    JTextField userTextfiled;
    JTextField passwordTextfiled;
    RoundedButton loginButton;
    RoundedButton Register;

    loginFrame() {

        frame.setResizable(false);
        frame.setSize(900, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);

        initializecomponents();
    }

    private void initializecomponents() {
        ImageIcon imageIcon = new ImageIcon("D:\\java4thsem\\securevault\\src\\key.png");

        // Resize the image by setting the preferred size of the ImageIcon
        Image image = imageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);

        JLabel label = new JLabel(imageIcon);
        label.setBounds(450, 0, 500, 500);
        frame.getContentPane().add(label);




        JLabel lblUsername = new JLabel("UserName");
        JLabel lblpassword = new JLabel("Password");
        JLabel lblNewLabel = new JLabel("LogIn");

        lblNewLabel.setBounds(390, 40, 200, 40);
        lblUsername.setBounds(190, 145, 200, 50);
        lblpassword.setBounds(190, 245, 200, 50);

        lblNewLabel.setForeground(Color.white);
        lblUsername.setForeground(Color.white);
        lblpassword.setForeground(Color.white);

        lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
        lblpassword.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));

        frame.getContentPane().add(lblUsername);
        frame.getContentPane().add(lblpassword);
        frame.getContentPane().add(lblNewLabel);

        loginButton = new RoundedButton("LogIn", 30);
        loginButton.setBounds(210, 350, 180, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));

        loginButton.addActionListener(this);
        frame.getContentPane().add(loginButton);

        Register = new RoundedButton("Register", 30);
        Register.setBounds(400, 350, 180, 40);
        Register.setFont(new Font("Arial", Font.BOLD, 15));
        Register.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        Register.addActionListener(this);
        frame.getContentPane().add(Register);

        //for server
        userTextfiled = new JTextField();
        userTextfiled.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        userTextfiled.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        userTextfiled.setBounds(310, 150, 210, 40);
        userTextfiled.setFont(new Font("Arial", Font.BOLD, 20));
        frame.getContentPane().add(userTextfiled);


        passwordTextfiled = new JTextField();
        passwordTextfiled.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordTextfiled.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        passwordTextfiled.setBounds(310, 250, 210, 40);
        passwordTextfiled.setFont(new Font("Arial", Font.BOLD, 20));
        frame.getContentPane().add(passwordTextfiled);


        RoundedPanel serverpanel1 = new RoundedPanel(30);
        serverpanel1.setBounds(120, 20, 700, 400);
        serverpanel1.setOpaque(false);
        // serverpanel1.setBackground(new Color(25, 25, 15, 200));
        serverpanel1.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(serverpanel1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
                    dashboardFrame dashboardFrame = new dashboardFrame();
                                frame.dispose();
//            String userName = userTextfiled.getText();
//            String password = passwordTextfiled.getText();
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                String url = "jdbc:mysql://localhost/securevault";
//                Connection connection = (Connection) DriverManager.getConnection(url, "root", "Sw@mysql001");
//
//                PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
//                st.setString(1, userName);
//                st.setString(2, password);
//
//                ResultSet rs = st.executeQuery();
//
//                if (rs.next()) {
//                    // Username and password are correct
//                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
//
//                    // Open a new form or perform any other action
//                    EventQueue.invokeLater(new Runnable() {
//                        public void run() {
//                            try {
//                                dashboardFrame dashboardFrame = new dashboardFrame();
//                                frame.dispose();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                } else {
//                    // Wrong username or password
//                    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            } catch (ClassNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
        }

        if (e.getSource() == Register) {
            String userName = userTextfiled.getText();
            String password = passwordTextfiled.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost/securevault";
                Connection connection = (Connection) DriverManager.getConnection(url, "root", "Sw@mysql001");


                PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
                st.setString(1, userName);
                st.setString(2, password);

                ResultSet rs = st.executeQuery();
                if (!rs.next()) {
                    // Corrected INSERT statement without WHERE clause
                    st = (PreparedStatement) connection.prepareStatement("INSERT INTO user (name, password) VALUES (?, ?)");
                    st.setString(1, userName);
                    st.setString(2, password);

                    // Execute the query
                    int rowsAffected = st.executeUpdate();

                    // Close the PreparedStatement and Connection
                    st.close();
                    connection.close();

                    // Show dialog box if registration is successful
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(Register, "Registration successful!");
                    } else {
                        JOptionPane.showMessageDialog(Register, "Registration failed. Please try again.");
                    }
                }else{
                    JOptionPane.showMessageDialog(btnNewButton, "Registration unsuccessful");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}


